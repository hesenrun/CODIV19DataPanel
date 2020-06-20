package com.bqmz001.codiv19panel.data;

//国字号疫情趋势
public class TrendDetail {
    //日期 年.月
    private String day;
    //累计确诊
    private int sure_cnt;
    //累计死亡
    private int die_cnt;
    //累计治愈
    private int cure_cnt;
    //当日疑似
    private int doubt_cnt;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getSure_cnt() {
        return sure_cnt;
    }

    public void setSure_cnt(int sure_cnt) {
        this.sure_cnt = sure_cnt;
    }

    public int getDie_cnt() {
        return die_cnt;
    }

    public void setDie_cnt(int die_cnt) {
        this.die_cnt = die_cnt;
    }

    public int getCure_cnt() {
        return cure_cnt;
    }

    public void setCure_cnt(int cure_cnt) {
        this.cure_cnt = cure_cnt;
    }

    public int getDoubt_cnt() {
        return doubt_cnt;
    }

    public void setDoubt_cnt(int doubt_cnt) {
        this.doubt_cnt = doubt_cnt;
    }
}
