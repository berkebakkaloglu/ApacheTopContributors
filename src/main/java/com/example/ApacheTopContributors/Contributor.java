package com.example.ApacheTopContributors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contributor {
    private String url = "";

    private int contributions = 0;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "url='" + url + '\'' +
                ", contributions=" + contributions +
                '}';
    }

}
