package com.bqmz001.codiv19panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.bqmz001.codiv19panel.data.TrendDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class ChartActivity extends AppCompatActivity {
    ActionBar actionBar;
    String json;
    LineChartView lineChartView;

    List<TrendDetail> trends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Intent intent = getIntent();
        json = intent.getStringExtra("trend");
        trends = new Gson().fromJson(json, new TypeToken<List<TrendDetail>>() {
        }.getType());

        lineChartView = findViewById(R.id.lineChart);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        }

        initChart();

    }

    private void initChart() {
        List<PointValue> sure_cnts = new ArrayList<>();
        List<PointValue> die_cnts = new ArrayList<>();
        List<PointValue> cure_cnts = new ArrayList<>();
        List<PointValue> doubt_cnts = new ArrayList<>();


        List<AxisValue> axisXValues = new ArrayList<>();

        for (int i = 0; i < trends.size(); i++) {
            axisXValues.add(new AxisValue(i).setLabel(trends.get(i).getDay()));
            sure_cnts.add(new PointValue(i, trends.get(i).getSure_cnt()));
            die_cnts.add(new PointValue(i, trends.get(i).getDie_cnt()));
            cure_cnts.add(new PointValue(i, trends.get(i).getCure_cnt()));
            doubt_cnts.add(new PointValue(i, trends.get(i).getDoubt_cnt()));
        }

        Axis axisX = new Axis();
        axisX.setHasLines(true);
        axisX.setValues(axisXValues);
        axisX.setName("时间");

        Axis axisY = new Axis();
        axisY.setHasTiltedLabels(true);
        axisY.setHasLines(true);
        axisY.setName("数量");

        Line sureLine = new Line(sure_cnts).setColor(getResources().getColor(R.color.red_500)).setCubic(false);
        Line dieLine = new Line(die_cnts).setColor(getResources().getColor(R.color.grey_500)).setCubic(false);
        Line cureLine = new Line(cure_cnts).setColor(getResources().getColor(R.color.green_500)).setCubic(false);
        Line doubtLine = new Line(doubt_cnts).setColor(getResources().getColor(R.color.orange_500)).setCubic(false);
        List<Line> lines = new ArrayList<>();
        lines.add(sureLine);
        lines.add(dieLine);
        lines.add(cureLine);
        lines.add(doubtLine);

        LineChartData data = new LineChartData();
        data.setLines(lines);
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        lineChartView.setLineChartData(data);
        lineChartView.setOnValueTouchListener(new ValueTouchListener());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            switch (lineIndex) {
                case 0:
                    Toast.makeText(getApplicationContext(), trends.get(pointIndex).getDay()+" 累计确诊人数:"+trends.get(pointIndex).getSure_cnt(), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), trends.get(pointIndex).getDay()+" 累计死亡人数:"+trends.get(pointIndex).getDie_cnt(), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), trends.get(pointIndex).getDay()+" 累计治愈人数:"+trends.get(pointIndex).getCure_cnt(), Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(), trends.get(pointIndex).getDay()+" 当日疑似人数:"+trends.get(pointIndex).getDoubt_cnt(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}