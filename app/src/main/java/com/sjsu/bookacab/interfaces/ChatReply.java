package com.sjsu.bookacab.interfaces;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;

public interface ChatReply {

    void callback(DetectIntentResponse returnResponse);
}
