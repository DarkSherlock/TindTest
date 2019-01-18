package com.liang.tind.www.tindtest.widget.mpchart.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.liang.tind.www.tindtest.widget.mpchart.interfaces.datasets.IScatterDataSet;
import com.liang.tind.www.tindtest.widget.mpchart.utils.ViewPortHandler;


/**
 * Created by wajdic on 15/06/2016.
 * Created at Time 09:07
 */
public interface IShapeRenderer
{

    /**
     * Renders the provided ScatterDataSet with a shape.
     *
     * @param c               Canvas object for drawing the shape
     * @param dataSet         The DataSet to be drawn
     * @param viewPortHandler Contains information about the current state of the view
     * @param posX            Position to draw the shape at
     * @param posY            Position to draw the shape at
     * @param renderPaint     Paint object used for styling and drawing
     */
    void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler,
                     float posX, float posY, Paint renderPaint);
}