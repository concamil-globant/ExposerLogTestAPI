package com.united.poc.lambda.ExposerLogTestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class ExposerLogTestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExposerLogTestApiApplication.class, args);
	}

	@Bean
	public Function<String, String> logAnalyzer() {
		return logEntry -> {
			// log tracker
			if (logEntry.contains("ERROR")) {
				return "Critical Error Detected: " + logEntry;
			}
			return "Log processed: " + logEntry;
		};
	}
}
