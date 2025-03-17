package com.example.jeevanjyotandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jeevanjyotandroidapplication.R;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class ChatBot extends AppCompatActivity {

    private EditText userInput;
    private Button sendButton;
    private LinearLayout chatLayout;
    private ScrollView scrollView;
    private GenerativeModelFutures model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userchat);

        // Initialize UI components
        userInput = findViewById(R.id.userInput);
        sendButton = findViewById(R.id.sendButton);
        chatLayout = findViewById(R.id.chatLayout);
        scrollView = findViewById(R.id.scrollView);

        // Initialize Gemini API model
        GenerativeModel gm = new GenerativeModel("models/gemini-1.5-pro",
                "AIzaSyC68fD23A05xMuFamdVA78QKgcBje9diFk"); // Replace with your API key
        model = GenerativeModelFutures.from(gm);

        // Set click listener on send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = userInput.getText().toString().trim();
                if (!query.isEmpty()) {
                    addMessage("You: " + query, true); // Show user message
                    fetchGeminiResponse(query);
                    userInput.setText(""); // Clear input field
                    userInput.setTextColor(Color.BLACK);
                }
            }
        });
    }

    // Function to fetch response from Gemini API
    private void fetchGeminiResponse(String query) {
        Content content = new Content.Builder().addText(query).build();
        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {
                    String resultText = result.getText();
                    runOnUiThread(() -> addMessage("Gemini: " + resultText, false));
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("GeminiAPI", "Error: " + t.toString());
                    runOnUiThread(() -> addMessage("Gemini: Error fetching response", false));
                }
            }, getMainExecutor());
        }
    }

    // Function to add a message to chat layout
    private void addMessage(String message, boolean isUser) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setPadding(16, 8, 16, 8);

        if (isUser) {
            textView.setBackgroundResource(R.drawable.rounded_bg);
        } else {
            textView.setBackgroundResource(R.drawable.rounded_bg);
        }

        chatLayout.addView(textView);
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN)); // Auto-scroll to latest message
    }
}
