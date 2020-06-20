package com.bqmz001.codiv19panel.adapter;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;

import com.bqmz001.codiv19panel.R;
import com.bqmz001.codiv19panel.data.Province;
import com.bqmz001.codiv19panel.nodedata.ProvinceNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProvinceNodeProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_provincenode;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        ProvinceNode pnode = (ProvinceNode) baseNode;
        holder.setText(R.id.textView_provinceName, pnode.getProvinceName())
                .setText(R.id.textView_provinceConfirmed, pnode.getProvinceTotalConfirmed()+"")
                .setText(R.id.textView_provinceCured, pnode.getProvinceTotalCured()+"")
                .setText(R.id.textView_provinceDeath, pnode.getProvinceTotalDeath()+"");
    }


    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        getAdapter().expandOrCollapse(position);
    }
}
