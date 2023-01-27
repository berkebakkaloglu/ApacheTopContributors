package com.example.ApacheTopContributors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.example.ApacheTopContributors.GithubDataHandler.*;

@SpringBootApplication
public class ApacheTopContributorsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApacheTopContributorsApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			List<TotalUserData> totalUserDataList = getContributors(restTemplate);
			getUsers(restTemplate, totalUserDataList);
			String output = generateOutput(totalUserDataList);

			printFile(output);

			System.exit(0);
		};
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}

