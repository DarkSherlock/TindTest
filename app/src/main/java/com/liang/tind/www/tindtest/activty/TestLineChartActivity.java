package com.liang.tind.www.tindtest.activty;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.liang.tind.www.tindtest.widget.mpchart.LineChart;
import com.liang.tind.www.tindtest.widget.mpchart.component.Legend;
import com.liang.tind.www.tindtest.widget.mpchart.component.XAxis;
import com.liang.tind.www.tindtest.widget.mpchart.component.YAxis;
import com.liang.tind.www.tindtest.widget.mpchart.data.Entry;
import com.liang.tind.www.tindtest.widget.mpchart.data.LineData;
import com.liang.tind.www.tindtest.widget.mpchart.data.LineDataSet;
import com.liang.tind.www.tindtest.widget.mpchart.formatter.IFillFormatter;
import com.liang.tind.www.tindtest.widget.mpchart.highlight.Highlight;
import com.liang.tind.www.tindtest.widget.mpchart.interfaces.dataprovider.LineDataProvider;
import com.liang.tind.www.tindtest.widget.mpchart.interfaces.datasets.ILineDataSet;
import com.liang.tind.www.tindtest.widget.mpchart.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * desc
 * created by liangtiande
 * date 2018/11/20
 */
public class TestLineChartActivity extends BaseActivity implements OnChartValueSelectedListener {
    private LineChart chart;
//    protected Typeface tfRegular;
//    protected Typeface tfLight;

    protected final String[] months = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_line_chart;
    }

    @Override
    protected void init() {
//        GraphChartView chartView = findViewById(R.id.custom);
//        float[] xPoints = new float[]{
//                10, 50, 90, 130, 170, 200,240
//        };
//        float[] yPoints = new float[]{
//                50, 100, 150, 200, 250, 300,350
//        };
//        String[] texts = new String[]{
//                "text1","text2","text3","text4","text5","text6","text7"
//        };
//        chartView.updateGraphChart(ChartTypeDef.WEEK_DIVIDE, 1000, xPoints,yPoints,texts);



        {   // // Chart Style // //
            chart = findViewById(R.id.chart);

            // background color
            chart.setBackgroundColor(Color.WHITE);

            // disable description text
            chart.getDescription().setEnabled(false);

            // enable touch gestures
            chart.setTouchEnabled(true);

            // set listeners
            chart.setOnChartValueSelectedListener(this);
            chart.setDrawGridBackground(false);

            // create marker to display box when values are selected
//            MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//
//            // Set the marker to the chart
//            mv.setChartView(chart);
//            chart.setMarker(mv);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(true);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart.setPinchZoom(true);
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();

            // vertical grid lines
//            xAxis.enableGridDashedLine(10f, 10f, 0f);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawAxisLine(true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelCount(7,true);
//            xAxis.setValueFormatter(new DefaultAxisValueFormatter(0){
//                @Override
//                public String getAxisLabel(float value, AxisBase axis) {
//                    if (value!=xAxis.getAxisMaximum()){
//                        return getFormattedValue(value)+"       ·  ·  ·  · ";
//                    }else {
//                        return super.getAxisLabel(value,axis);
//                    }
//
////                    return super.getAxisLabel(value,axis);
//                }
//            });
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);
//            chart.getAxisLeft().setEnabled(false);
            chart.getAxisLeft().setDrawAxisLine(false);

            // horizontal grid lines
//            yAxis.enableGridDashedLine(10f, 10f, 0f);
            yAxis.setDrawGridLines(true);

            // axis range
            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(0f);
            yAxis.setPosition((YAxis.YAxisLabelPosition.OUTSIDE_CHART));
        }


        {   // // Create Limit Lines // //
//            LimitLine llXAxis = new LimitLine(9f, "Index 10");
//            llXAxis.setLineWidth(4f);
//            llXAxis.enableDashedLine(10f, 10f, 0f);
//            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//            llXAxis.setTextSize(10f);
//            llXAxis.setTypeface(tfRegular);

//            LimitLine ll1 = new LimitLine(150f, "Upper Limit");
//            ll1.setLineWidth(4f);
//            ll1.enableDashedLine(10f, 10f, 0f);
//            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
//            ll1.setTextSize(10f);
//            ll1.setTypeface(tfRegular);

//            LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
//            ll2.setLineWidth(4f);
//            ll2.enableDashedLine(10f, 10f, 0f);
//            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//            ll2.setTextSize(10f);
//            ll2.setTypeface(tfRegular);

            // draw limit lines behind data instead of on top
//            yAxis.setDrawLimitLinesBehindData(true);
//            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
//            yAxis.addLimitLine(ll1);
//            yAxis.addLimitLine(ll2);
//            xAxis.addLimitLine(llXAxis);
        }

        // add data
//        seekBarX.setProgress(45);
//        seekBarY.setProgress(180);
        setData(45, 180);

        // draw points over time
        chart.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.NONE);
    }


    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range);
            values.add(new Entry(i, val, getResources().getDrawable(R.mipmap.star)));
        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "");

            set1.setDrawIcons(false);

            // draw dashed line
//            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(getResources().getColor(android.R.color.holo_green_light));
            set1.setCircleColor(getResources().getColor(android.R.color.holo_blue_light));

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setDrawValues(false);//禁止绘制折点数据值
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
//            if (Utils.getSDKInt() >= 18) {
//                // drawables only supported on api level 18 and above
//                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
//                set1.setFillDrawable(drawable);
//            } else {
//                set1.setFillColor(Color.BLACK);
//            }
            // TODO: 2018/11/23
//            set1.setFillColor(Color.BLACK);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOW HIGH", "low: " + chart.getLowestVisibleX() + ", high: " + chart.getHighestVisibleX());
        Log.i("MIN MAX", "xMin: " + chart.getXChartMin() + ", xMax: " + chart.getXChartMax() + ", yMin: " + chart.getYChartMin() + ", yMax: " + chart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}
