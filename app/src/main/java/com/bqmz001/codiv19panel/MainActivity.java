package com.bqmz001.codiv19panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bqmz001.codiv19panel.adapter.TreeAdapter;
import com.bqmz001.codiv19panel.data.City;
import com.bqmz001.codiv19panel.data.DataSource;
import com.bqmz001.codiv19panel.data.Province;
import com.bqmz001.codiv19panel.nodedata.CityNode;
import com.bqmz001.codiv19panel.nodedata.ProvinceNode;
import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel vm;
    TextView t_totalCured, t_totalDeath, t_totalConfirmed, t_totalDoubtful, t_incDoubtful, t_dataSource;
    List<BaseExpandNode> provinceNodes;
    RecyclerView recyclerView;
    TreeAdapter adapter;
    Gson gson;

    LoadingPopupView loadingPopupView;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();

        t_dataSource = findViewById(R.id.textView_dataSource);
        t_totalConfirmed = findViewById(R.id.textView_totalConfirmed);
        t_incDoubtful = findViewById(R.id.textView_incDoubtful);
        t_totalDoubtful = findViewById(R.id.textView_totalDoubtful);
        t_totalDeath = findViewById(R.id.textView_totalDeath);
        t_totalCured = findViewById(R.id.textView_totalCured);

        provinceNodes = new ArrayList<>();

        loadingPopupView = (LoadingPopupView) new XPopup.Builder(MainActivity.this)
                .asLoading("正在加载");


        vm = ViewModelProviders.of(this, new MainViewModelFactory()).get(MainViewModel.class);
        vm.status.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch (s) {
                    case "success":
                        loadingPopupView.dismiss();
                        Toast.makeText(MainActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                        break;
                    case "fail":
                        loadingPopupView.dismiss();
                        Toast.makeText(MainActivity.this, "加载失败，重新打开app试试", Toast.LENGTH_SHORT).show();
                        break;
                    case "loading":
                        loadingPopupView.show();
                        break;
                }

            }
        });

        vm.data.observe(this, new Observer<DataSource>() {
            @Override
            public void onChanged(DataSource dataSource) {
                t_dataSource.setText("来源：" + dataSource.getDataSourceUpdateTime().getDataSource() + "," + dataSource.getDataSourceUpdateTime().getUpdateTime());
                t_totalConfirmed.setText(dataSource.getCountry().getTotalConfirmed() + "");
                if (dataSource.getCountry().getIncDoubtful() != 0) {
                    t_incDoubtful.setText("较昨日 + " + dataSource.getCountry().getIncDoubtful());
                } else {
                    t_incDoubtful.setVisibility(View.GONE);
                }

                t_totalDoubtful.setText(dataSource.getCountry().getTotalDoubtful() + "");
                t_totalDeath.setText(dataSource.getCountry().getTotalDeath() + "");
                t_totalCured.setText(dataSource.getCountry().getTotalCured() + "");


                for (int i = 0; i < dataSource.getProvinceArray().size(); i++) {
                    ProvinceNode provinceNode = new ProvinceNode();
                    provinceNode.setProvinceName(dataSource.getProvinceArray().get(i).getChildStatistic());
                    provinceNode.setProvinceTotalDeath(dataSource.getProvinceArray().get(i).getTotalDeath());
                    provinceNode.setProvinceTotalCured(dataSource.getProvinceArray().get(i).getTotalCured());
                    provinceNode.setProvinceTotalConfirmed(dataSource.getProvinceArray().get(i).getTotalConfirmed());

                    List<BaseNode> cityNodes = new ArrayList<>();
                    for (int j = 0; j < dataSource.getProvinceArray().get(i).getCityArray().size(); j++) {
                        CityNode cityNode = new CityNode();
                        cityNode.setCityName(dataSource.getProvinceArray().get(i).getCityArray().get(j).getChildStatistic());
                        cityNode.setCityTotalConfirmed(dataSource.getProvinceArray().get(i).getCityArray().get(j).getTotalConfirmed());
                        cityNode.setCityTotalCured(dataSource.getProvinceArray().get(i).getCityArray().get(j).getTotalCured());
                        cityNode.setCityTotalDeath(dataSource.getProvinceArray().get(i).getCityArray().get(j).getTotalDeath());
                        cityNodes.add(cityNode);
                    }

                    provinceNode.setCityNode(cityNodes);
                    provinceNode.setExpanded(false);
                    provinceNodes.add(provinceNode);
                }

                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter = new TreeAdapter();
                adapter.setList(provinceNodes);
                recyclerView.setAdapter(adapter);

            }

        });
        vm.load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_chart:
                if (vm.status.getValue().equals("success")) {
                    String json = gson.toJson(vm.data.getValue().getTrend());
                    Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                    intent.putExtra("trend", json);
                    startActivity(intent);
                }
                break;
            case R.id.icon_about:
                Toast.makeText(MainActivity.this, "作者：bqmz001", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}