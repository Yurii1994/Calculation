package com.project.myv.calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GraphView extends View implements View.OnTouchListener
{
    private Metrics metrics = new Metrics(getContext());
    private GraphView graphView = (GraphView)this;

    private Paint p1;
    private Paint p2;
    private Paint fontPaintAxis;
    private Paint fontPaintGrid;
    private Paint lineGraph;

    int fontSizeAxis = 28;
    int fontSizeGrid = 28;
    private float numberAxis = 0;
    private float widthNumberAxis;
    private float heightNumberAxis;
    Rect boundsAxis;

    private float scale = 1f;
    private float step = 1;
    private float STEP_REAL = step * scale;

    public void setIncreaseScale()
    {
        scale = scale * 2;
        stateButtonIncDec = true;
        invalidate();
    }

    public void setDecreaseScale()
    {
        scale = scale / 2;
        stateButtonIncDec = true;
        invalidate();
    }

    Canvas canvas;

    private String textToCalculationGraph;

    public void setTextToCalculationGraph(String text)
    {
        textToCalculationGraph = text;
    }

    public void setGraphReset ()
    {
        MOVE_X = false;
        MOVE_Y = false;
        pointsAxisCoordinateX = getPointAxisCoordinateX(HEIGHT, WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGIN_GRID_BOTTOM);
        pointsAxisCoordinateY = getPointAxisCoordinateY(HEIGHT, WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGIN_GRID_BOTTOM);
        pointsGridCoordinateXAfterAxis = getPointGridCoordinateXAfterAxis(HEIGHT, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_BOTTOM, MARGINS_LINE);
        pointsGridCoordinateXToAxis = getPointGridCoordinateXToAxis(HEIGHT, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_BOTTOM, MARGINS_LINE);
        pointsGridCoordinateYAfterAxis = getPointGridCoordinateYAfterAxis(WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGINS_LINE);
        pointsGridCoordinateYToAxis = getPointGridCoordinateYToAxis(WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGINS_LINE);
        NUMBER_X_HORIZONTAL = verticalAxisCoordinate -  widthNumberAxis / 2;
        NUMBER_Y_VERTICAL = horizontalAxisCoordinate + heightNumberAxis / 2;
        scale = 1f;
        stateStartGraphRes = true;
        invalidate();
    }

    public ArrayList<Float> getListPointsGraphInteger()
    {
        ArrayList<Float> result = new ArrayList<>();
        ArrayList<Float> y = getListY(listFullAxisXInteger);
        for (int i = 0; i < listFullAxisXInteger.size(); i++)
        {
            result.add(listFullAxisXInteger.get(i));
            result.add(y.get(i));
        }
        return result;
    }

    private float[] pointsFrame;
    private float[] pointsAxisCoordinateX;
    private float[] pointsAxisCoordinateY;
    private float[] pointsGridCoordinateXAfterAxis;
    private float[] pointsGridCoordinateXToAxis;
    private float[] pointsGridCoordinateYAfterAxis;
    private float[] pointsGridCoordinateYToAxis;
    private float[] pointsGridCoordinateXAfterAxisFull;
    private float[] pointsGridCoordinateXToAxisFull;
    private float[] pointsGridCoordinateYAfterAxisFull;
    private float[] pointsGridCoordinateYToAxisFull;

    private int HEIGHT;
    private int WIDTH;
    private int WIDTH_LINE = metrics.pxFromDp(1f);
    private int MARGINS_LINE = metrics.pxFromDp(25);
    private int MARGIN_GRID_LEFT_TOP = metrics.pxFromDp(35);
    private int MARGIN_GRID_RIGHT = metrics.pxFromDp(10);
    private int MARGIN_GRID_BOTTOM = metrics.pxFromDp(10);
    private int NUMBER_WIDTH = MARGINS_LINE - MARGINS_LINE / 3;

    public void setMarginBottom(int bottom)
    {
        MARGIN_GRID_BOTTOM = bottom;
    }

    public void setMarginRight(int right)
    {
        MARGIN_GRID_RIGHT = right;
    }

    public GraphView(Context context)
    {
        super(context);

        Typeface faceLight = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto-Light.ttf");

        p1 = new Paint();
        p2 = new Paint();
        lineGraph = new Paint();

        fontPaintAxis = new Paint(Paint.ANTI_ALIAS_FLAG);
        int size = getTextSize(roundNumber(numberAxis), NUMBER_WIDTH, fontSizeAxis);
        fontPaintAxis.setTextSize(fontSizeAxis);
        if (size <= fontSizeAxis)
        {
            fontPaintAxis.setTextSize(size);
        }
        fontPaintAxis.setStyle(Paint.Style.STROKE);
        fontPaintAxis.setTypeface(faceLight);

        fontPaintGrid = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaintGrid.setTextSize(fontSizeGrid);
        fontPaintGrid.setStyle(Paint.Style.STROKE);
        fontPaintGrid.setTypeface(faceLight);

        widthNumberAxis = fontPaintAxis.measureText(roundNumber(numberAxis));
        boundsAxis = new Rect();
        fontPaintAxis.getTextBounds(roundNumber(numberAxis), 0, 1, boundsAxis);
        heightNumberAxis = boundsAxis.height();

        NUMBER_X_VERTICAL = MARGIN_GRID_LEFT_TOP / 2 - widthNumberAxis / 2;
        NUMBER_Y_HORIZONTAL = MARGIN_GRID_LEFT_TOP / 2 + heightNumberAxis / 2;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.WIDTH = w;
        this.HEIGHT = h;
        pointsFrame = getPointFrame(HEIGHT, WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGIN_GRID_BOTTOM);
        pointsAxisCoordinateX = getPointAxisCoordinateX(HEIGHT, WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGIN_GRID_BOTTOM);
        pointsAxisCoordinateY = getPointAxisCoordinateY(HEIGHT, WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGIN_GRID_BOTTOM);
        pointsGridCoordinateXAfterAxis = getPointGridCoordinateXAfterAxis(HEIGHT, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_BOTTOM, MARGINS_LINE);
        pointsGridCoordinateXToAxis = getPointGridCoordinateXToAxis(HEIGHT, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_BOTTOM, MARGINS_LINE);
        pointsGridCoordinateYAfterAxis = getPointGridCoordinateYAfterAxis(WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGINS_LINE);
        pointsGridCoordinateYToAxis = getPointGridCoordinateYToAxis(WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGINS_LINE);
        NUMBER_X_HORIZONTAL = verticalAxisCoordinate -  widthNumberAxis / 2;
        NUMBER_Y_VERTICAL = horizontalAxisCoordinate + heightNumberAxis / 2;

        super.onSizeChanged(w, h, oldw, oldh);
    }

    private boolean MOVE_X = false;
    private boolean MOVE_Y = false;

    private  boolean stateZeroToX;
    private  boolean stateZeroAfterX;
    private boolean stateX;
    private boolean stateButtonIncDec;

    @Override
    protected void onDraw(Canvas canvas)
    {
        this.canvas = canvas;
        stateZeroToX = false;
        stateZeroAfterX = false;
        STEP_REAL = step * scale;
        stepPoint = STEP_REAL / (MARGINS_LINE / (WIDTH_LINE * 3));

        canvas.drawARGB(255, 255, 255, 255);

        p1.setColor(Color.parseColor("#cccccc"));
        p1.setStrokeWidth(WIDTH_LINE * 2);

        p2.setColor(Color.parseColor("#cccccc"));
        p2.setStrokeWidth((int) (WIDTH_LINE * 0.5f));

        canvas.drawLines(pointsFrame, p1);

        if (!getStateAxisCoordinateLessX(pointsAxisCoordinateX, minGridX, maxGridX))
        {
            canvas.drawLines(pointsAxisCoordinateX, p1);
        }

        if (!getStateAxisCoordinateLessY(pointsAxisCoordinateY, minGridY, maxGridY))
        {
            canvas.drawLines(pointsAxisCoordinateY, p1);
        }

        pointsGridCoordinateXAfterAxis = getPointGridCoordinateXAfterAxisReal(pointsGridCoordinateXAfterAxis);
        canvas.drawLines(pointsGridCoordinateXAfterAxis, p2);

        pointsGridCoordinateXToAxis = getPointGridCoordinateXToAxisReal(pointsGridCoordinateXToAxis);
        canvas.drawLines(pointsGridCoordinateXToAxis, p2);

        pointsGridCoordinateYAfterAxis = getPointGridCoordinateYAfterAxisReal(pointsGridCoordinateYAfterAxis);
        canvas.drawLines(pointsGridCoordinateYAfterAxis, p2);

        pointsGridCoordinateYToAxis = getPointGridCoordinateYToAxisReal(pointsGridCoordinateYToAxis);
        canvas.drawLines(pointsGridCoordinateYToAxis, p2);

        if (NUMBER_X_HORIZONTAL + widthNumberAxis / 2 >= minGridX & NUMBER_X_HORIZONTAL + widthNumberAxis / 2 <= maxGridX)
        {
            canvas.drawText(roundNumber(numberAxis), NUMBER_X_HORIZONTAL, NUMBER_Y_HORIZONTAL, fontPaintAxis);
            stateX = true;
        }
        else
        {
            stateX = false;
        }

        if (NUMBER_Y_VERTICAL - heightNumberAxis / 2 >= minGridY & NUMBER_Y_VERTICAL - heightNumberAxis  / 2 <= maxGridY)
        {
            canvas.drawText(roundNumber(numberAxis), NUMBER_X_VERTICAL, NUMBER_Y_VERTICAL, fontPaintAxis);
        }

        if (stateButtonIncDec)
        {
            listAfterAxisX = getDrawNumberAfterAxisX(canvas, STEP_REAL, pointsGridCoordinateXAfterAxis, pointsGridCoordinateXAfterAxisFull, MOVE_X, true);
            listToAxisX = getDrawNumberToAxisX(canvas, STEP_REAL, pointsGridCoordinateXToAxis, pointsGridCoordinateXToAxisFull, MOVE_X, true);
            getDrawNumberAfterAxisY(canvas, STEP_REAL, pointsGridCoordinateYAfterAxis, pointsGridCoordinateYAfterAxisFull, MOVE_Y);
            getDrawNumberToAxisY(canvas, STEP_REAL, pointsGridCoordinateYToAxis, pointsGridCoordinateYToAxisFull, MOVE_Y);
            listAxisX = getListAxisX(listToAxisX, listAfterAxisX, stateX);

            listFullAxisX = getListFullAxis(listToAxisX, listAfterAxisX, listAxisX);
            listFullAxisX = getListAxisFullSorted(listFullAxisX);
            listFullAxisXAdditionally = getListFullAxisAdditionally(listFullAxisX);
            listFullAxisXAdditionally = getListAxisFullSorted(listFullAxisXAdditionally);

            listAxisToX = getListAxisToX(listFullAxisXAdditionally);
            listAxisAfterX = getListAxisAfterX(listFullAxisXAdditionally);

            listAxisAdditionallyToX = getListAxisAdditionallyToX(listAxisToX, stepPoint, stateZeroToX);
            listAxisAdditionallyAfterX = getListAxisAdditionallyAfterX(listAxisAfterX, stepPoint, stateZeroAfterX);
            listAxisAdditionallyFullX = getListAxisAdditionallyFullX(listAxisAdditionallyToX, listAxisAdditionallyAfterX, stateX);
            listAxisAdditionallyFullX = getListAxisFullSorted(listAxisAdditionallyFullX);
            listAxisAdditionallyFullY = getListY(listAxisAdditionallyFullX);

            listAdditionallyXY =  getListXY(listAxisAdditionallyFullX, listAxisAdditionallyFullY);
            listAdditionallyXY = getListXYToDrawLines(listAdditionallyXY);
            getGraph(canvas, listAdditionallyXY);
            stateButtonIncDec = false;
            stateStartGraphRes = false;

            listFullAxisXInteger = getListFullAxisXInteger(listAfterAxisX, listToAxisX, listAxisX);
            listFullAxisXInteger = getListAxisFullSorted(listFullAxisXInteger);
        }
        else
        if (stateStartGraph & (!MOVE_X | !MOVE_Y) & !onTouch)
        {
            listAfterAxisX = getDrawNumberAfterAxisX(canvas, STEP_REAL, pointsGridCoordinateXAfterAxis, pointsGridCoordinateXAfterAxisFull, MOVE_X, true);
            listToAxisX = getDrawNumberToAxisX(canvas, STEP_REAL, pointsGridCoordinateXToAxis, pointsGridCoordinateXToAxisFull, MOVE_X, true);
            getDrawNumberAfterAxisY(canvas, STEP_REAL, pointsGridCoordinateYAfterAxis, pointsGridCoordinateYAfterAxisFull, MOVE_Y);
            getDrawNumberToAxisY(canvas, STEP_REAL, pointsGridCoordinateYToAxis, pointsGridCoordinateYToAxisFull, MOVE_Y);
            listAxisX = getListAxisX(listToAxisX, listAfterAxisX, stateX);

            getGraph(canvas, listAdditionallyXYRes);

            listFullAxisXInteger = getListFullAxisXInteger(listAfterAxisX, listToAxisX, listAxisX);
            listFullAxisXInteger = getListAxisFullSorted(listFullAxisXInteger);

        }
        else
        if (MOVE_X | MOVE_Y | onTouch)
        {
            listAfterAxisX = getDrawNumberAfterAxisX(canvas, STEP_REAL, pointsGridCoordinateXAfterAxis, pointsGridCoordinateXAfterAxisFull, MOVE_X, true);
            listToAxisX = getDrawNumberToAxisX(canvas, STEP_REAL, pointsGridCoordinateXToAxis, pointsGridCoordinateXToAxisFull, MOVE_X, true);
            getDrawNumberAfterAxisY(canvas, STEP_REAL, pointsGridCoordinateYAfterAxis, pointsGridCoordinateYAfterAxisFull, MOVE_Y);
            getDrawNumberToAxisY(canvas, STEP_REAL, pointsGridCoordinateYToAxis, pointsGridCoordinateYToAxisFull, MOVE_Y);

            listAxisX = getListAxisX(listToAxisX, listAfterAxisX, stateX);

            listFullAxisX = getListFullAxis(listToAxisX, listAfterAxisX, listAxisX);
            listFullAxisX = getListAxisFullSorted(listFullAxisX);
            listFullAxisXAdditionally = getListFullAxisAdditionally(listFullAxisX);
            listFullAxisXAdditionally = getListAxisFullSorted(listFullAxisXAdditionally);

            listAxisToX = getListAxisToX(listFullAxisXAdditionally);
            listAxisAfterX = getListAxisAfterX(listFullAxisXAdditionally);

            listAdditionallyXY =  getListXY(listAxisAdditionallyFullX, listAxisAdditionallyFullY);
            listAdditionallyXY = getListXYToDrawLines(listAdditionallyXY);

            if (stateStartGraphRes)
            {
                getGraph(canvas, listAdditionallyXYRes);
            }
            else
            {
                getGraph(canvas, listAdditionallyXY);
            }

            listFullAxisXInteger = getListFullAxisXInteger(listAfterAxisX, listToAxisX, listAxisX);
            listFullAxisXInteger = getListAxisFullSorted(listFullAxisXInteger);
        }
        else
        {
            stateStartGraph = true;

            listAfterAxisX = getDrawNumberAfterAxisX(canvas, STEP_REAL, pointsGridCoordinateXAfterAxis, pointsGridCoordinateXAfterAxisFull, false, true);
            listToAxisX = getDrawNumberToAxisX(canvas, STEP_REAL, pointsGridCoordinateXToAxis, pointsGridCoordinateXToAxisFull, MOVE_X, true);
            getDrawNumberAfterAxisY(canvas, STEP_REAL, pointsGridCoordinateYAfterAxis, pointsGridCoordinateYAfterAxisFull, MOVE_Y);
            getDrawNumberToAxisY(canvas, STEP_REAL, pointsGridCoordinateYToAxis, pointsGridCoordinateYToAxisFull, MOVE_Y);

            listAxisX = getListAxisX(listToAxisX, listAfterAxisX, stateX);

            listFullAxisX = getListFullAxis(listToAxisX, listAfterAxisX, listAxisX);
            listFullAxisX = getListAxisFullSorted(listFullAxisX);
            listFullAxisXAdditionally = getListFullAxisAdditionally(listFullAxisX);
            listFullAxisXAdditionally  = getListAxisFullSorted(listFullAxisXAdditionally);

            listAxisToX = getListAxisToX(listFullAxisXAdditionally);
            listAxisAfterX = getListAxisAfterX(listFullAxisXAdditionally);

            listAxisAdditionallyToX = getListAxisAdditionallyToX(listAxisToX, stepPoint, stateZeroToX);
            listAxisAdditionallyAfterX = getListAxisAdditionallyAfterX(listAxisAfterX, stepPoint, stateZeroAfterX);
            listAxisAdditionallyFullX = getListAxisAdditionallyFullX(listAxisAdditionallyToX, listAxisAdditionallyAfterX, stateX);
            listAxisAdditionallyFullX = getListAxisFullSorted(listAxisAdditionallyFullX);
            listAxisAdditionallyFullY = getListY(listAxisAdditionallyFullX);

            listAdditionallyXY = getListXY(listAxisAdditionallyFullX, listAxisAdditionallyFullY);
            listAdditionallyXY = getListXYToDrawLines(listAdditionallyXY);
            getGraph(canvas, listAdditionallyXY);

            listAdditionallyXYRes = listAdditionallyXY;
            listFullAxisXInteger = getListFullAxisXInteger(listAfterAxisX, listToAxisX, listAxisX);
            listFullAxisXInteger = getListAxisFullSorted(listFullAxisXInteger);


        }
        onTouch = false;
    }

    private boolean onTouch;
    private boolean stateStartGraph;
    private boolean stateStartGraphRes;
    private ArrayList<Float> listAdditionallyXYRes;

    private ArrayList<Float> getListFullAxisXInteger(ArrayList<Float> list1, ArrayList<Float> list2, ArrayList<Float> list3)
    {
        ArrayList<Float> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++)
        {
            list.add(list1.get(i));
        }
        for (int i = 0; i < list2.size(); i++)
        {
            list.add(list2.get(i));
        }
        for (int i = 0; i < list3.size(); i++)
        {
            list.add(list3.get(i));
        }
        return list;
    }

    public void getGraph(Canvas canvas, ArrayList<Float> listAdditionallyXY)
    {
        try
        {
            listRealXY = getListRealXY(listAdditionallyXY);

            lineGraph.setColor(Color.parseColor("#00BCD4"));
            lineGraph.setStrokeWidth(WIDTH_LINE * 2);

            float left = MARGIN_GRID_LEFT_TOP;
            float top = MARGIN_GRID_LEFT_TOP;
            float right = WIDTH - MARGIN_GRID_RIGHT;
            float bottom = HEIGHT - MARGIN_GRID_BOTTOM;

            canvas.clipRect(left, top, right, bottom);
            canvas.drawLines(listRealXY, lineGraph);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    ArrayList<Float> listAfterAxisX;
    ArrayList<Float> listToAxisX;
    ArrayList<Float> listAxisX;
    ArrayList<Float> listFullAxisXAdditionally;
    ArrayList<Float> listFullAxisX;
    private ArrayList<Float> listAxisToX;
    private ArrayList<Float> listAxisAfterX;
    private float stepPoint;
    private ArrayList<Float> listAxisAdditionallyToX;
    private ArrayList<Float> listAxisAdditionallyAfterX;
    private ArrayList<Float> listAxisAdditionallyFullX;
    private ArrayList<Float> listAxisAdditionallyFullY;
    ArrayList<Float> listAdditionallyXY;
    float[] listRealXY;
    private ArrayList<Float> listFullAxisXInteger;

    private ArrayList<Float> getListFullAxisAdditionally(ArrayList<Float> listFullAxisX)
    {
        ArrayList<Float> list = new ArrayList<>();
        float min = listFullAxisX.get(0);
        float max = listFullAxisX.get(listFullAxisX.size() - 1);
        for (int i = 0; i < listFullAxisX.size(); i++)
        {
            list.add(listFullAxisX.get(i));
        }
        list.add(max + STEP_REAL);
        list.add(min - STEP_REAL);
        return list;
    }


    Calculation calculation = new Calculation();

    private ArrayList<Float> getListY(ArrayList<Float> list)
    {
        ArrayList<Float> listRez = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            String textToCalculation = textToCalculationGraph.replace("X", "(" + list.get(i) + ")");
            double y = calculation.getCalculation(textToCalculation);
            listRez.add((float) y);
        }
        return listRez;
    }

    private ArrayList<Float> getListAxisAdditionallyFullX(ArrayList<Float> list1, ArrayList<Float> list2, boolean stateZero)
    {
        ArrayList<Float> listRez = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++)
        {
            listRez.add(list1.get(i));
        }
        if (stateZero)
        {
            listRez.add(0f);
        }
        for (int i = 0; i < list2.size(); i++)
        {
            listRez.add(list2.get(i));
        }
        return listRez;
    }

    private ArrayList<Float> getListAxisAdditionallyToX(ArrayList<Float> list, float step, boolean stateZeroToX)
    {
        ArrayList<Float> listRez = new ArrayList<>();
        if (list.size() > 0)
        {
            float min = list.get(0);
            float max = list.get(list.size() - 1);
            int count = (int) ((max - min) / step);
            if (count< 0f)
            {
                count = count * (-1);
            }
            float x = 0;
            if (!stateZeroToX)
            {
                x = max;
            }
            for (int i = 0; i < count; i++)
            {
                if (i == 0 & !stateZeroToX)
                {
                    listRez.add(max);
                }
                x = x - step;
                if (x > min)
                {
                    listRez.add(x);
                }
                else
                {
                    listRez.add(min);
                }
            }
        }
        return listRez;
    }

    private ArrayList<Float> getListAxisAdditionallyAfterX(ArrayList<Float> list, float step, boolean stateZeroAfterX)
    {
        ArrayList<Float> listRez = new ArrayList<>();
        if(list.size() > 0)
        {
            float max = list.get(list.size() - 1);
            float min = list.get(0);
            int count = (int) ((max - min) / step);
            if (count< 0f)
            {
                count = count * (-1);
            }
            float x = 0;
            if (!stateZeroAfterX)
            {
                x = min;
            }
            for (int i = 0; i < count; i++)
            {
                if (i == 0 & !stateZeroAfterX)
                {
                    listRez.add(min);
                }
                x = x + step;
                if (x < max)
                {
                    listRez.add(x);
                }
                else
                {
                    listRez.add(max);
                }
            }
        }
        return listRez;
    }

    private ArrayList<Float> getListAxisToX(ArrayList<Float> list)
    {
        ArrayList<Float> listRez = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i) <= 0f)
            {
                listRez.add(list.get(i));
            }
            if (list.get(i) == 0f)
            {
                stateZeroToX = true;
            }
        }
        return listRez;
    }

    private ArrayList<Float> getListAxisAfterX(ArrayList<Float> list)
    {
        ArrayList<Float> listRez = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i) >= 0f)
            {
                listRez.add(list.get(i));
            }
            if (list.get(i) == 0f)
            {
                stateZeroAfterX = true;
            }
        }
        return listRez;
    }

    private ArrayList<Float> getListXYToDrawLines(ArrayList<Float> list)
    {
        ArrayList<Float> listRez = new ArrayList<>();
        float x = 0;
        for (int i = 0; i < list.size(); i++)
        {
            if (i % 2 == 0)
            {
                if (i < 2 || i >= list.size() - 2)
                {
                    listRez.add(list.get(i));
                }
                else
                {
                    listRez.add(list.get(i));
                    x = list.get(i);
                }
            }
            else
            {
                if (i < 2  || i >= list.size() - 2)
                {
                    listRez.add(list.get(i));
                }
                else
                {
                    listRez.add(list.get(i));
                    listRez.add(x);
                    listRez.add(list.get(i));
                }
            }
        }
        return listRez;
    }

    private float[] getListRealXY(ArrayList<Float> listXY)
    {
        float[] list = new float[listXY.size()];
        for (int i = 0; i < listXY.size(); i++)
        {
            if (i % 2 == 0)
            {
                float x = listXY.get(i);
                x = x * (-1);
                x = verticalAxisCoordinate - (x / STEP_REAL * MARGINS_LINE);
                list[i] = x;
            }
            else
            {
                float y = listXY.get(i);
                y = horizontalAxisCoordinate - (y / STEP_REAL  * MARGINS_LINE);
                list[i] = y;
            }
        }
        return list;
    }

    private ArrayList<Float> getListXY(ArrayList<Float> listFullAxisX, ArrayList<Float> listY)
    {
        ArrayList<Float> list = new ArrayList<>();
        for (int i = 0; i < listFullAxisX.size(); i++)
        {
            float x = listFullAxisX.get(i);
            float y = listY.get(i);
            list.add(x);
            list.add(y);
        }
        return list;
    }

    private ArrayList<Float> getListAxisFullSorted(ArrayList<Float> list)
    {
        ArrayList<Float> listRes = new ArrayList<>();
        float min;
        int size = list.size();
        if (list.size() > 0)
        {
            for (int j = 0; j < size; j++)
            {
                min = list.get(0);
                int c = 0;
                for (int i = 0; i < list.size(); i++)
                {
                    min = Math.min(min, list.get(i));
                    if (min == list.get(i))
                    {
                        c = i;
                    }
                }
                listRes.add(min);
                list.remove(c);
            }
        }
        return listRes;
    }

    private ArrayList<Float> getListAxisX(ArrayList<Float> listToX, ArrayList<Float> listAfterX, boolean state)
    {
        ArrayList<Float> list = new ArrayList<>();
        if (state)
        {
            if (listAfterX.size() == 0)
            {
                list.add(0f);
            }
            else
            if (listToX.size() == 0)
            {
                list.add(0f);
            }
            else
            {
                list.add(0f);
            }
        }
        return list;
    }

    private ArrayList<Float> getListFullAxis(ArrayList<Float> listTo, ArrayList<Float> listAfter, ArrayList<Float> listAxis)
    {
        ArrayList<Float> list = new ArrayList<>();
        for (int i = 0; i < listTo.size(); i++)
        {
            list.add(listTo.get(i));
        }
        for (int i = 0; i < listAxis.size(); i++)
        {
            list.add(listAxis.get(i));
        }
        for (int i = 0; i < listAfter.size(); i++)
        {
            list.add(listAfter.get(i));
        }
        return list;
    }

    private float NUMBER_X_HORIZONTAL;
    private float NUMBER_Y_HORIZONTAL;
    private float NUMBER_X_VERTICAL;
    private float NUMBER_Y_VERTICAL;

    private float verticalAxisCoordinate;
    private float horizontalAxisCoordinate;

    private float widthAfterAxisX;
    private float widthToAxisX;
    private float heightAfterAxisY;
    private float heightToAxisY;

    private float minGridX;
    private float maxGridX;
    private float minGridY;
    private float maxGridY;

    private float xDown1;
    private float yDown1;
    private float xMove1;
    private float yMove1;

    private boolean pointer_up = false;
    private int countDown;

    private float getLengthLine(float x1, float y1, float x2, float y2)
    {
        return (float) (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    private float lengthLine;

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        invalidate();
        float xDown2;
        float yDown2;
        onTouch = true;
        int actionMask = event.getActionMasked();
        int pointerCount = event.getPointerCount();

        float x = event.getX(0);
        float y = event.getY(0);

        switch (actionMask)
        {
            case MotionEvent.ACTION_DOWN:
                xDown1 = x;
                yDown1 = y;
                xMove1 = 0;
                yMove1 = 0;
                countDown = pointerCount;

            case MotionEvent.ACTION_POINTER_DOWN:
                countDown = pointerCount;
                if (pointerCount > 1)
                {
                    xDown1 = event.getX(0);
                    yDown1 = event.getY(0);
                    xDown2 = event.getX(pointerCount - 1);
                    yDown2 = event.getY(pointerCount - 1);
                    lengthLine = getLengthLine(xDown1, yDown1, xDown2, yDown2);
                }
                break;

            case MotionEvent.ACTION_UP:

                listAxisAdditionallyToX = getListAxisAdditionallyToX(listAxisToX, stepPoint, stateZeroToX);
                listAxisAdditionallyAfterX = getListAxisAdditionallyAfterX(listAxisAfterX, stepPoint, stateZeroAfterX);
                listAxisAdditionallyFullX = getListAxisAdditionallyFullX(listAxisAdditionallyToX, listAxisAdditionallyAfterX, stateX);
                listAxisAdditionallyFullX = getListAxisFullSorted(listAxisAdditionallyFullX);
                listAxisAdditionallyFullY = getListY(listAxisAdditionallyFullX);
                stateStartGraphRes = false;

            case MotionEvent.ACTION_POINTER_UP:
                countDown = pointerCount - 1;
                pointer_up = true;
                break;

            case MotionEvent.ACTION_MOVE:
                if (countDown == 1)
                {
                    if (pointer_up)
                    {
                        for (int i = 0; i < pointerCount; i++)
                        {
                            x = event.getX(i);
                            y = event.getY(i);
                            xDown1 = x;
                            yDown1 = y;
                            xMove1 = 0;
                            yMove1 = 0;
                            pointer_up = false;
                        }
                    }
                    if (xDown1 != x)
                    {
                        MOVE_X = true;
                        for (int i = 0; i < pointsAxisCoordinateX.length; i++)
                        {
                            if (i % 2 == 0)
                            {
                                float a = (pointsAxisCoordinateX[i] - xMove1) + (x - xDown1);
                                pointsAxisCoordinateX[i] = a;

                                verticalAxisCoordinate = a;
                                widthAfterAxisX = WIDTH - verticalAxisCoordinate - MARGIN_GRID_RIGHT;
                                pointsGridCoordinateXAfterAxis = getPointGridCoordinateXAfterAxis(HEIGHT, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_BOTTOM, MARGINS_LINE);

                                widthToAxisX = verticalAxisCoordinate - MARGIN_GRID_LEFT_TOP;
                                pointsGridCoordinateXToAxis = getPointGridCoordinateXToAxis(HEIGHT, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_BOTTOM, MARGINS_LINE);
                            }
                        }
                        pointsGridCoordinateXAfterAxisFull = pointsGridCoordinateXAfterAxis;
                        pointsGridCoordinateXToAxisFull = pointsGridCoordinateXToAxis;
                        xMove1 = x - xDown1;
                    }
                    if (yDown1 != y)
                    {
                        MOVE_Y = true;
                        for (int i = 0; i < pointsAxisCoordinateY.length; i++)
                        {
                            if (i % 2 != 0)
                            {
                                float a = (pointsAxisCoordinateY[i] - yMove1) + (y - yDown1);
                                pointsAxisCoordinateY[i] = a;

                                horizontalAxisCoordinate = a;
                                heightAfterAxisY = horizontalAxisCoordinate - MARGIN_GRID_LEFT_TOP;
                                pointsGridCoordinateYAfterAxis = getPointGridCoordinateYAfterAxis(WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGINS_LINE);

                                heightToAxisY = HEIGHT - horizontalAxisCoordinate - MARGIN_GRID_BOTTOM;
                                pointsGridCoordinateYToAxis = getPointGridCoordinateYToAxis(WIDTH, MARGIN_GRID_LEFT_TOP, MARGIN_GRID_RIGHT, MARGINS_LINE);
                            }
                        }
                        pointsGridCoordinateYAfterAxisFull = pointsGridCoordinateYAfterAxis;
                        pointsGridCoordinateYToAxisFull = pointsGridCoordinateYToAxis;
                        yMove1 = y - yDown1;
                    }
                    NUMBER_X_HORIZONTAL = verticalAxisCoordinate -  widthNumberAxis / 2;
                    NUMBER_Y_VERTICAL = horizontalAxisCoordinate + heightNumberAxis / 2;
                }
                else
                if (pointerCount == 2)
                {
                    xDown1 = event.getX(0);
                    yDown1 = event.getY(0);
                    xDown2 = event.getX(pointerCount - 1);
                    yDown2 = event.getY(pointerCount - 1);
                    float lengthLine2 = getLengthLine(xDown1, yDown1, xDown2, yDown2);
                    if (lengthLine != lengthLine2)
                    {
                        if (lengthLine2 / lengthLine > 1.2f)
                        {
                            int z = (int) (lengthLine2 / lengthLine);
                            scale = scale / 2 * z;
                            lengthLine = lengthLine * 1.5f;
                        }
                        if (lengthLine / lengthLine2 > 1.2f)
                        {
                            int z = (int) (lengthLine / lengthLine2);
                            scale = scale * 2 * z;
                            lengthLine = lengthLine / 1.5f;
                        }
                    }
                }
                break;
        }
        return true;
    }

    public void setOnTouch()
    {
        graphView.setOnTouchListener(this);
    }

    private int getTextSize(String text, int numberWith, int sizeOld)
    {
        if (!text.contains("-"))
        {
            text = "-" + text;
        }
        int size;
        int width;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(sizeOld);
        paint.setStyle(Paint.Style.STROKE);
        width = (int) paint.measureText(text);
        if (width > numberWith)
        {
            for (int i = 0; ; i++)
            {
                size = sizeOld - i;
                if (size <= 1)
                {
                    break;
                }
                paint.setTextSize(size);
                width = (int) paint.measureText(text);
                if (width <= numberWith)
                {
                    break;
                }
            }
        }
        else
        {
            size = sizeOld;
        }
        return size;
    }

    private ArrayList<Float> getDrawNumberAfterAxisY(Canvas canvas, float step, float[] list, float[] listFull, boolean move)
    {
        ArrayList<Float> listNumber = new ArrayList<>();
        float[] listReal;
        int count = 0;
        if (!move)
        {
            listReal = list;
        }
        else
        {
            listReal = listFull;
        }
        ArrayList<Float> listArray = new ArrayList<>();
        for (int i = 1; i < listReal.length; i = i + 4)
        {
            if (listReal[i] <= maxGridY)
            {
                for (int j = i - 1; j < listReal.length; j++)
                {
                    listArray.add(listReal[j]);
                }
                break;
            }
            else
            {
                count++;
            }
        }
        float[] pointReal = new float[listArray.size()];
        for (int i = 0; i < listArray.size(); i++)
        {
            pointReal[i] = listArray.get(i);
        }
        float NUMBER_Y_VERTICAL_OLD = NUMBER_Y_VERTICAL;
        float NUMBER_X_VERTICAL_OLD = NUMBER_X_VERTICAL;
        float numberAxisOld = numberAxis;
        int position = -3;
        numberAxis = count * scale;
        for (int i = 0; i < pointReal.length / 4; i++)
        {
            position = position + 4;
            numberAxis = numberAxis + step;
            listNumber.add(numberAxis);

            int size = getTextSize(roundNumber(numberAxis), NUMBER_WIDTH, fontSizeGrid);
            if (size <= fontSizeGrid)
            {
                fontPaintGrid.setTextSize(size);
            }
            float widthNumberAxis = fontPaintGrid.measureText(roundNumber(numberAxis));
            NUMBER_X_VERTICAL = MARGIN_GRID_LEFT_TOP / 2 - widthNumberAxis / 2;

            fontPaintGrid.getTextBounds(roundNumber(numberAxis), 0, 1, boundsAxis);
            float heightNumber = boundsAxis.height();

            NUMBER_Y_VERTICAL = pointReal[position] + heightNumber / 2;
            canvas.drawText(roundNumber(numberAxis), NUMBER_X_VERTICAL, NUMBER_Y_VERTICAL, fontPaintGrid);
        }
        NUMBER_Y_VERTICAL = NUMBER_Y_VERTICAL_OLD;
        NUMBER_X_VERTICAL = NUMBER_X_VERTICAL_OLD;
        numberAxis = numberAxisOld;
        return listNumber;
    }

    private ArrayList<Float> getDrawNumberToAxisY(Canvas canvas, float step, float[] list, float[] listFull, boolean move)
    {
        ArrayList<Float> listNumber = new ArrayList<>();
        float[] listReal;
        int count = 0;
        if (!move)
        {
            listReal = list;
        }
        else
        {
            listReal = listFull;
        }
        ArrayList<Float> listArray = new ArrayList<>();
        for (int i = 1; i < listReal.length; i = i + 4)
        {
            if (listReal[i] >= minGridY)
            {
                for (int j = i - 1; j < listReal.length; j++)
                {
                    listArray.add(listReal[j]);
                }
                break;
            }
            else
            {
                count++;
            }
        }
        float[] pointReal = new float[listArray.size()];
        for (int i = 0; i < listArray.size(); i++)
        {
            pointReal[i] = listArray.get(i);
        }
        float NUMBER_Y_VERTICAL_OLD = NUMBER_Y_VERTICAL;
        float NUMBER_X_VERTICAL_OLD = NUMBER_X_VERTICAL;
        float numberAxisOld = numberAxis;
        int position = -3;
        numberAxis = count * (-1) * scale;
        for (int i = 0; i < pointReal.length / 4; i++)
        {
            position = position + 4;
            numberAxis = numberAxis - step;
            listNumber.add(numberAxis);

            int size = getTextSize(roundNumber(numberAxis), NUMBER_WIDTH, fontSizeGrid);
            if (size <= fontSizeGrid)
            {
                fontPaintGrid.setTextSize(size);
            }
            float widthNumberAxis = fontPaintGrid.measureText(roundNumber(numberAxis));
            NUMBER_X_VERTICAL = MARGIN_GRID_LEFT_TOP / 2 - widthNumberAxis / 2;

            fontPaintGrid.getTextBounds(roundNumber(numberAxis).replace("-", ""), 0, 1, boundsAxis);
            float heightNumber = boundsAxis.height();

            NUMBER_Y_VERTICAL = pointReal[position] + heightNumber / 2;
            canvas.drawText(roundNumber(numberAxis), NUMBER_X_VERTICAL, NUMBER_Y_VERTICAL, fontPaintGrid);
        }
        NUMBER_Y_VERTICAL = NUMBER_Y_VERTICAL_OLD;
        NUMBER_X_VERTICAL = NUMBER_X_VERTICAL_OLD;
        numberAxis = numberAxisOld;
        return listNumber;
    }

    private ArrayList<Float> getDrawNumberAfterAxisX(Canvas canvas, float step, float[] list, float[] listFull, boolean move, boolean paint)
    {
        ArrayList<Float> listNumber = new ArrayList<>();
        float[] listReal;
        int count = 0;
        if (!move)
        {
            listReal = list;
        }
        else
        {
            listReal = listFull;
        }
        ArrayList<Float> listArray = new ArrayList<>();
        for (int i = 0; i < listReal.length; i = i + 4)
        {
            if (listReal[i] >= minGridX)
            {
                for (int j = i; j < listReal.length; j++)
                {
                    listArray.add(listReal[j]);
                }
                break;
            }
            else
            {
                count++;
            }
        }
        float[] pointReal = new float[listArray.size()];
        for (int i = 0; i < listArray.size(); i++)
        {
            pointReal[i] = listArray.get(i);
        }
        float NUMBER_X_HORIZONTAL_OLD = NUMBER_X_HORIZONTAL;
        float NUMBER_Y_HORIZONTAL_OLD = NUMBER_Y_HORIZONTAL;
        float numberAxisOld = numberAxis;
        int position = -4;
        numberAxis = count * scale;
        for (int i = 0; i < pointReal.length / 4; i++)
        {
            position = position + 4;
            numberAxis = numberAxis + step;
            listNumber.add(numberAxis);

            if (paint)
            {
                int size = getTextSize(roundNumber(numberAxis), NUMBER_WIDTH, fontSizeGrid);
                if (size <= fontSizeGrid)
                {
                    fontPaintGrid.setTextSize(size);
                }
                float widthNumber = fontPaintGrid.measureText(roundNumber(numberAxis));
                NUMBER_X_HORIZONTAL = pointReal[position] - widthNumber / 2;

                fontPaintGrid.getTextBounds(roundNumber(numberAxis), 0, 1, boundsAxis);
                float heightNumber = boundsAxis.height();
                NUMBER_Y_HORIZONTAL = MARGIN_GRID_LEFT_TOP / 2 + heightNumber / 2;
                canvas.drawText(roundNumber(numberAxis), NUMBER_X_HORIZONTAL, NUMBER_Y_HORIZONTAL, fontPaintGrid);
            }
        }
        NUMBER_X_HORIZONTAL = NUMBER_X_HORIZONTAL_OLD;
        NUMBER_Y_HORIZONTAL = NUMBER_Y_HORIZONTAL_OLD;
        numberAxis = numberAxisOld;
        return listNumber;
    }

    private ArrayList<Float> getDrawNumberToAxisX(Canvas canvas, float step, float[] list, float[] listFull, boolean move, boolean paint)
    {
        ArrayList<Float> listNumber = new ArrayList<>();
        float[] listReal;
        int count = 0;
        if (!move)
        {
            listReal = list;
        }
        else
        {
            listReal = listFull;
        }
        ArrayList<Float> listArray = new ArrayList<>();
        for (int i = 0; i < listReal.length; i = i + 4)
        {
            if (listReal[i] <= maxGridX)
            {
                for (int j = i; j < listReal.length; j++)
                {
                    listArray.add(listReal[j]);
                }
                break;
            }
            else
            {
                count++;
            }
        }
        float[] pointReal = new float[listArray.size()];
        for (int i = 0; i < listArray.size(); i++)
        {
            pointReal[i] = listArray.get(i);
        }
        float NUMBER_X_HORIZONTAL_OLD = NUMBER_X_HORIZONTAL;
        float NUMBER_Y_HORIZONTAL_OLD = NUMBER_Y_HORIZONTAL;
        float numberAxisOld = numberAxis;
        int position = -4;
        numberAxis = count * (-1) * scale;
        for (int i = 0; i < pointReal.length / 4; i++)
        {
            position = position + 4;
            numberAxis = numberAxis - step;
            listNumber.add(numberAxis);

            if (paint)
            {
                int size = getTextSize(roundNumber(numberAxis), NUMBER_WIDTH, fontSizeGrid);
                if (size <= fontSizeGrid)
                {
                    fontPaintGrid.setTextSize(size);
                }
                float widthNumber = fontPaintGrid.measureText(roundNumber(numberAxis));
                NUMBER_X_HORIZONTAL = pointReal[position] - widthNumber / 2;

                fontPaintGrid.getTextBounds(roundNumber(numberAxis).replace("-", ""), 0, 1, boundsAxis);
                float heightNumber = boundsAxis.height();
                NUMBER_Y_HORIZONTAL = MARGIN_GRID_LEFT_TOP / 2 + heightNumber / 2;
                canvas.drawText(roundNumber(numberAxis), NUMBER_X_HORIZONTAL, NUMBER_Y_HORIZONTAL, fontPaintGrid);
            }
        }
        NUMBER_X_HORIZONTAL = NUMBER_X_HORIZONTAL_OLD;
        NUMBER_Y_HORIZONTAL = NUMBER_Y_HORIZONTAL_OLD;
        numberAxis = numberAxisOld;
        return listNumber;
    }

    private String roundNumber(float a)
    {
        String res;
        if (a % 1 == 0)
        {
            res = (int) (a) + "";
        }
        else
        {
            res = a + "";
        }
        return res;
    }

    private float[] getPointGridCoordinateYAfterAxis(int width, int margin_grid_left_top, int margin_grid_right, int margin_line)
    {
        if (heightAfterAxisY < 0)
        {
            heightAfterAxisY = 0;
        }
        int countHorizontalGridLines = (int) heightAfterAxisY / margin_line;
        float[] point = new  float[countHorizontalGridLines * 4];
        float a = horizontalAxisCoordinate;
        for (int i = 0; i < countHorizontalGridLines * 4; i++)
        {
            horizontalAxisCoordinate = horizontalAxisCoordinate - margin_line;
            point[i] = margin_grid_left_top;
            i = i + 1;
            point[i] = horizontalAxisCoordinate;
            i = i + 1;
            point[i] = width - margin_grid_right;
            i = i + 1;
            point[i] = horizontalAxisCoordinate;
        }
        horizontalAxisCoordinate = a;
        return point;
    }

    private float[] getPointGridCoordinateYAfterAxisReal(float[] point)
    {
        ArrayList<Float> list = new ArrayList<>();
        for (int i = 1; i < point.length; i = i + 4)
        {
            if (point[i] <= maxGridY)
            {
                for (int j = i - 1; j < point.length; j++)
                {
                    list.add(point[j]);
                }
                break;
            }
        }
        float[] pointNew = new float[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            pointNew[i] = list.get(i);
        }
        return pointNew;
    }

    private float[] getPointGridCoordinateYToAxis(int width, int margin_grid_left_top, int margin_grid_right, int margin_line)
    {
        if (heightToAxisY < 0)
        {
            heightToAxisY = 0;
        }
        int countHorizontalGridLines = (int) heightToAxisY / margin_line;
        float[] point = new  float[countHorizontalGridLines * 4];
        float a = horizontalAxisCoordinate;
        for (int i = 0; i < countHorizontalGridLines * 4; i++)
        {
            horizontalAxisCoordinate = horizontalAxisCoordinate + margin_line;
            point[i] = margin_grid_left_top;
            i = i + 1;
            point[i] = horizontalAxisCoordinate;
            i = i + 1;
            point[i] = width - margin_grid_right;
            i = i + 1;
            point[i] = horizontalAxisCoordinate;
        }
        horizontalAxisCoordinate = a;
        return point;
    }

    private float[] getPointGridCoordinateYToAxisReal(float[] point)
    {
        ArrayList<Float> list = new ArrayList<>();
        for (int i = 1; i < point.length; i = i + 4)
        {
            if (point[i] >= minGridY)
            {
                for (int j = i - 1; j < point.length; j++)
                {
                    list.add(point[j]);
                }
                break;
            }
        }
        float[] pointNew = new float[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            pointNew[i] = list.get(i);
        }
        return pointNew;
    }

    private float[] getPointGridCoordinateXAfterAxis(int height, int margin_grid_left_top, int margin_grid_bottom, int margin_line)
    {
        if (widthAfterAxisX < 0)
        {
            widthAfterAxisX = 0;
        }
        int countVerticalGridLines = (int) widthAfterAxisX / margin_line;
        float[] point = new  float[countVerticalGridLines * 4];
        float a = verticalAxisCoordinate;
        for (int i = 0; i < countVerticalGridLines * 4; i++)
        {
            verticalAxisCoordinate = verticalAxisCoordinate + margin_line;
            point[i] = verticalAxisCoordinate;
            i = i + 1;
            point[i] = margin_grid_left_top;
            i = i + 1;
            point[i] = verticalAxisCoordinate;
            i = i + 1;
            point[i] = height - margin_grid_bottom;
        }
        verticalAxisCoordinate = a;
        return point;
    }

    private float[] getPointGridCoordinateXAfterAxisReal(float[] point)
    {
        ArrayList<Float> list = new ArrayList<>();
        for (int i = 0; i < point.length; i = i + 4)
        {
            if (point[i] >= minGridX)
            {
                for (int j = i; j < point.length; j++)
                {
                    list.add(point[j]);
                }
                break;
            }
        }
        float[] pointNew = new float[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            pointNew[i] = list.get(i);
        }
        return pointNew;
    }

    private float[] getPointGridCoordinateXToAxis(int height, int margin_grid_left_top, int margin_grid_bottom, int margin_line)
    {
        if (widthToAxisX < 0)
        {
            widthToAxisX = 0;
        }
        int countVerticalGridLines = (int) widthToAxisX / margin_line;
        float[] point = new  float[countVerticalGridLines * 4];
        float a = verticalAxisCoordinate;
        for (int i = 0; i < countVerticalGridLines * 4; i++)
        {
            verticalAxisCoordinate = verticalAxisCoordinate - margin_line;
            point[i] = verticalAxisCoordinate;
            i = i + 1;
            point[i] = margin_grid_left_top;
            i = i + 1;
            point[i] = verticalAxisCoordinate;
            i = i + 1;
            point[i] = height - margin_grid_bottom;
        }
        verticalAxisCoordinate = a;
        return point;
    }

    private float[] getPointGridCoordinateXToAxisReal(float[] point)
    {
        ArrayList<Float> list = new ArrayList<>();
        for (int i = 0; i < point.length; i = i + 4)
        {
            if (point[i] <= maxGridX)
            {
                for (int j = i; j < point.length; j++)
                {
                    list.add(point[j]);
                }
                break;
            }
        }
        float[] pointNew = new float[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            pointNew[i] = list.get(i);
        }
        return pointNew;
    }

    private float[] getPointAxisCoordinateX(int height, int width, int margin_grid_left_top, int margin_grid_right, int margin_grid_bottom)
    {
        float[] point = new float[4];
        verticalAxisCoordinate = margin_grid_left_top + (width - margin_grid_left_top - margin_grid_right) / 2;
        widthAfterAxisX = width - verticalAxisCoordinate - margin_grid_right;
        widthToAxisX = verticalAxisCoordinate - margin_grid_left_top;
        point[0] = verticalAxisCoordinate;
        point[1] = margin_grid_left_top;
        point[2] = verticalAxisCoordinate;
        point[3] = height - margin_grid_bottom;
        return point;
    }

    private float[] getPointAxisCoordinateY(int height, int width, int margin_grid_left_top, int margin_grid_right, int margin_grid_bottom)
    {
        float[] point = new float[4];
        horizontalAxisCoordinate = margin_grid_left_top + (height - margin_grid_left_top - margin_grid_bottom) / 2;
        heightAfterAxisY = horizontalAxisCoordinate - margin_grid_left_top;
        heightToAxisY = height - horizontalAxisCoordinate - margin_grid_bottom;
        point[0] = margin_grid_left_top;
        point[1] = horizontalAxisCoordinate;
        point[2] = width - margin_grid_right;
        point[3] = horizontalAxisCoordinate;
        return point;
    }

    private boolean getStateAxisCoordinateLessX(float[] point, float min, float max)
    {
        boolean res = false;
        for (int i = 0; i < point.length; i++)
        {
            if (i % 2 == 0)
            {
                if (point[i] <= min || point[i] >= max)
                {
                    res = true;
                }
            }
        }
        return  res;
    }

    private boolean getStateAxisCoordinateLessY(float[] point, float min, float max)
    {
        boolean res = false;
        for (int i = 0; i < point.length; i++)
        {
            if (i % 2 != 0)
            {
                if (point[i] <= min || point[i] >= max)
                {
                    res = true;
                }
            }
        }
        return  res;
    }

    private float[] getPointFrame(int height, int width, int margin_grid_left_top, int margin_grid_right, int margin_grid_bottom)
    {
        float[] point = new float[16];
        for (int i = 0; i < 4; i++)
        {
            switch (i)
            {
                case 0:
                    point[0] = margin_grid_left_top - WIDTH_LINE;
                    point[1] = margin_grid_left_top;
                    point[2] = width - margin_grid_right + WIDTH_LINE;
                    point[3] = margin_grid_left_top;
                    break;

                case 1:
                    point[4] = margin_grid_left_top - WIDTH_LINE;
                    point[5] = height - margin_grid_bottom;
                    point[6] = width - margin_grid_right + WIDTH_LINE;
                    point[7] = height - margin_grid_bottom;
                    break;
                case 2:
                    point[8] = margin_grid_left_top;
                    point[9] = margin_grid_left_top;
                    point[10] = margin_grid_left_top;
                    point[11] = height - margin_grid_bottom;
                    break;
                case 3:
                    point[12] = width - margin_grid_right;
                    point[13] = margin_grid_left_top;
                    point[14] = width - margin_grid_right;
                    point[15] = height - margin_grid_bottom;
                    break;
            }
            minGridX = margin_grid_left_top;
            maxGridX = width - margin_grid_right;
            minGridY = margin_grid_left_top;
            maxGridY = height - margin_grid_bottom;
        }
        return point;
    }
}
