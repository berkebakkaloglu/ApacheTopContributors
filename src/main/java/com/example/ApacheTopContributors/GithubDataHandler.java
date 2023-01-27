package com.example.ApacheTopContributors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GithubDataHandler {
    private static final String ORG = "apache";
    private static final String[] REPOS = {"echarts", "superset", "dubbo", "spark", "airflow"};
    private static final String fileName = "output";

    public static void printFile(String output) {
        File file = new File(fileName + ".txt");

        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TotalUserData> getContributors(RestTemplate restTemplate) {
        List<TotalUserData> totalUserDataList = new ArrayList<>();

        for (String repo : REPOS) {
            String contributorsUrl = "https://api.github.com/repos/" + ORG + "/" + repo + "/contributors";
            ResponseEntity<Contributor[]> responseEntity = restTemplate.getForEntity(contributorsUrl, Contributor[].class);
            Contributor[] contributors = responseEntity.getBody();

            for (int i = 0; i < 10; i++) {
                TotalUserData totalUserData = new TotalUserData();
                Contributor currentContributor = contributors[i];

                totalUserData.setRepo(repo);
                totalUserData.setUrl(currentContributor.getUrl());
                totalUserData.setContributions(currentContributor.getContributions());

                totalUserDataList.add(totalUserData);
            }
        }

        return totalUserDataList;
    }

    public static void getUsers(RestTemplate restTemplate, List<TotalUserData> totalUserDataList) {
        for (TotalUserData totalUserData : totalUserDataList) {
            String usersUrl = totalUserData.getUrl();
            ResponseEntity<User> responseEntity = restTemplate.getForEntity(usersUrl, User.class);
            User currentUser = responseEntity.getBody();

            totalUserData.setUser(currentUser.getLogin());
            totalUserData.setLocation(currentUser.getLocation());
            totalUserData.setCompany(currentUser.getCompany());
        }
    }

    public static String generateOutput(List<TotalUserData> totalUserDataList) {
        StringBuilder output = new StringBuilder();

        for (TotalUserData totalUserData : totalUserDataList) {
            output.append("repo: ")
                    .append(totalUserData.getRepo())
                    .append(", user: ")
                    .append(totalUserData.getUser())
                    .append(", location: ")
                    .append(totalUserData.getLocation())
                    .append(", company: ")
                    .append(totalUserData.getCompany())
                    .append(", contributions: ")
                    .append(totalUserData.getContributions())
                    .append(System.getProperty("line.separator"));
        }

        return output.toString();
    }
}
