//package com.example.jeevanjyotandroidapplication;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//public class ChatBot extends AppCompatActivity {
//    private LinearLayout chatLayout;
//    private EditText userInput;
//    private Button sendButton;
//    private RequestQueue requestQueue;
//    private String API_KEY;
//    private String API_URL = "https://openrouter.ai/api/v1/chat/completions";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.userchat);
//
//        chatLayout = findViewById(R.id.chatLayout);
//        userInput = findViewById(R.id.userInput);
//        sendButton = findViewById(R.id.sendButton);
//        requestQueue = Volley.newRequestQueue(this);
//
//        API_KEY = getString(R.string.openrouter_api_key); // Secure API key storage
//
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = userInput.getText().toString().trim();
//                if (!message.isEmpty()) {
//                    addMessage("You: " + message);
//                    sendMessageToBot(enhanceMessageWithHealthContext(message));
//                    userInput.setText("");
//                }
//            }
//        });
//    }
//    private String enhanceMessageWithHealthContext(String message) {
//        if (message.toLowerCase().contains("symptoms") || message.toLowerCase().contains("fever")) {
//            return "Analyze symptoms and provide general health advice: " + message;
//        } else if (message.toLowerCase().contains("medicine")) {
//            return "Suggest possible over-the-counter medications with precautions: " + message;
//        } else if (message.toLowerCase().contains("emergency")) {
//            return "Provide first aid instructions and recommend seeking medical attention: " + message;
//        } else {
//            return "Provide general health guidance: " + message;
//        }
//    }
//    private void sendMessageToBot(String message) {
//        try {
//            JSONObject jsonBody = new JSONObject();
//            jsonBody.put("model", "google/gemini-2.0-pro-exp-02-05:free");
//            jsonBody.put("messages", new JSONArray().put(new JSONObject()
//                    .put("role", "user")
//                    .put("content", message)));
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, jsonBody,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                JSONArray choices = response.getJSONArray("choices");
//                                String botReply = choices.getJSONObject(0).getJSONObject("message").getString("content");
//                                botReply += "\n\n*Disclaimer: This is AI-generated health advice. Consult a doctor for accurate medical guidance.*";
//                                addMessage("Bot: " + botReply);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                addMessage("Bot: Sorry, I couldn't process that.");
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            addMessage("Bot: Error connecting to the server.");
//                        }
//                    }) {
//                @Override
//                public java.util.Map<String, String> getHeaders() {
//                    java.util.Map<String, String> headers = new java.util.HashMap<>();
//                    headers.put("Authorization", "Bearer " + API_KEY);
//                    headers.put("Content-Type", "application/json");
//                    return headers;
//                }
//            };
//
//            requestQueue.add(request);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void addMessage(String message) {
//        TextView textView = new TextView(this);
//        textView.setText(message);
//        chatLayout.addView(textView);
//    }
//}
package com.example.jeevanjyotandroidapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jeevanjyotandroidapplication.databinding.ActivityDashboardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatBot extends DrawerBaseActivity  {
    private LinearLayout chatLayout;
    private EditText userInput;
    private Button sendButton;
    private RequestQueue requestQueue;
    private String API_KEY;
    private String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    ActivityDashboardBinding activityDashboardBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());

        // Inflate Doctor's layout inside the drawer
        getLayoutInflater().inflate(R.layout.userchat, activityDashboardBinding.contentFrame, true);

        chatLayout = findViewById(R.id.chatLayout);
        userInput = findViewById(R.id.userInput);
        sendButton = findViewById(R.id.sendButton);
        requestQueue = Volley.newRequestQueue(this);

        API_KEY = getString(R.string.openrouter_api_key); // Secure API key storage

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = userInput.getText().toString().trim();
                if (!message.isEmpty()) {
                    addMessage("You", message, R.drawable.baseline_person_24);
                    sendMessageToBot(enhanceMessageWithHealthContext(message));
                    userInput.setText("");
                }
            }
        });
    }

    private String enhanceMessageWithHealthContext(String message) {
        if (message.toLowerCase().contains("symptoms") || message.toLowerCase().contains("fever")) {
            return "Analyze symptoms and provide general health advice: " + message;
        } else if (message.toLowerCase().contains("medicine")) {
            return "Suggest possible over-the-counter medications with precautions: " + message;
        } else if (message.toLowerCase().contains("emergency")) {
            return "Provide first aid instructions and recommend seeking medical attention: " + message;
        } else {
            return "Provide general health guidance: " + message;
        }
    }

    private void sendMessageToBot(String message) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("model", "google/gemini-2.0-pro-exp-02-05:free");
            jsonBody.put("messages", new JSONArray().put(new JSONObject()
                    .put("role", "user")
                    .put("content", message)));

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray choices = response.getJSONArray("choices");
                                String botReply = choices.getJSONObject(0).getJSONObject("message").getString("content");
                                botReply += "\n\n*Disclaimer: This is AI-generated health advice. Consult a doctor for accurate medical guidance.*";
                                addSeparator();
                                addMessage("Bot", botReply, R.drawable.baseline_chat_24);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                addMessage("Bot", "Sorry, I couldn't process that.", R.drawable.baseline_chat_24);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            addMessage("Bot", "Error connecting to the server.", R.drawable.baseline_chat_24);
                        }
                    }) {
                @Override
                public java.util.Map<String, String> getHeaders() {
                    java.util.Map<String, String> headers = new java.util.HashMap<>();
                    headers.put("Authorization", "Bearer " + API_KEY);
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            requestQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addMessage(String sender, String message, int iconResId) {
        LinearLayout messageLayout = new LinearLayout(this);
        messageLayout.setOrientation(LinearLayout.HORIZONTAL);
        messageLayout.setPadding(16, 8, 16, 8);

        ImageView icon = new ImageView(this);
        icon.setImageDrawable(ContextCompat.getDrawable(this, iconResId));
        icon.setLayoutParams(new LinearLayout.LayoutParams(80, 80));

        TextView textView = new TextView(this);
        textView.setText(sender + ": " + message);
        textView.setPadding(16, 8, 16, 8);
        textView.setBackground(ContextCompat.getDrawable(this, R.drawable.message_bg));
        textView.setTextColor(Color.BLACK);

        messageLayout.addView(icon);
        messageLayout.addView(textView);
        chatLayout.addView(messageLayout);
    }

    private void addSeparator() {
        View separator = new View(this);
        separator.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 2));
        separator.setBackgroundColor(Color.GRAY);
        chatLayout.addView(separator);
    }
}

