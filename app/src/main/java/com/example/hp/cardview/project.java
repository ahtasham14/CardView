package com.example.hp.cardview;

public class project {

    public String projectName;
    public String platform;
    public String team;
    public String starting_date;
    public String ending_date;
    public String fund;
    public String cost;
    public String code_link;
    public String client;
    public String project_type;

    public project() {

    }

    public project(String projectName, String platform, String team, String starting_date, String ending_date, String fund, String cost, String code_link, String client, String project_type) {
        this.projectName = projectName;
        this.platform = platform;
        this.team = team;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
        this.fund = fund;
        this.cost = cost;
        this.code_link = code_link;
        this.client = client;
        this.project_type = project_type;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(String starting_date) {
        this.starting_date = starting_date;
    }

    public String getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(String ending_date) {
        this.ending_date = ending_date;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCode_link() {
        return code_link;
    }

    public void setCode_link(String code_link) {
        this.code_link = code_link;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    @Override
    public String toString() {
        return "project{" +
                "projectName='" + projectName + '\'' +
                ", platform='" + platform + '\'' +
                ", team='" + team + '\'' +
                ", starting_date='" + starting_date + '\'' +
                ", ending_date='" + ending_date + '\'' +
                ", fund='" + fund + '\'' +
                ", cost='" + cost + '\'' +
                ", code_link='" + code_link + '\'' +
                ", client='" + client + '\'' +
                ", project_type='" + project_type + '\'' +
                '}';
    }
}
