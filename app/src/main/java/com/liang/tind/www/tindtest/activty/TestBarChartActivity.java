package com.liang.tind.www.tindtest.activty;

import android.util.Log;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.liang.tind.www.tindtest.widget.mpchart.BarChart;
import com.liang.tind.www.tindtest.widget.mpchart.component.XAxis;
import com.liang.tind.www.tindtest.widget.mpchart.data.BarData;
import com.liang.tind.www.tindtest.widget.mpchart.data.BarDataSet;
import com.liang.tind.www.tindtest.widget.mpchart.data.BarEntry;
import com.liang.tind.www.tindtest.widget.mpchart.formatter.DefaultAxisValueFormatter;
import com.liang.tind.www.tindtest.widget.mpchart.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

/**
 * desc
 * created by liangtiande
 * date 2018/11/20
 */
public class TestBarChartActivity extends BaseActivity   {
    private BarChart chart;

    protected final String[] months = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_bar_chart;
    }

    @Override
    protected void init() {
        chart = findViewById(R.id.chart);

        chart.getDescription().setEnabled(false);


        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
        //x
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(months.length);
        xAxis.setValueFormatter(new DefaultAxisValueFormatter(0){
            @Override
            public String getFormattedValue(float value) {
                Log.i(TAG, "getAxisLabel(TestBarChartActivity.java:64): "+value);
                return months[(int) value];
            }
        });

        //y
        chart.getAxisLeft().setDrawGridLines(true);
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisRight().setEnabled(false);

        // add a nice and smooth animation
        chart.animateY(1500);

        chart.getLegend().setEnabled(false);

        setData();
    }

    private void setData(){

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < months.length; i++) {
            float val = (float) (Math.random() * 10000);
            values.add(new BarEntry(i, val));
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
//            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setColors(getResources().getColor(android.R.color.holo_blue_light));


            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setBarWidth(0.85f);
            chart.setData(data);
            chart.setFitBars(false);
        }
        set1.setDrawValues(true);
    }

}
