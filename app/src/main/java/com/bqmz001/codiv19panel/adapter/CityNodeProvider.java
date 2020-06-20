package com.bqmz001.codiv19panel.adapter;

import com.bqmz001.codiv19panel.R;
import com.bqmz001.codiv19panel.nodedata.CityNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class CityNodeProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_citynode;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        CityNode cnode= (CityNode) baseNode;
        holder.setText(R.id.textView_cityName, cnode.getCityName())
                .setText(R.id.textView_cityConfirmed, cnode.getCityTotalConfirmed()+"")
                .setText(R.id.textView_cityCured, cnode.getCityTotalCured()+"")
                .setText(R.id.textView_cityDeath, cnode.getCityTotalDeath()+"");
    }
}
