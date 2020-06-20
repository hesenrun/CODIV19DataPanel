package com.bqmz001.codiv19panel.adapter;

import com.bqmz001.codiv19panel.data.City;
import com.bqmz001.codiv19panel.data.Province;
import com.bqmz001.codiv19panel.nodedata.CityNode;
import com.bqmz001.codiv19panel.nodedata.ProvinceNode;
import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TreeAdapter extends BaseNodeAdapter {

    public TreeAdapter(){
        super();
        addNodeProvider(new ProvinceNodeProvider());
        addNodeProvider(new CityNodeProvider());

    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
       BaseNode baseNode=list.get(i);
       if (baseNode instanceof ProvinceNode)
           return 0;
       else if (baseNode instanceof CityNode)
           return 1;
       return -1;
    }
}
