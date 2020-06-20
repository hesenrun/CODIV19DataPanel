package com.bqmz001.codiv19panel.data;


//国字号
public class Country {
    //累计治愈
    private int totalCured;
    //累计死亡
    private int totalDeath;
    //废的
    private int totalIncrease;
    //较昨日疑似增加数
    private int incDoubtful;
    //国家和地区
    private String childStatistic;
    //全国疑似
    private int totalDoubtful;
    //时间
    private String time;
    //累计确诊
    private int totalConfirmed;

    public int getTotalCured() {
        return totalCured;
    }

    public void setTotalCured(int totalCured) {
        this.totalCured = totalCured;
    }

    public int getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(int totalDeath) {
        this.totalDeath = totalDeath;
    }

    public int getTotalIncrease() {
        return totalIncrease;
    }

    public void setTotalIncrease(int totalIncrease) {
        this.totalIncrease = totalIncrease;
    }

    public int getIncDoubtful() {
        return incDoubtful;
    }

    public void setIncDoubtful(int incDoubtful) {
        this.incDoubtful = incDoubtful;
    }

    public String getChildStatistic() {
        return childStatistic;
    }

    public void setChildStatistic(String childStatistic) {
        this.childStatistic = childStatistic;
    }

    public int getTotalDoubtful() {
        return totalDoubtful;
    }

    public void setTotalDoubtful(int totalDoubtful) {
        this.totalDoubtful = totalDoubtful;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }
}
