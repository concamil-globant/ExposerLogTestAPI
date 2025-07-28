package com.united.poc.lambda.ExposerLogTestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentRequest;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentResponse;

import java.util.function.Function;

@SpringBootApplication
public class ExposerLogTestApiApplication {

    private final ComprehendClient comprehendClient = ComprehendClient.create();

    public static void main(String[] args) {
        SpringApplication.run(ExposerLogTestApiApplication.class, args);
    }

    @Bean
    public Function<String, String> logAnalyzer() {
        return logEntry -> {
            String processedLog = logEntry.split("\n")[0]; // take the first line
            boolean isStacktrace = logEntry.contains("Exception") || logEntry.contains("at");

            DetectSentimentRequest request = DetectSentimentRequest.builder()
                    .text(processedLog)  // analyze error message
                    .languageCode("en")
                    .build();

            DetectSentimentResponse response = comprehendClient.detectSentiment(request);
            String sentiment = response.sentimentAsString();

            if (isStacktrace) {
                System.out.println("INFO: Stacktrace detected, sentiment may not be accurate.");
            }

            System.out.println("Log Level Detected: " +
                    (logEntry.contains("CRITICAL") ? "CRITICAL" :
                            logEntry.contains("ERROR") ? "ERROR" :
                                    logEntry.contains("WARNING") ? "WARNING" :
                                            "INFO") +
                    " | Sentiment: " + sentiment +
                    " | Message: " + processedLog
            );

            return "Sentiment: " + sentiment + " | Log Processed: " + processedLog;
        };
    }

}
