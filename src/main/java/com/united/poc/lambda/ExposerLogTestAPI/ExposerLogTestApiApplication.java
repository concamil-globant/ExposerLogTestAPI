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
			DetectSentimentRequest request = DetectSentimentRequest.builder()
					.text(logEntry)
					.languageCode("en")
					.build();

			DetectSentimentResponse response = comprehendClient.detectSentiment(request);
			String sentiment = response.sentimentAsString();

			// explicit aws Comprehend log
			System.out.println("Comprehend Sentiment Detected: " + sentiment);

			if (logEntry.contains("CRITICAL")) {
				return "CRITICAL Error Detected [" + sentiment + "]: " + logEntry;
			} else if (logEntry.contains("ERROR")) {
				return "Error Detected [" + sentiment + "]: " + logEntry;
			} else if (logEntry.contains("WARNING")) {
				return "Warning Detected [" + sentiment + "]: " + logEntry;
			}
			return "Log processed successfully [" + sentiment + "]: " + logEntry;
		};
	}

}
