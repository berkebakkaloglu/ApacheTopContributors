package com.example.ApacheTopContributors;

public class TotalUserData {
    private String repo;
    private String user;
    private String location;
    private String company;
    private int contributions;
    private String url;

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "repo='" + repo + '\'' +
                ", user='" + user + '\'' +
                ", location='" + location + '\'' +
                ", company='" + company + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}
