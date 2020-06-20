package com.bqmz001.codiv19panel.nodedata;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProvinceNode extends BaseExpandNode {
    private String provinceName;
    private int provinceTotalCured;
    private int provinceTotalDeath;
    private int provinceTotalConfirmed;
    private List<BaseNode> cityNode;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceTotalCured() {
        return provinceTotalCured;
    }

    public void setProvinceTotalCured(int provinceTotalCured) {
        this.provinceTotalCured = provinceTotalCured;
    }

    public int getProvinceTotalDeath() {
        return provinceTotalDeath;
    }

    public void setProvinceTotalDeath(int provinceTotalDeath) {
        this.provinceTotalDeath = provinceTotalDeath;
    }

    public int getProvinceTotalConfirmed() {
        return provinceTotalConfirmed;
    }

    public void setProvinceTotalConfirmed(int provinceTotalConfirmed) {
        this.provinceTotalConfirmed = provinceTotalConfirmed;
    }

    public List<BaseNode> getCityNode() {
        return cityNode;
    }

    public void setCityNode(List<BaseNode> cityNode) {
        this.cityNode = cityNode;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return cityNode;
    }
}
