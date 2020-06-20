package com.bqmz001.codiv19panel.data;

import java.util.List;

public class DataSource {
    private Country country;
    private DataSourceUpdateTime dataSourceUpdateTime;
    private List<TrendDetail> trend;
    private List<Province> provinceArray;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public DataSourceUpdateTime getDataSourceUpdateTime() {
        return dataSourceUpdateTime;
    }

    public void setDataSourceUpdateTime(DataSourceUpdateTime dataSourceUpdateTime) {
        this.dataSourceUpdateTime = dataSourceUpdateTime;
    }

    public List<TrendDetail> getTrend() {
        return trend;
    }

    public void setTrend(List<TrendDetail> trend) {
        this.trend = trend;
    }

    public List<Province> getProvinceArray() {
        return provinceArray;
    }

    public void setProvinceArray(List<Province> provinceArray) {
        this.provinceArray = provinceArray;
    }
}
