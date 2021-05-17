package com.sjsu.bookacab.helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.cloud.dialogflow.v2.DetectIntentRequest;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.sjsu.bookacab.interfaces.ChatReply;

import static android.content.ContentValues.TAG;

public class RequestTaskHandler extends AsyncTask<Void, Void, DetectIntentResponse> {

    private ChatReply chatInterface;
    private SessionName session;
    private SessionsClient sessionsClient;
    private QueryInput queryInput;

    public RequestTaskHandler(ChatReply chatInterface, SessionName session, SessionsClient sessionsClient,
                              QueryInput queryInput) {
        this.chatInterface = chatInterface;
        this.session = session;
        this.sessionsClient = sessionsClient;
        this.queryInput = queryInput;
    }

    @Override
    protected DetectIntentResponse doInBackground(Void... voids) {
        try {
            DetectIntentRequest detectIntentRequest =
                    DetectIntentRequest.newBuilder()
                            .setSession(session.toString())
                            .setQueryInput(queryInput)
                            .build();
            return sessionsClient.detectIntent(detectIntentRequest);
        } catch (Exception e) {
            Log.d(TAG, "doInBackground: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(DetectIntentResponse response) {
        chatInterface.callback(response);
    }
}