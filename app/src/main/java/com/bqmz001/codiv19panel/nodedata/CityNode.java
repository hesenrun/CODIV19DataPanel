package com.bqmz001.codiv19panel.nodedata;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CityNode extends BaseNode {
    private String cityName;
    private int cityTotalCured;
    private int cityTotalDeath;
    private int cityTotalConfirmed;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityTotalCured() {
        return cityTotalCured;
    }

    public void setCityTotalCured(int cityTotalCured) {
        this.cityTotalCured = cityTotalCured;
    }

    public int getCityTotalDeath() {
        return cityTotalDeath;
    }

    public void setCityTotalDeath(int cityTotalDeath) {
        this.cityTotalDeath = cityTotalDeath;
    }

    public int getCityTotalConfirmed() {
        return cityTotalConfirmed;
    }

    public void setCityTotalConfirmed(int cityTotalConfirmed) {
        this.cityTotalConfirmed = cityTotalConfirmed;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
