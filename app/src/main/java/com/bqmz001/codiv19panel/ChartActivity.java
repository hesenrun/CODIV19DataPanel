package com.bqmz001.codiv19panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
    Spinner s_start, s_end;
    Button button;

    List<TrendDetail> trends;

    List<String> dates;

    List<TrendDetail> chooseTrends;

    int StartPosition = 0;
    int EndPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Intent intent = getIntent();
        json = intent.getStringExtra("trend");
        trends = new Gson().fromJson(json, new TypeToken<List<TrendDetail>>() {
        }.getType());

        dates = new ArrayList<>();
        for (TrendDetail trend : trends) {
            dates.add(trend.getDay());
        }

        StartPosition = trends.size() - 30;
        EndPosition = trends.size() - 1;


        chooseTrends = new ArrayList<>();
        for (int i = trends.size() - 30; i < trends.size(); i++) {
            chooseTrends.add(trends.get(i));
        }


        s_start = findViewById(R.id.spinner_startTime);
        s_end = findViewById(R.id.spinner_endTime);

        ArrayAdapter adapter_s = new ArrayAdapter(ChartActivity.this, R.layout.support_simple_spinner_dropdown_item, dates);
        adapter_s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        s_start.setAdapter(adapter_s);
        s_start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StartPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s_end.setAdapter(adapter_s);
        s_end.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EndPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s_start.setSelection(StartPosition);
        s_end.setSelection(EndPosition);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setClickable(false);
                if (StartPosition < EndPosition) {
                    chooseTrends.clear();
                    for (int i = StartPosition; i <= EndPosition; i++) {
                        chooseTrends.add(trends.get(i));
                    }
                    initChart();
                } else {
                    Toast.makeText(ChartActivity.this,"开始时间须小于结束时间，请重新选择",Toast.LENGTH_SHORT).show();
                    StartPosition = trends.size() - 30;
                    EndPosition = trends.size() - 1;
                    s_start.setSelection(StartPosition);
                    s_end.setSelection(EndPosition);
                    chooseTrends.clear();
                    for (int i = StartPosition; i <= EndPosition; i++) {
                        chooseTrends.add(trends.get(i));
                    }
                    initChart();
                }
                button.setClickable(true);
            }
        });

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

        for (int i = 0; i < chooseTrends.size(); i++) {
            axisXValues.add(new AxisValue(i).setLabel(chooseTrends.get(i).getDay()));
            sure_cnts.add(new PointValue(i, chooseTrends.get(i).getSure_cnt()));
            die_cnts.add(new PointValue(i, chooseTrends.get(i).getDie_cnt()));
            cure_cnts.add(new PointValue(i, chooseTrends.get(i).getCure_cnt()));
            doubt_cnts.add(new PointValue(i, chooseTrends.get(i).getDoubt_cnt()));
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
                    Toast.makeText(getApplicationContext(), chooseTrends.get(pointIndex).getDay() + " 累计确诊人数:" + chooseTrends.get(pointIndex).getSure_cnt(), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), chooseTrends.get(pointIndex).getDay() + " 累计死亡人数:" + chooseTrends.get(pointIndex).getDie_cnt(), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), chooseTrends.get(pointIndex).getDay() + " 累计治愈人数:" + chooseTrends.get(pointIndex).getCure_cnt(), Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(), chooseTrends.get(pointIndex).getDay() + " 当日疑似人数:" + chooseTrends.get(pointIndex).getDoubt_cnt(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
