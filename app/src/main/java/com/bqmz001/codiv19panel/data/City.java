package com.bqmz001.codiv19panel.data;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class City {
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

}
