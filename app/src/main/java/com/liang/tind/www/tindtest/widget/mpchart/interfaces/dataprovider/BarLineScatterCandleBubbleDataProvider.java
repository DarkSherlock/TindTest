package com.liang.tind.www.tindtest.widget.mpchart.interfaces.dataprovider;


import com.liang.tind.www.tindtest.widget.mpchart.component.YAxis;
import com.liang.tind.www.tindtest.widget.mpchart.data.BarLineScatterCandleBubbleData;
import com.liang.tind.www.tindtest.widget.mpchart.utils.Transformer;


public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(YAxis.AxisDependency axis);
    boolean isInverted(YAxis.AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
