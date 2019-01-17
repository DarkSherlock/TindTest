package com.liang.tind.www.tindtest.widget.mpchart.interfaces.dataprovider;


import com.liang.tind.www.tindtest.widget.mpchart.component.YAxis;
import com.liang.tind.www.tindtest.widget.mpchart.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
