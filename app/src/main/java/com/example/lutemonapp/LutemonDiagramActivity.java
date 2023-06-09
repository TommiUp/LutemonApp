package com.example.lutemonapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class LutemonDiagramActivity extends AppCompatActivity {
    private LutemonStorage storage;
    private List<DataEntry> data;
    private Pie pie;
    private AnyChartView anyChartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lutemon_diagram);
        storage = LutemonStorage.getInstance();
        anyChartView = findViewById(R.id.chart_lutemon_stat);
        data = new ArrayList<>();
        pie = AnyChart.pie();
        for (Lutemon lutemon : storage.getLutemons("all")){
            data.add(new ValueDataEntry(lutemon.getName(), lutemon.getWins()));
        }
        pie.data(data);
        anyChartView.setChart(pie);
    }
}