package com.united.poc.lambda.ExposerLogTestAPI;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LogProcessor implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String logEntry, Context context) {
        if (logEntry.contains("ERROR")) {
            return "Critical Error Detected: " + logEntry;
        }
        return "Log processed successfully.";
    }
}
