package com.bqmz001.codiv19panel.data;

import java.util.List;

//省级
public class Province {
    //累计治愈
    private int totalCured;
    //累计死亡
    private int totalDeath;
    //废的
    private int totalIncrease;
    //名字
    private String childStatistic;
    //废的
    private int totalDoubtful;
    //累计确诊
    private int totalConfirmed;
    //市级列表
    private List<City> cityArray;

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

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public List<City> getCityArray() {
        return cityArray;
    }

    public void setCityArray(List<City> cityArray) {
        this.cityArray = cityArray;
    }
}
