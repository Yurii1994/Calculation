package com.project.myv.calculator;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import io.codetail.widget.RevealFrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener
{
    public static ExtendEditText etNum;
    TextView view;
    TextView preResults;

    ImageButton btnC;
    Button btnPr;
    Button btnDiv;
    Button btnPow_X;
    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnMult;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSub;
    Button btnSeven;
    Button btnEight;
    Button btnNine;
    Button btnAdd;
    Button btnZero;
    static Button btnPoint;
    Button btnBr;
    static Button btnEqual;
    Button btnSin;
    Button btnAsin;
    Button btnSqrt;
    Button btnFac;
    Button btnCos;
    Button btnAcos;
    Button btnLn;
    Button btnLog;
    Button btnTan;
    Button btnAtan;
    Button btnP;
    Button btnE;

    SlidingUpPanelLayout slidingLayout;
    ImageView imageStatePanel;
    boolean panelState;
    Metrics metrics = new Metrics(this);
    Intent intent;
    SaveLoadPreferences saveLoadPreferences = new SaveLoadPreferences();
    DeprecatedMethods deprecatedMethods = new DeprecatedMethods();
    int countStartCalculator = 0;
    Calculation calculation = new Calculation();
    RelativeLayout bannerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            String appName = getString(R.string.app_name);
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            int color = deprecatedMethods.getColor(this, R.color.Blue1);
            ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(appName, icon, color);
            setTaskDescription(taskDesc);
        }

        makeActionOverflowMenuShown();

        etNum = new ExtendEditText(this, this);
        etNum.setId(R.id.etNum);
        etNum.setOnTouch();
        etNum.setTextSize(etNum.getMaxTextSize());
        etNum.setTextColor(deprecatedMethods.getColor(this, R.color.Grey2));
        etNum.setTypeface(etNum.faceLight);

        view = new TextView(this);
        view.setTypeface(etNum.faceLight);

        preResults = new TextView(this);
        preResults.setId(R.id.preResults);
        preResults.setTypeface(etNum.faceLight);
        preResults.setTextColor(deprecatedMethods.getColor(this, R.color.Grey2));

        btnC = new ImageButton(this, null, android.R.attr.borderlessButtonStyle);
        btnPr = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnDiv = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnPow_X = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnOne = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnTwo = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnThree = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnMult = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnFour = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnFive = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnSix = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnSub = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnSeven = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnEight = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnNine = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnAdd = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnZero = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnPoint = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnBr = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnEqual = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnSin = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnAsin = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnSqrt = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnFac = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnCos = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnAcos = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnLn = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnLog = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnTan = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnAtan = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnP = new Button(this, null, android.R.attr.borderlessButtonStyle);
        btnE = new Button(this, null, android.R.attr.borderlessButtonStyle);

        btnC.setId(R.id.btnC);
        btnPr.setId(R.id.btnPr);
        btnDiv.setId(R.id.btnDiv);
        btnPow_X.setId(R.id.btnPow_X);
        btnOne.setId(R.id.btnOne);
        btnTwo.setId(R.id.btnTwo);
        btnThree.setId(R.id.btnThree);
        btnMult.setId(R.id.btnMult);
        btnFour.setId(R.id.btnFour);
        btnFive.setId(R.id.btnFive);
        btnSix.setId(R.id.btnSix);
        btnSub.setId(R.id.btnSub);
        btnSeven.setId(R.id.btnSeven);
        btnEight.setId(R.id.btnEight);
        btnNine.setId(R.id.btnNine);
        btnAdd.setId(R.id.btnAdd);
        btnZero.setId(R.id.btnZero);
        btnPoint.setId(R.id.btnPoint);
        btnBr.setId(R.id.btnBr);
        btnEqual.setId(R.id.btnEqual);
        btnSin.setId(R.id.btnSin);
        btnAsin.setId(R.id.btnAsin);
        btnSqrt.setId(R.id.btnSqrt);
        btnFac.setId(R.id.btnFac);
        btnCos.setId(R.id.btnCos);
        btnAcos.setId(R.id.btnAcos);
        btnLn.setId(R.id.btnLn);
        btnLog.setId(R.id.btnLog);
        btnTan.setId(R.id.btnTan);
        btnAtan.setId(R.id.btnAtan);
        btnP.setId(R.id.btnP);
        btnE.setId(R.id.btnE);

        btnPr.setTypeface(etNum.faceLight);
        btnDiv.setTypeface(etNum.faceLight);
        btnPow_X.setTypeface(etNum.faceLight);
        btnOne.setTypeface(etNum.faceLight);
        btnTwo.setTypeface(etNum.faceLight);
        btnThree.setTypeface(etNum.faceLight);
        btnMult.setTypeface(etNum.faceLight);
        btnFour.setTypeface(etNum.faceLight);
        btnFive.setTypeface(etNum.faceLight);
        btnSix.setTypeface(etNum.faceLight);
        btnSub.setTypeface(etNum.faceLight);
        btnSeven.setTypeface(etNum.faceLight);
        btnEight.setTypeface(etNum.faceLight);
        btnNine.setTypeface(etNum.faceLight);
        btnAdd.setTypeface(etNum.faceLight);
        btnZero.setTypeface(etNum.faceLight);
        btnPoint.setTypeface(etNum.faceLight);
        btnBr.setTypeface(etNum.faceLight);
        btnEqual.setTypeface(etNum.faceLight);
        btnSin.setTypeface(etNum.faceLight);
        btnAsin.setTypeface(etNum.faceLight);
        btnSqrt.setTypeface(etNum.faceLight);
        btnFac.setTypeface(etNum.faceLight);
        btnCos.setTypeface(etNum.faceLight);
        btnAcos.setTypeface(etNum.faceLight);
        btnLn.setTypeface(etNum.faceLight);
        btnLog.setTypeface(etNum.faceLight);
        btnTan.setTypeface(etNum.faceLight);
        btnAtan.setTypeface(etNum.faceLight);
        btnP.setTypeface(etNum.faceLight);
        btnE.setTypeface(etNum.faceLight);

        btnC.setOnLongClickListener(this);
        btnEqual.setOnLongClickListener(this);
        btnC.setOnClickListener(this);
        btnPr.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnPow_X.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnBr.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnSin.setOnClickListener(this);
        btnAsin.setOnClickListener(this);
        btnSqrt.setOnClickListener(this);
        btnFac.setOnClickListener(this);
        btnCos.setOnClickListener(this);
        btnAcos.setOnClickListener(this);
        btnLn.setOnClickListener(this);
        btnLog.setOnClickListener(this);
        btnTan.setOnClickListener(this);
        btnAtan.setOnClickListener(this);
        btnP.setOnClickListener(this);
        btnE.setOnClickListener(this);

        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnC);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnPr);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnDiv);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnPow_X);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnOne);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnTwo);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnThree);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnMult);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnFour);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnFive);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnSix);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnSub);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnSeven);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnEight);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnNine);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnAdd);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnZero);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnPoint);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnBr);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_grey), btnEqual);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnSin);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnAsin);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnSqrt);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnFac);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnCos);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnAcos);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnLn);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnLog);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnTan);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnAtan);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnP);
        deprecatedMethods.setBackground(deprecatedMethods.getDrawable(this, R.drawable.button_selector_white), btnE);

        SAVE_TAG = SAVE_TAG + "onCreate()";
        countStartCalculator = saveLoadPreferences.loadIntegerPreferences("Calculator", "COUNT_START_CALCULATOR", this);
        countStartCalculator++;
        saveLoadPreferences.saveIntegerPreferences("Calculator", "COUNT_START_CALCULATOR", countStartCalculator, this);
        SettingsActivity.countStartSettings = saveLoadPreferences.loadIntegerPreferences("Settings", "COUNT_START_SETTINGS", this);
        if (countStartCalculator == 1)
        {
            TAG_ANGLE = "RAD";
            STATE_APP_RATE = false;
            STATE_APP_RATE_LATER = false;
            STATE_APP_RATE_DENIED = false;
        }
        else
        {
            TAG_ANGLE = saveLoadPreferences.loadStringPreferences("Settings", "ANGLE", this);
            if (!TAG_ANGLE.equals("RAD") & !TAG_ANGLE.equals("GRAD") & !TAG_ANGLE.equals("DEG"))
            {
                TAG_ANGLE = "RAD";
            }
            STATE_APP_RATE = saveLoadPreferences.loadBooleanPreferences("AppRate", "STATE_APP_RATE", this);
            STATE_APP_RATE_LATER = saveLoadPreferences.loadBooleanPreferences("AppRate", "STATE_APP_RATE_LATER", this);
            STATE_APP_RATE_DENIED = saveLoadPreferences.loadBooleanPreferences("AppRate", "STATE_APP_RATE_DENIED", this);
        }

        getCreatedDisplay(0);
        getAppRate();
        getRotateImageSlidePanel(slidingLayout, imageStatePanel);
    }

    public void getRotateImageSlidePanel(SlidingUpPanelLayout layout, final ImageView view)
    {
        if (layout != null)
        {
            layout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener()
            {
                @Override
                public void onPanelSlide(View panel, float slideOffset)
                {
                    float angle = slideOffset * 180;
                    view.setRotation(angle);
                    float scalePercent = 1 * 0.5f;
                    float scale;
                    if (slideOffset < scalePercent)
                    {
                        scale = 1 - slideOffset;
                    }
                    else
                    {
                        scale = slideOffset;
                    }
                    view.setScaleX(scale);
                    view.setScaleY(scale);
                }

                @Override
                public void onPanelExpanded(View panel)
                {

                }

                @Override
                public void onPanelCollapsed(View panel)
                {

                }

                @Override
                public void onPanelAnchored(View panel)
                {

                }

                @Override
                public void onPanelHidden(View panel)
                {

                }
            });
        }
    }

    private boolean getStatePreResults()
    {
        boolean state = false;
        int countChangePreResult = saveLoadPreferences.loadIntegerPreferences("Settings", "COUNT_CHANGE_PRE_RESULTS", this);
        if (countChangePreResult >= 1)
        {
            boolean statePreResults = saveLoadPreferences.loadBooleanPreferences("Settings", "PRE_RESULTS", this);
            if (statePreResults)
            {
                state = true;
            }
        }
        else
        {
            state = true;
        }
        return state;
    }

    private int getHeightEtNum(boolean statePreResults, int heightPreResults, int line)
    {
        int height;
        if (metrics.getOrientation())
        {
            if (statePreResults)
            {
                height = metrics.pxFromDp(metrics.getHeightEtNum(line));
            }
            else
            {
                height = metrics.pxFromDp(metrics.getHeightEtNum(line)) + heightPreResults;
            }
        }
        else
        {
            if (statePreResults)
            {
                height = metrics.pxFromDp(metrics.getHeightEtNum(line));
            }
            else
            {
                height = metrics.pxFromDp(metrics.getHeightEtNum(line)) + heightPreResults;
            }
        }
        return height;
    }

    private int getWindowCalcHeight(boolean state, int etNumHeight, int preResultsHeight)
    {
        int height;
        if (state)
        {
            height = etNumHeight + preResultsHeight;
        }
        else
        {
            height = etNumHeight;
        }
        return height;
    }


    private void getCreateWindowCalc(LinearLayout revealLayout, boolean statePreResults, int padding, int etNumHeight, int preResultHeight)
    {
        revealLayout.removeAllViews();
        if (statePreResults)
        {
            LinearLayout.LayoutParams etNumParams = new  LinearLayout.LayoutParams(
                    RevealFrameLayout.LayoutParams.MATCH_PARENT, etNumHeight);
            etNum.setPadding(padding, padding, padding, padding);
            etNum.setGravity(Gravity.CENTER | Gravity.END);
            etNum.setBackgroundColor(deprecatedMethods.getColor(this, R.color.White));
            revealLayout.addView(etNum, etNumParams);

            LinearLayout.LayoutParams preResultsParams = new  LinearLayout.LayoutParams(
                    RevealFrameLayout.LayoutParams.MATCH_PARENT, preResultHeight);
            preResults.setPadding(0, 0 , padding, 0);
            preResults.setGravity(Gravity.CENTER | Gravity.END);
            preResults.setBackgroundColor(deprecatedMethods.getColor(this, R.color.White));
            revealLayout.addView(preResults, preResultsParams);
        }
        else
        {
            RevealFrameLayout.LayoutParams etNumParams = new RevealFrameLayout.LayoutParams(
                    RevealFrameLayout.LayoutParams.MATCH_PARENT, etNumHeight);
            etNum.setPadding(padding, padding, padding, padding);
            etNum.setGravity(Gravity.CENTER | Gravity.END);
            etNum.setBackgroundColor(deprecatedMethods.getColor(this, R.color.White));
            revealLayout.addView(etNum, etNumParams);

        }
    }

    LinearLayout revealLayout;

    public void getCreatedDisplay(int bannerHeight)
    {
        int ActionBarHeight = metrics.getActionBarHeight();
        int StatusBarHeight = metrics.getStatusBarHeight();

        bannerLayout = new RelativeLayout(this);
        bannerLayout.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Black1));
        bannerLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));

        preResults.setTextSize(etNum.getMinTextSize());
        boolean statePreResults = getStatePreResults();
        int preResultHeight = metrics.getHeightPreResult((int) preResults.getTextSize());

        if (metrics.getOrientation())
        {
            panelState = true;
            int PanelHeightClosed = metrics.getHeightSlidingPanelClosed();
            int margin = metrics.getMargin();
            int padding = metrics.getPadding();
            int buttonPadding = margin / 2;
            int etNumHeight = getHeightEtNum(statePreResults, preResultHeight, 4);
            int windowCalcHeight = getWindowCalcHeight(statePreResults, etNumHeight, preResultHeight);
            int layout3Height = metrics.getMyHeight() - windowCalcHeight - PanelHeightClosed
                    - ActionBarHeight - StatusBarHeight - bannerHeight;

            int PanelHeightOpened = ((layout3Height / 5) * 3) + PanelHeightClosed;
            int linearLayoutHeight = (windowCalcHeight + layout3Height) + PanelHeightClosed;
            int widthTrigonometricPanel = metrics.getWidthTrigonometricPanel();
            int textSize = metrics.getTextSizeButton(metrics.getSizeButtonWidth(widthTrigonometricPanel, buttonPadding, 4),
                    metrics.getSizeButtonHeight(PanelHeightOpened, buttonPadding, 3));
            int buttonImagePadding = (metrics.getSizeButtonHeight(layout3Height, buttonPadding, 5)) / 5;

            LinearLayout linearLayoutMain = new LinearLayout(this);
            linearLayoutMain.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayoutMain.setOrientation(LinearLayout.VERTICAL);
            setContentView(linearLayoutMain);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, linearLayoutHeight));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayoutMain.addView(linearLayout);


            slidingLayout = new SlidingUpPanelLayout(this);
            slidingLayout.setLayoutParams(new SlidingUpPanelLayout.LayoutParams(
                    SlidingUpPanelLayout.LayoutParams.MATCH_PARENT, SlidingUpPanelLayout.LayoutParams.MATCH_PARENT));
            slidingLayout.setPanelHeight(PanelHeightClosed);
            slidingLayout.setShadowHeight(0);
            slidingLayout.setGravity(Gravity.BOTTOM);
            slidingLayout.setCoveredFadeColor(Color.TRANSPARENT);
            linearLayout.addView(slidingLayout);

            LinearLayout layout1 = new LinearLayout(this);
            layout1.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            layout1.setOrientation(LinearLayout.VERTICAL);
            slidingLayout.addView(layout1);

            RevealFrameLayout revealFrameLayout = new RevealFrameLayout(this);
            revealFrameLayout.setLayoutParams(new RevealFrameLayout.LayoutParams(
                    RevealFrameLayout.LayoutParams.MATCH_PARENT, windowCalcHeight));
            revealFrameLayout.setBackgroundColor(deprecatedMethods.getColor(this, R.color.White));
            layout1.addView(revealFrameLayout);

            revealLayout = new LinearLayout(this);
            revealLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams revealLayoutParams = new  LinearLayout.LayoutParams(
                    RevealFrameLayout.LayoutParams.MATCH_PARENT, windowCalcHeight);
            revealFrameLayout.addView(revealLayout, revealLayoutParams);

            getCreateWindowCalc(revealLayout, statePreResults, padding, etNumHeight, preResultHeight);
            view.setVisibility(View.INVISIBLE);
            revealFrameLayout.addView(view, revealLayoutParams);

            LinearLayout layout3 = new LinearLayout(this);
            layout3.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, layout3Height));
            layout3.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layout3.setOrientation(LinearLayout.VERTICAL);
            layout1.addView(layout3);

            LinearLayout layoutNumericalPanel = new LinearLayout(this);
            LinearLayout.LayoutParams layoutNumericalPanelParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutNumericalPanelParams.leftMargin = margin;
            layoutNumericalPanelParams.rightMargin = margin;
            layoutNumericalPanelParams.topMargin = margin;
            layoutNumericalPanelParams.bottomMargin = margin;
            layoutNumericalPanel.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutNumericalPanel.setOrientation(LinearLayout.VERTICAL);
            layout3.addView(layoutNumericalPanel, layoutNumericalPanelParams);

            LinearLayout.LayoutParams layoutButtonParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutButtonParams.weight = 1;

            LinearLayout.LayoutParams layoutRowParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutRowParams.weight = 1;

            LinearLayout layoutRow1_1 = new LinearLayout(this);
            layoutRow1_1.setOrientation(LinearLayout.HORIZONTAL);
            layoutNumericalPanel.addView(layoutRow1_1, layoutRowParams);

            btnPow_X.setTextSize(textSize);
            btnPow_X.setText("^ | X");
            btnPow_X.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnPow_X.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow1_1.addView(btnPow_X, layoutButtonParams);

            btnPr.setTextSize(textSize);
            btnPr.setText("%");
            btnPr.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnPr.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow1_1.addView(btnPr, layoutButtonParams);

            btnDiv.setTextSize(textSize);
            btnDiv.setText("÷");
            btnDiv.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnDiv.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow1_1.addView(btnDiv, layoutButtonParams);

            btnC.setImageDrawable(deprecatedMethods.getDrawable(this, R.mipmap.button_clear));
            btnC.setPadding(buttonImagePadding, buttonImagePadding, buttonImagePadding, buttonImagePadding);
            layoutRow1_1.addView(btnC, layoutButtonParams);

            LinearLayout layoutRow2_1 = new LinearLayout(this);
            layoutRow2_1.setOrientation(LinearLayout.HORIZONTAL);
            layoutNumericalPanel.addView(layoutRow2_1, layoutRowParams);

            btnSeven.setTextSize(textSize);
            btnSeven.setText("7");
            btnSeven.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSeven.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow2_1.addView(btnSeven, layoutButtonParams);

            btnEight.setTextSize(textSize);
            btnEight.setText("8");
            btnEight.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnEight.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow2_1.addView(btnEight, layoutButtonParams);

            btnNine.setTextSize(textSize);
            btnNine.setText("9");
            btnNine.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnNine.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow2_1.addView(btnNine, layoutButtonParams);

            btnMult.setTextSize(textSize);
            btnMult.setText("×");
            btnMult.setGravity(Gravity.CENTER);
            btnMult.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnMult.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow2_1.addView(btnMult, layoutButtonParams);

            LinearLayout layoutRow3_1 = new LinearLayout(this);
            layoutRow3_1.setOrientation(LinearLayout.HORIZONTAL);
            layoutNumericalPanel.addView(layoutRow3_1, layoutRowParams);

            btnFour.setTextSize(textSize);
            btnFour.setText("4");
            btnFour.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnFour.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow3_1.addView(btnFour, layoutButtonParams);

            btnFive.setTextSize(textSize);
            btnFive.setText("5");
            btnFive.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnFive.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow3_1.addView(btnFive, layoutButtonParams);

            btnSix.setTextSize(textSize);
            btnSix.setText("6");
            btnSix.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSix.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow3_1.addView(btnSix, layoutButtonParams);

            btnSub.setTextSize(textSize);
            btnSub.setText("−");
            btnSub.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSub.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow3_1.addView(btnSub, layoutButtonParams);

            LinearLayout layoutRow4_1 = new LinearLayout(this);
            layoutRow4_1.setOrientation(LinearLayout.HORIZONTAL);
            layoutNumericalPanel.addView(layoutRow4_1, layoutRowParams);

            btnOne.setTextSize(textSize);
            btnOne.setText("1");
            btnOne.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnOne.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow4_1.addView(btnOne, layoutButtonParams);

            btnTwo.setTextSize(textSize);
            btnTwo.setText("2");
            btnTwo.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnTwo.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow4_1.addView(btnTwo, layoutButtonParams);

            btnThree.setTextSize(textSize);
            btnThree.setText("3");
            btnThree.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnThree.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow4_1.addView(btnThree, layoutButtonParams);

            btnAdd.setTextSize(textSize);
            btnAdd.setText("+");
            btnAdd.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnAdd.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow4_1.addView(btnAdd, layoutButtonParams);

            LinearLayout layoutRow5_1 = new LinearLayout(this);
            layoutRow5_1.setOrientation(LinearLayout.HORIZONTAL);
            layoutNumericalPanel.addView(layoutRow5_1, layoutRowParams);

            btnZero.setTextSize(textSize);
            btnZero.setText("0");
            btnZero.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnZero.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow5_1.addView(btnZero, layoutButtonParams);

            btnPoint.setTextSize(textSize);
            btnPoint.setText(etNum.getStatePoint(saveLoadPreferences.loadBooleanPreferences("Settings", "POINT", this)));
            btnPoint.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnPoint.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow5_1.addView(btnPoint, layoutButtonParams);

            btnBr.setTextSize(textSize);
            btnBr.setText("( )");
            btnBr.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnBr.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow5_1.addView(btnBr, layoutButtonParams);

            btnEqual.setTextSize(textSize);
            btnEqual.setText("=");
            btnEqual.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnEqual.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow5_1.addView(btnEqual, layoutButtonParams);

            LinearLayout layout4 = new LinearLayout(this);
            LinearLayout.LayoutParams layout4Params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, PanelHeightOpened);
            layout4.setOrientation(LinearLayout.VERTICAL);
            layout4.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Green1));
            slidingLayout.addView(layout4, layout4Params);

            LinearLayout layout5 = new LinearLayout(this);
            layout5.setOrientation(LinearLayout.VERTICAL);
            layout5.setGravity(Gravity.CENTER);
            layout5.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Green1));

            LinearLayout.LayoutParams layout5Params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, PanelHeightClosed);
            layout5Params.gravity = Gravity.CENTER;

            layout4.addView(layout5, layout5Params);

            imageStatePanel = new ImageView(this);
            imageStatePanel.setImageDrawable(deprecatedMethods.getDrawable(this, R.drawable.ic_action_state_panel));
            layout5.addView(imageStatePanel, layout5Params);

            LinearLayout layoutTrigonometric = new LinearLayout(this);
            LinearLayout.LayoutParams layoutTrigonometricParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutTrigonometricParams.leftMargin = margin;
            layoutTrigonometricParams.rightMargin = margin;
            layoutTrigonometricParams.bottomMargin = margin;
            layoutTrigonometric.setOrientation(LinearLayout.VERTICAL);
            layout4.addView(layoutTrigonometric, layoutTrigonometricParams);

            LinearLayout layoutRow1_2 = new LinearLayout(this);
            layoutRow1_2.setOrientation(LinearLayout.HORIZONTAL);
            layoutTrigonometric.addView(layoutRow1_2, layoutRowParams);

            btnSin.setTextSize(textSize);
            btnSin.setText(getResources().getText(R.string.sin));
            btnSin.setInputType(InputType.TYPE_CLASS_TEXT);
            btnSin.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSin.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow1_2.addView(btnSin, layoutButtonParams);

            btnAsin.setTextSize(textSize);
            btnAsin.setText(getResources().getText(R.string.asin));
            btnAsin.setInputType(InputType.TYPE_CLASS_TEXT);
            btnAsin.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnAsin.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow1_2.addView(btnAsin, layoutButtonParams);

            btnSqrt.setTextSize(textSize);
            btnSqrt.setText(getResources().getText(R.string.sqrt));
            btnSqrt.setInputType(InputType.TYPE_CLASS_TEXT);
            btnSqrt.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSqrt.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow1_2.addView(btnSqrt, layoutButtonParams);

            btnFac.setTextSize(textSize);
            btnFac.setText(getResources().getText(R.string.fac));
            btnFac.setInputType(InputType.TYPE_CLASS_TEXT);
            btnFac.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnFac.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow1_2.addView(btnFac, layoutButtonParams);

            LinearLayout layoutRow2_2 = new LinearLayout(this);
            layoutRow2_2.setOrientation(LinearLayout.HORIZONTAL);
            layoutTrigonometric.addView(layoutRow2_2, layoutRowParams);

            btnCos.setTextSize(textSize);
            btnCos.setText(getResources().getText(R.string.cos));
            btnCos.setInputType(InputType.TYPE_CLASS_TEXT);
            btnCos.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnCos.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow2_2.addView(btnCos, layoutButtonParams);

            btnAcos.setTextSize(textSize);
            btnAcos.setText(getResources().getText(R.string.acos));
            btnAcos.setInputType(InputType.TYPE_CLASS_TEXT);
            btnAcos.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnAcos.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow2_2.addView(btnAcos, layoutButtonParams);

            btnLn.setTextSize(textSize);
            btnLn.setText(getResources().getText(R.string.ln));
            btnLn.setInputType(InputType.TYPE_CLASS_TEXT);
            btnLn.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnLn.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow2_2.addView(btnLn, layoutButtonParams);

            btnLog.setTextSize(textSize);
            btnLog.setText(getResources().getText(R.string.log));
            btnLog.setInputType(InputType.TYPE_CLASS_TEXT);
            btnLog.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnLog.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow2_2.addView(btnLog, layoutButtonParams);

            LinearLayout layoutRow3_2 = new LinearLayout(this);
            layoutRow3_2.setOrientation(LinearLayout.HORIZONTAL);
            layoutTrigonometric.addView(layoutRow3_2, layoutRowParams);

            btnTan.setTextSize(textSize);
            btnTan.setText(getResources().getText(R.string.tan));
            btnTan.setInputType(InputType.TYPE_CLASS_TEXT);
            btnTan.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnTan.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow3_2.addView(btnTan, layoutButtonParams);

            btnAtan.setTextSize(textSize);
            btnAtan.setText(getResources().getText(R.string.atan));
            btnAtan.setInputType(InputType.TYPE_CLASS_TEXT);
            btnAtan.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnAtan.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow3_2.addView(btnAtan, layoutButtonParams);

            btnP.setTextSize(textSize);
            btnP.setText(getResources().getText(R.string.p));
            btnP.setInputType(InputType.TYPE_CLASS_TEXT);
            btnP.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnP.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow3_2.addView(btnP, layoutButtonParams);

            btnE.setTextSize(textSize);
            btnE.setText(getResources().getText(R.string.e));
            btnE.setInputType(InputType.TYPE_CLASS_TEXT);
            btnE.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnE.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow3_2.addView(btnE, layoutButtonParams);

            linearLayoutMain.addView(bannerLayout);
        }
        else
        {
            int margin = metrics.getMargin();
            int padding = metrics.getPadding();
            int buttonPadding = margin / 2;

            int etNumHeight = getHeightEtNum(statePreResults, preResultHeight, 2);
            int windowCalcHeight = getWindowCalcHeight(statePreResults, etNumHeight, preResultHeight);

            int layout2Height = metrics.getMyHeight() - windowCalcHeight - ActionBarHeight - StatusBarHeight - bannerHeight;
            int widthTrigonometricPanel = metrics.getWidthTrigonometricPanel();
            int textSize = metrics.getTextSizeButton(metrics.getSizeButtonWidth(widthTrigonometricPanel, buttonPadding, 3),
                    metrics.getSizeButtonHeight(layout2Height, buttonPadding, 4));
            int buttonImagePadding = (metrics.getSizeButtonHeight(layout2Height, buttonPadding, 4)) / 6;

            LinearLayout linearLayoutMain = new LinearLayout(this);
            linearLayoutMain.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayoutMain.setOrientation(LinearLayout.VERTICAL);
            setContentView(linearLayoutMain);

            RevealFrameLayout revealFrameLayout = new RevealFrameLayout(this);
            revealFrameLayout.setLayoutParams(new RevealFrameLayout.LayoutParams(
                    RevealFrameLayout.LayoutParams.MATCH_PARENT, windowCalcHeight));
            revealFrameLayout.setBackgroundColor(deprecatedMethods.getColor(this, R.color.White));
            linearLayoutMain.addView(revealFrameLayout);

            revealLayout = new LinearLayout(this);
            revealLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams revealLayoutParams = new  LinearLayout.LayoutParams(
                    RevealFrameLayout.LayoutParams.MATCH_PARENT, windowCalcHeight);
            revealFrameLayout.addView(revealLayout, revealLayoutParams);

            getCreateWindowCalc(revealLayout, statePreResults, padding, etNumHeight, preResultHeight);
            etNum.setImeOptions(EditorInfo.IME_FLAG_NO_FULLSCREEN);
            view.setVisibility(View.INVISIBLE);
            revealFrameLayout.addView(view, revealLayoutParams);

            LinearLayout layout2 = new LinearLayout(this);
            layout2.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, layout2Height));
            linearLayoutMain.addView(layout2);

            LinearLayout layout3 = new LinearLayout(this);
            LinearLayout.LayoutParams layout3Params = new LinearLayout.LayoutParams(
                    widthTrigonometricPanel, LinearLayout.LayoutParams.MATCH_PARENT);
            layout3.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Green1));
            layout2.addView(layout3, layout3Params);

            LinearLayout layoutTrigonometricalPanel = new LinearLayout(this);
            layoutTrigonometricalPanel.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutTrigonometricalPanelParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutTrigonometricalPanel.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Green1));
            layoutTrigonometricalPanelParams.rightMargin = margin / 2;
            layoutTrigonometricalPanelParams.leftMargin = margin;
            layoutTrigonometricalPanelParams.topMargin = margin;
            layoutTrigonometricalPanelParams.bottomMargin = margin;
            layout3.addView(layoutTrigonometricalPanel, layoutTrigonometricalPanelParams);

            LinearLayout.LayoutParams layoutButtonParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutButtonParams.weight = 1;

            LinearLayout.LayoutParams layoutRowParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutRowParams.weight = 1;

            LinearLayout layoutRow1_1 = new LinearLayout(this);
            layoutRow1_1.setOrientation(LinearLayout.HORIZONTAL);
            layoutTrigonometricalPanel.addView(layoutRow1_1, layoutRowParams);

            btnSin.setTextSize(textSize);
            btnSin.setText(getResources().getText(R.string.sin));
            btnSin.setInputType(InputType.TYPE_CLASS_TEXT);
            btnSin.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSin.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow1_1.addView(btnSin, layoutButtonParams);

            btnAsin.setTextSize(textSize);
            btnAsin.setText(getResources().getText(R.string.asin));
            btnAsin.setInputType(InputType.TYPE_CLASS_TEXT);
            btnAsin.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnAsin.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow1_1.addView(btnAsin, layoutButtonParams);

            btnSqrt.setTextSize(textSize);
            btnSqrt.setText(getResources().getText(R.string.sqrt));
            btnSqrt.setInputType(InputType.TYPE_CLASS_TEXT);
            btnSqrt.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSqrt.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow1_1.addView(btnSqrt, layoutButtonParams);


            LinearLayout layoutRow2_1 = new LinearLayout(this);
            layoutRow2_1.setOrientation(LinearLayout.HORIZONTAL);
            layoutTrigonometricalPanel.addView(layoutRow2_1, layoutRowParams);

            btnCos.setTextSize(textSize);
            btnCos.setText(getResources().getText(R.string.cos));
            btnCos.setInputType(InputType.TYPE_CLASS_TEXT);
            btnCos.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnCos.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow2_1.addView(btnCos, layoutButtonParams);

            btnAcos.setTextSize(textSize);
            btnAcos.setText(getResources().getText(R.string.acos));
            btnAcos.setInputType(InputType.TYPE_CLASS_TEXT);
            btnAcos.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnAcos.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow2_1.addView(btnAcos, layoutButtonParams);

            btnFac.setTextSize(textSize);
            btnFac.setText(getResources().getText(R.string.fac));
            btnFac.setInputType(InputType.TYPE_CLASS_TEXT);
            btnFac.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnFac.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow2_1.addView(btnFac, layoutButtonParams);

            LinearLayout layoutRow3_1 = new LinearLayout(this);
            layoutRow3_1.setOrientation(LinearLayout.HORIZONTAL);
            layoutTrigonometricalPanel.addView(layoutRow3_1, layoutRowParams);

            btnTan.setTextSize(textSize);
            btnTan.setText(getResources().getText(R.string.tan));
            btnTan.setInputType(InputType.TYPE_CLASS_TEXT);
            btnTan.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnTan.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow3_1.addView(btnTan, layoutButtonParams);

            btnAtan.setTextSize(textSize);
            btnAtan.setText(getResources().getText(R.string.atan));
            btnAtan.setInputType(InputType.TYPE_CLASS_TEXT);
            btnAtan.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnAtan.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow3_1.addView(btnAtan, layoutButtonParams);

            btnP.setTextSize(textSize);
            btnP.setText(getResources().getText(R.string.p));
            btnP.setInputType(InputType.TYPE_CLASS_TEXT);
            btnP.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnP.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow3_1.addView(btnP, layoutButtonParams);

            LinearLayout layoutRow4_1 = new LinearLayout(this);
            layoutRow4_1.setOrientation(LinearLayout.HORIZONTAL);
            layoutTrigonometricalPanel.addView(layoutRow4_1, layoutRowParams);

            btnLn.setTextSize(textSize);
            btnLn.setText(getResources().getText(R.string.ln));
            btnLn.setInputType(InputType.TYPE_CLASS_TEXT);
            btnLn.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnLn.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow4_1.addView(btnLn, layoutButtonParams);

            btnLog.setTextSize(textSize);
            btnLog.setText(getResources().getText(R.string.log));
            btnLog.setInputType(InputType.TYPE_CLASS_TEXT);
            btnLog.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnLog.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow4_1.addView(btnLog, layoutButtonParams);

            btnE.setTextSize(textSize);
            btnE.setText(getResources().getText(R.string.e));
            btnE.setInputType(InputType.TYPE_CLASS_TEXT);
            btnE.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnE.setTextColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutRow4_1.addView(btnE, layoutButtonParams);

            LinearLayout layout4 = new LinearLayout(this);
            LinearLayout.LayoutParams layout4Params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layout4.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layout2.addView(layout4, layout4Params);

            LinearLayout layoutNumericalPanel = new LinearLayout(this);
            layoutNumericalPanel.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutNumericalPanelParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutNumericalPanel.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Grey1));
            layoutNumericalPanelParams.rightMargin = margin;
            layoutNumericalPanelParams.leftMargin = margin / 2;
            layoutNumericalPanelParams.topMargin = margin;
            layoutNumericalPanelParams.bottomMargin = margin;
            layout4.addView(layoutNumericalPanel, layoutNumericalPanelParams);

            LinearLayout layoutRow1_2 = new LinearLayout(this);
            layoutRow1_2.setOrientation(LinearLayout.HORIZONTAL);
            layoutNumericalPanel.addView(layoutRow1_2, layoutRowParams);

            btnSeven.setTextSize(textSize);
            btnSeven.setText("7");
            btnSeven.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSeven.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow1_2.addView(btnSeven, layoutButtonParams);

            btnEight.setTextSize(textSize);
            btnEight.setText("8");
            btnEight.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnEight.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow1_2.addView(btnEight, layoutButtonParams);

            this.btnNine.setTextSize(textSize);
            this.btnNine.setText("9");
            this.btnNine.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            this.btnNine.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow1_2.addView(btnNine, layoutButtonParams);

            btnDiv.setTextSize(textSize);
            btnDiv.setText("÷");
            btnDiv.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnDiv.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow1_2.addView(btnDiv, layoutButtonParams);

            btnC.setImageDrawable(deprecatedMethods.getDrawable(this, R.mipmap.button_clear));
            btnC.setPadding(buttonImagePadding, buttonImagePadding, buttonImagePadding, buttonImagePadding);
            layoutRow1_2.addView(btnC, layoutButtonParams);

            LinearLayout layoutRow2_2 = new LinearLayout(this);
            layoutRow2_2.setOrientation(LinearLayout.HORIZONTAL);
            layoutNumericalPanel.addView(layoutRow2_2, layoutRowParams);

            btnFour.setTextSize(textSize);
            btnFour.setText("4");
            btnFour.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnFour.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow2_2.addView(btnFour, layoutButtonParams);

            btnFive.setTextSize(textSize);
            btnFive.setText("5");
            btnFive.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnFive.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow2_2.addView(btnFive, layoutButtonParams);

            btnSix.setTextSize(textSize);
            btnSix.setText("6");
            btnSix.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSix.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow2_2.addView(btnSix, layoutButtonParams);

            btnMult.setTextSize(textSize);
            btnMult.setText("×");
            btnMult.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnMult.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow2_2.addView(btnMult, layoutButtonParams);

            btnPr.setTextSize(textSize);
            btnPr.setText("%");
            btnPr.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnPr.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow2_2.addView(btnPr, layoutButtonParams);

            LinearLayout layoutRow3_2 = new LinearLayout(this);
            layoutRow3_2.setOrientation(LinearLayout.HORIZONTAL);
            layoutNumericalPanel.addView(layoutRow3_2, layoutRowParams);

            btnOne.setTextSize(textSize);
            btnOne.setText("1");
            btnOne.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnOne.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow3_2.addView(btnOne, layoutButtonParams);

            btnTwo.setTextSize(textSize);
            btnTwo.setText("2");
            btnTwo.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnTwo.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow3_2.addView(btnTwo, layoutButtonParams);

            btnThree.setTextSize(textSize);
            btnThree.setText("3");
            btnThree.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnThree.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow3_2.addView(btnThree, layoutButtonParams);

            btnSub.setTextSize(textSize);
            btnSub.setText("−");
            btnSub.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnSub.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow3_2.addView(btnSub, layoutButtonParams);

            btnPow_X.setTextSize(textSize);
            btnPow_X.setText("^ | X");
            btnPow_X.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnPow_X.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow3_2.addView(btnPow_X, layoutButtonParams);

            LinearLayout layoutRow4_2 = new LinearLayout(this);
            layoutRow4_2.setOrientation(LinearLayout.HORIZONTAL);
            layoutNumericalPanel.addView(layoutRow4_2, layoutRowParams);

            btnZero.setTextSize(textSize);
            btnZero.setText("0");
            btnZero.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnZero.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow4_2.addView(btnZero, layoutButtonParams);

            btnPoint.setTextSize(textSize);
            btnPoint.setText(etNum.getStatePoint(saveLoadPreferences.loadBooleanPreferences("Settings", "POINT", this)));
            btnPoint.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnPoint.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow4_2.addView(btnPoint, layoutButtonParams);

            btnBr.setTextSize(textSize);
            btnBr.setText("( )");
            btnBr.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnBr.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow4_2.addView(btnBr, layoutButtonParams);

            btnAdd.setTextSize(textSize);
            btnAdd.setText("+");
            btnAdd.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnAdd.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow4_2.addView(btnAdd, layoutButtonParams);

            btnEqual.setTextSize(textSize);
            btnEqual.setText("=");
            btnEqual.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            btnEqual.setTextColor(deprecatedMethods.getColor(this, R.color.White));
            layoutRow4_2.addView(btnEqual, layoutButtonParams);

            linearLayoutMain.addView(bannerLayout);
        }
    }


    String SAVE_TAG;

    @Override
    protected void onResume()
    {
        super.onResume();
        SAVE_TAG = SAVE_TAG + "onResume()";
        if (countStartCalculator == 1 & SettingsActivity.countStartSettings == 0 || SettingsActivity.countStartSettings == 0)
        {
            etNum.textVertical = true;
            etNum.limitedText = true;
            etNum.limitedNumber = true;
            etNum.exitText = true;
            SettingsActivity.progressSeekBar = (int) (80 * 0.25);
            button.milliseconds = SettingsActivity.progressSeekBar;
            SettingsActivity.stateHibernate = false;
            etNum.point = false;
        }
        else
        {
            etNum.textVertical = saveLoadPreferences.loadBooleanPreferences("Settings", "TEXT_VERTICAL", this);
            etNum.limitedText = saveLoadPreferences.loadBooleanPreferences("Settings", "LIMITED_TEXT", this);
            etNum.limitedNumber = saveLoadPreferences.loadBooleanPreferences("Settings", "LIMITED_NUMBER", this);
            etNum.exitText = saveLoadPreferences.loadBooleanPreferences("Settings", "EXIT_TEXT", this);
            SettingsActivity.progressSeekBar = saveLoadPreferences.loadIntegerPreferences("Settings", "PROGRESS_SEEKBAR", this);
            button.milliseconds = SettingsActivity.progressSeekBar;
            SettingsActivity.stateHibernate = saveLoadPreferences.loadBooleanPreferences("Settings", "STATE_HIBERNATE", this);
            etNum.point = saveLoadPreferences.loadBooleanPreferences("Settings", "POINT", this);
            if (etNum.exitText)
            {
                stateCalculation = saveLoadPreferences.loadBooleanPreferences("STATE_CALCULATION", "STATE_CALCULATION", this);
            }
        }
        if (SettingsActivity.stateHibernate)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        etNum.setInputTypeDependingTextVertical(etNum.textVertical);

        statePreResults = getStatePreResults();
        int preResultHeight = metrics.getHeightPreResult((int) preResults.getTextSize());
        int padding = metrics.getPadding();
        if (metrics.getOrientation())
        {
            int etNumHeight = getHeightEtNum(statePreResults, preResultHeight, 4);
            getCreateWindowCalc(revealLayout, statePreResults, padding, etNumHeight, preResultHeight);
        }
        else
        {
            int etNumHeight = getHeightEtNum(statePreResults, preResultHeight, 2);
            getCreateWindowCalc(revealLayout, statePreResults, padding, etNumHeight, preResultHeight);
        }

        if (etNum.exitText || SAVE_TAG.equals("onPause()onStop()onStart()onResume()") || HistoryActivity.history)
        {
            etNum.setText(saveLoadPreferences.loadStringPreferences("EditText", "TEXT", this));
            etNum.textToChange = etNum.getTextChange();
            etNum.cursor = saveLoadPreferences.loadIntegerPreferences("EditText", "CURSOR", this);
            etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextChange())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextChange())));
            etNum.setTextSize();
            etNum.textAfterChange = etNum.getTextChange();
            etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, "", etNum.cursor, false);
            etNum.setCursor(etNum.cursor);
            HistoryActivity.history = false;

            etNum.num = saveLoadPreferences.loadStringPreferences("EditText", "ET_NUM", this);
            getCalculationToPreResult(etNum.getText().toString(), etNum.num);
            if (etNum.textAfterChange.contains("X"))
            {
                btnEqual.setText("F(X)");
            }
            stateX = saveLoadPreferences.loadBooleanPreferences("STATE_CALCULATION", "STATE_X", this);
        }
        else
        {
            etNum.setText("");
            etNum.cursor = 0;
        }
    }

    boolean statePreResults;

    static boolean STATE_APP_RATE;
    static boolean STATE_APP_RATE_LATER;
    static boolean STATE_APP_RATE_DENIED;
    static boolean SAVE_STATE_APP_RATE_LATER = false;

    public void getAppRate()
    {
        if (!STATE_APP_RATE & isOnline(this))
        {
            if (!STATE_APP_RATE_DENIED)
            {
                if (STATE_APP_RATE_LATER)
                {
                    int count = saveLoadPreferences.loadIntegerPreferences("AppRate",
                            "STATE_APP_RATE_LATER_COUNT_START_CALCULATOR", this);
                    if (countStartCalculator - count > 5)
                    {
                        Timer timer = new Timer();
                        AppRateTimerTask timerTask = new AppRateTimerTask();
                        timer.schedule(timerTask, 1000);
                    }
                }
                else
                {
                    if (countStartCalculator > 3)
                    {
                        Timer timer = new Timer();
                        AppRateTimerTask timerTask = new AppRateTimerTask();
                        timer.schedule(timerTask, 1000);
                    }
                }
            }
        }
    }

    class AppRateTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    DialogFragmentAppRate dialogFragmentAppRate = new DialogFragmentAppRate();
                    dialogFragmentAppRate.show(getFragmentManager(), "dialogFragmentAppRate");
                    dialogFragmentAppRate.setCancelable(false);
                }
            });
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        SAVE_TAG = "";
        SAVE_TAG = SAVE_TAG + "onPause()";
        saveLoadPreferences.saveStringPreferences("EditText", "TEXT", etNum.getText().toString(), this);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        saveLoadPreferences.saveIntegerPreferences("EditText", "CURSOR", etNum.cursor, this);
        saveLoadPreferences.saveStringPreferences("Settings", "ANGLE", TAG_ANGLE, this);
        saveLoadPreferences.saveBooleanPreferences("AppRate", "STATE_APP_RATE", STATE_APP_RATE, this);
        saveLoadPreferences.saveBooleanPreferences("AppRate", "STATE_APP_RATE_LATER", STATE_APP_RATE_LATER, this);
        saveLoadPreferences.saveStringPreferences("EditText", "ET_NUM", etNum.num, this);
        if (SAVE_STATE_APP_RATE_LATER)
        {
            saveLoadPreferences.saveIntegerPreferences("AppRate", "STATE_APP_RATE_LATER_COUNT_START_CALCULATOR",
                    countStartCalculator, this);
        }
        saveLoadPreferences.saveBooleanPreferences("AppRate", "STATE_APP_RATE_DENIED", STATE_APP_RATE_DENIED, this);
        saveLoadPreferences.saveBooleanPreferences("STATE_CALCULATION", "STATE_CALCULATION", stateCalculation, this);
        saveLoadPreferences.saveBooleanPreferences("STATE_CALCULATION", "STATE_X", stateX, this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        SAVE_TAG = SAVE_TAG + "onStart()";
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        SAVE_TAG = SAVE_TAG  + "onStop()";
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        SAVE_TAG = SAVE_TAG  + "onDestroy()";
    }


    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    private void makeActionOverflowMenuShown() {
        try
        {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null)
            {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        }
        catch (Exception e)
        {
            //not errors
        }
    }

    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean online = false;
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            online = true;
        }
        return online;
    }

    public void setSlidingPanelClose(boolean panelState)
    {
        if (panelState)
        {
            if (slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
            {
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        }
    }

    com.project.myv.calculator.Button button = new com.project.myv.calculator.Button(this);

    private void getClearAnimation()
    {
        int cx = view.getWidth();
        int cy = view.getHeight();

        float finalRadius = (float) Math.hypot(cx, cy);
        io.codetail.animation.SupportAnimator animator =
                io.codetail.animation.ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        animator.setDuration(500);
        view.setVisibility(View.VISIBLE);
        view.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Blue1));
        animator.start();

        Timer timer = new Timer();
        ClearTimerTask timerTask = new ClearTimerTask();
        timer.schedule(timerTask, animator.getDuration() + 350);
    }

    class ClearTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    etNum.num = etNum.setLongC();
                    preResults.setText("");
                    view.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    private void getErrorAnimation()
    {
        int cx = view.getWidth();
        int cy = view.getHeight();

        float finalRadius = (float) Math.hypot(cx, cy);
        io.codetail.animation.SupportAnimator animator =
                io.codetail.animation.ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        animator.setDuration(500);
        view.setVisibility(View.VISIBLE);
        view.setBackgroundColor(deprecatedMethods.getColor(this, R.color.Magenta));
        view.setText(getResources().getText(R.string.error));
        view.setTextColor(deprecatedMethods.getColor(this, R.color.White));
        view.setGravity(Gravity.CENTER);
        view.setTextSize(metrics.getViewTextSize());
        animator.start();

        Timer timer = new Timer();
        ErrorTimerTask timerTask = new ErrorTimerTask();
        timer.schedule(timerTask, animator.getDuration() + 350);
    }

    class ErrorTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    view.setVisibility(View.INVISIBLE);
                    view.setText("");
                }
            });
        }
    }

    static boolean stateCalculation = false;

    private void getCalculationOrError()
    {
        try
        {
            if (etNum.textToEqual.contains("X") || etNum.getCalculationError(etNum.textToEqual))
            {
                getErrorAnimation();
                stateCalculation = false;
            }
            else
            {
                Calculation.calculationResult = calculation.getCalculation(etNum.textToEqual);
                etNum.calculationResult = etNum.getTextCalculationResultToEqual(String.valueOf(Calculation.calculationResult), etNum.getRounding());
                etNum.calculationResult = etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.calculationResult));
                Map<String, String> listMap = calculation.getListMapOperation(etNum.textToEqual);
                if ((listMap.size() > 0 & etNum.getTextContainsNumber(etNum.textToEqual) || etNum.textToEqual.contains("%"))
                        || etNum.textToEqual.contains("π") || etNum.textToEqual.contains("e"))
                {
                    if (!etNum.textToHistoryEndEqual.equals(etNum.calculationResult) & etNum.textToHistoryEndEqual.length() != 0)
                    {
                        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
                        etNum.startAnimation(animation);
                        etNum.setText(etNum.calculationResult);
                        etNum.setTextSize();
                        etNum.cursor = etNum.getText().length();
                        etNum.setCursor(etNum.cursor);
                        stateCalculation = true;
                        setBaseHistory();
                    }
                }
            }
        }
        catch (Exception e)
        {
            getErrorAnimation();
            stateCalculation = false;
        }
    }


    private void setBaseHistory()
    {
        Map<Integer, String> preferencesMap = saveLoadPreferences.getPreferencesToMapSorted("BaseHistory", this);
        if (preferencesMap.size() < 30)
        {
            String valueOld = "";
            ArrayList<String> listHistory = getListToHistory();
            if (listHistory.size() > 0)
            {
                valueOld = listHistory.get(0);
            }
            String key = preferencesMap.size() + 1 + "";
            String value;
            if (etNum.textToHistoryEndEqual.contains("sin") || etNum.textToHistoryEndEqual.contains("cos") ||
                    etNum.textToHistoryEndEqual.contains("tan"))
            {
                if (etNum.textToHistoryEndEqual.contains("X"))
                {
                    value = etNum.textToHistoryEndEqual + "|" + TAG_ANGLE;
                }
                else
                {
                    value = etNum.textToHistoryEndEqual + " = " + etNum.calculationResult + "|" + TAG_ANGLE;
                }
            }
            else
            {
                if (etNum.textToHistoryEndEqual.contains("X"))
                {
                    value = etNum.textToHistoryEndEqual;
                }
                else
                {
                    value = etNum.textToHistoryEndEqual + " = " + etNum.calculationResult;
                }
            }
            if (!valueOld.equals(value))
            {
                saveLoadPreferences.saveStringPreferences("BaseHistory", key, value, this);
            }
        }
        else
        {
            ArrayList<String> listHistory = getListToHistory();
            String valueOld = listHistory.get(0);
            String key = 1 + "";
            saveLoadPreferences.removeAboutKeyPreferences("BaseHistory", key, this);
            saveLoadPreferences.getUpdateKeyPreference("BaseHistory", this);
            preferencesMap = saveLoadPreferences.getPreferencesToMapSorted("BaseHistory", this);
            key = preferencesMap.size() + 1 + "";
            String value;
            if (etNum.textToHistoryEndEqual.contains("sin") || etNum.textToHistoryEndEqual.contains("cos") ||
                    etNum.textToHistoryEndEqual.contains("tan"))
            {
                if (etNum.textToHistoryEndEqual.contains("X"))
                {
                    value = etNum.textToHistoryEndEqual + "|" + TAG_ANGLE;
                }
                else
                {
                    value = etNum.textToHistoryEndEqual + " = " + etNum.calculationResult + "|" + TAG_ANGLE;
                }
            }
            else
            {
                if (etNum.textToHistoryEndEqual.contains("X"))
                {
                    value = etNum.textToHistoryEndEqual;
                }
                else
                {
                    value = etNum.textToHistoryEndEqual + " = " + etNum.calculationResult;
                }
            }
            if (!valueOld.equals(value))
            {
                saveLoadPreferences.saveStringPreferences("BaseHistory", key, value, this);
            }
        }
    }

    private ArrayList<String> getListToHistory()
    {
        ArrayList<String> list = new ArrayList<>();
        Map<Integer, String> preferencesMap = saveLoadPreferences.getPreferencesToMapSorted("BaseHistory", this);
        for (Map.Entry entry : preferencesMap.entrySet())
        {
            String value = entry.getValue().toString();
            list.add(getValue(value));
        }
        return  list;
    }

    private String getValue(String text)
    {
        boolean pointState = saveLoadPreferences.loadBooleanPreferences("Settings", "POINT", this);
        if (pointState)
        {
            text = text.replace(",", ".");
        }
        else
        {
            text = text.replace(".", ",");
        }
        return text;
    }

    private void onClickStateCalculation(String num)
    {
        if (num.length() > 0)
        {
            stateCalculation = false;
        }
    }

    static boolean stateCalculationCourse;

    @Override
    public boolean onLongClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnC:
                getClearAnimation();
                btnC.setHapticFeedbackEnabled(false);
                button.setVibrator();
                setSlidingPanelClose(panelState);
                stateCalculation = false;
                btnEqual.setText("=");
                stateX = false;
                break;

            case R.id.btnEqual:
                btnEqual.setHapticFeedbackEnabled(false);
                button.setVibrator();
                if (!stateX)
                {
                    etNum.textToChange = etNum.getTextChange();
                    etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
                    etNum.textToHistoryEndEqual = etNum.getTextToHistoryEndEqual(etNum.textToChange);
                    etNum.textToEqual = etNum.getTextToEqual(etNum.textToHistoryEndEqual);
                    stateCalculationCourse = true;
                    getCalculationOrError();
                    if (stateCalculation)
                    {
                        String[] list = etNum.getListResultCalculatingCourse(ExtendEditText.listCalculatingCourse).toArray(
                                new String[etNum.getListResultCalculatingCourse(ExtendEditText.listCalculatingCourse).size()]);

                        if (list.length > 0)
                        {
                            DialogFragmentCalculatingCourse dialogFragment = new DialogFragmentCalculatingCourse().newInstance(list, "MainActivity");
                            dialogFragment.setCancelable(false);
                            dialogFragment.show(getFragmentManager(), "dialogFragmentCalculatingCourse");
                        }
                    }
                    stateCalculationCourse = false;
                }
                else
                {
                    etNum.textToChange = etNum.getTextChange();
                    etNum.textToHistoryEndEqual = etNum.getTextToHistoryEndEqual(etNum.textToChange);
                    etNum.textToEqual = etNum.getTextToEqual(etNum.textToHistoryEndEqual);
                    getCalculationOrErrorGraph();
                }
                break;
        }
        return true;
    }


    public void getClickVibration()
    {
        button.setVibrator();
    }

    public void getNegativeClick()
    {
        getClearAnimation();
        button.setVibrator();
        setSlidingPanelClose(panelState);
        stateCalculation = false;
    }

    private void getClickC(boolean stateCalculation)
    {
        if (stateCalculation)
        {
            getClearAnimation();
            btnC.setHapticFeedbackEnabled(false);
            button.setVibrator();
            setSlidingPanelClose(panelState);
            MainActivity.stateCalculation = false;
            preResults.setText("");
            btnEqual.setText("=");
            stateX = false;
        }
        else
        {
            button.setVibrator();
            setSlidingPanelClose(panelState);
            etNum.textToChange = etNum.getTextChange();
            etNum.setBooleanAdd(false);
            etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
            etNum.num = etNum.setTextCl();
            if (etNum.num.length() > 0)
            {
                etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
                etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
                etNum.setTextSize();
            }
            etNum.textAfterChange = etNum.getTextChange();
            etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
            etNum.setCursor(etNum.cursor);
            getCalculationToPreResult(etNum.getText().toString(), etNum.num);
            if (etNum.num.equals("X") & !etNum.textAfterChange.contains("X"))
            {
                btnEqual.setText("=");
                stateX = false;
            }
        }
    }

    public static boolean stateX;

    private void getCalculationToPreResult(String text, String num)
    {
        String result;
        if (text.contains("X") || num.equals("X") || num.equals("×X") || etNum.getCalculationError(text))
        {
            preResults.setTextColor(deprecatedMethods.getColor(this, R.color.Grey3));
        }
        else
        {
            try
            {
                if (num.length() >= 1 & statePreResults || SAVE_TAG.equals("nullonCreate()onStart()onResume()"))
                {
                    text = etNum.getTextToHistoryEndEqual(text);
                    text = etNum.getTextToEqual(text);
                    double res = calculation.getCalculation(text);
                    result = etNum.getTextCalculationResultToEqual(String.valueOf(res), etNum.getRounding());
                    result = etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(result));
                    if (result.equals("0") & text.length() == 0)
                    {
                        preResults.setText("");
                    }
                    else
                    {
                        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
                        preResults.startAnimation(animation);
                        preResults.setTextColor(deprecatedMethods.getColor(this, R.color.Grey2));
                        preResults.setText(result);
                    }
                    etNum.getShowMessageRounding(text, result);
                }
            }
            catch (Exception e)
            {
                preResults.setTextColor(deprecatedMethods.getColor(this, R.color.Grey3));
            }
        }
    }

    private void getClickOne()
    {
        button.setVibrator();
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getNumbers(etNum.setOne(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickTwo()
    {
        button.setVibrator();
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getNumbers(etNum.setTwo(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickThree()
    {
        button.setVibrator();
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getNumbers(etNum.setThree(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickFour()
    {
        button.setVibrator();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.textToChange = etNum.getTextChange();
        etNum.num = etNum.getNumbers(etNum.setFour(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickFive()
    {
        button.setVibrator();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.textToChange = etNum.getTextChange();
        etNum.num = etNum.getNumbers(etNum.setFive(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickSix()
    {
        button.setVibrator();
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getNumbers(etNum.setSix(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickSeven()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getNumbers(etNum.setSeven(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickEight()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getNumbers(etNum.setEight(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickNine()
    {
        button.setVibrator();
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getNumbers(etNum.setNine(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        setSlidingPanelClose(panelState);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickZero()
    {
        button.setVibrator();
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getZero(etNum.setZero(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickPoint()
    {
        button.setVibrator();
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getPoint(etNum.setPoint(etNum.getStatePoint(etNum.point)), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        etNum.num = etNum.setLimitedNumber(etNum.textToChange, etNum.num, etNum.cursor, etNum.limitedNumber);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickBr()
    {
        button.setVibrator();
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getBr(etNum.setBr(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickSin()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getTrigonometricOperation_Sqrt_Log_X(etNum.setSin(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickAsin()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getTrigonometricOperation_Sqrt_Log_X(etNum.setAsin(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickCos()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getTrigonometricOperation_Sqrt_Log_X(etNum.setCos(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickAcos()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getTrigonometricOperation_Sqrt_Log_X(etNum.setAcos(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickTan()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getTrigonometricOperation_Sqrt_Log_X(etNum.setTan(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickAtan()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getTrigonometricOperation_Sqrt_Log_X(etNum.setAtan(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickLn()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getTrigonometricOperation_Sqrt_Log_X(etNum.setLn(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickLog()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getTrigonometricOperation_Sqrt_Log_X(etNum.setLog(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickP()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getP_E(etNum.setP(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickE()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getP_E(etNum.setE(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getClickSqrt()
    {
        button.setVibrator();
        setSlidingPanelClose(panelState);
        etNum.textToChange = etNum.getTextChange();
        etNum.setBooleanAdd(true);
        etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
        etNum.num = etNum.getTrigonometricOperation_Sqrt_Log_X(etNum.setSqrt(), etNum.textToChange, etNum.cursor);
        etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
        onClickStateCalculation(etNum.num);
        if (etNum.num.length() > 0)
        {
            etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
            etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
            etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
            etNum.setTextSize();
        }
        etNum.textAfterChange = etNum.getTextChange();
        etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
        etNum.setCursor(etNum.cursor);
        getCalculationToPreResult(etNum.getText().toString(), etNum.num);
    }

    private void getCalculationOrErrorGraph()
    {
        try
        {
            String text = etNum.textToEqual.replace("X", "(0.1)");
            Calculation.calculationResult = calculation.getCalculation(text);
            if (etNum.getCalculationError(text))
            {
                getErrorAnimation();
                stateCalculation = false;
            }
            else
            {
                setBaseHistory();
                stateCalculation = true;
                intent = new Intent(this, GraphActivity.class);
                intent.putExtra("TEXT", etNum.textToEqual);
                startActivity(intent);
            }
        }
        catch (Exception e)
        {
            getErrorAnimation();
            stateCalculation = false;
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnC:
                getClickC(stateCalculation);
                break;

            case R.id.btnPr:
                button.setVibrator();
                setSlidingPanelClose(panelState);
                etNum.textToChange = etNum.getTextChange();
                etNum.setBooleanAdd(true);
                etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
                etNum.num = etNum.getPow_Pr_Fac_X(etNum.setPr(), etNum.textToChange, etNum.cursor);
                etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
                onClickStateCalculation(etNum.num);
                if (etNum.num.length() > 0)
                {
                    etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
                    etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
                    etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
                    etNum.setTextSize();
                }
                etNum.textAfterChange = etNum.getTextChange();
                etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
                etNum.setCursor(etNum.cursor);
                getCalculationToPreResult(etNum.getText().toString(), etNum.num);
                break;

            case R.id.btnDiv:
                button.setVibrator();
                setSlidingPanelClose(panelState);
                etNum.textToChange = etNum.getTextChange();
                etNum.setBooleanAdd(true);
                etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
                etNum.num = etNum.getAdd_Mult_Div(etNum.setDiv(), etNum.textToChange, etNum.cursor);
                etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
                onClickStateCalculation(etNum.num);
                if (etNum.num.length() > 0)
                {
                    etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
                    etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
                    etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
                    etNum.setTextSize();
                }
                etNum.textAfterChange = etNum.getTextChange();
                etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
                etNum.setCursor(etNum.cursor);
                getCalculationToPreResult(etNum.getText().toString(), etNum.num);
                break;

            case R.id.btnPow_X:
                button.setVibrator();
                setSlidingPanelClose(panelState);
                etNum.textToChange = etNum.getTextChange();
                etNum.setBooleanAdd(true);
                etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
                etNum.num = etNum.getPow_Pr_Fac_X(etNum.setPow_X(etNum.textToChange, etNum.cursor), etNum.textToChange, etNum.cursor);
                etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
                onClickStateCalculation(etNum.num);
                if (etNum.num.length() > 0)
                {
                    etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
                    etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
                    etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
                    etNum.setTextSize();
                }
                etNum.textAfterChange = etNum.getTextChange();
                etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
                etNum.setCursor(etNum.cursor);
                if (etNum.num.equals("X") || etNum.num.equals("×X"))
                {
                    btnEqual.setText("F(X)");
                    stateX = true;
                }
                getCalculationToPreResult(etNum.getText().toString(), etNum.num);
                break;

            case R.id.btnOne:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickOne();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickOne();
                }
                break;

            case R.id.btnTwo:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickTwo();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickTwo();
                }
                break;

            case R.id.btnThree:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickThree();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickThree();
                    btnEqual.setText("=");
                }
                break;

            case R.id.btnMult:
                button.setVibrator();
                setSlidingPanelClose(panelState);
                etNum.setBooleanAdd(true);
                etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
                etNum.textToChange = etNum.getTextChange();
                etNum.num = etNum.getAdd_Mult_Div(etNum.setMult(), etNum.textToChange, etNum.cursor);
                etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
                onClickStateCalculation(etNum.num);
                if (etNum.num.length() > 0)
                {
                    etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
                    etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
                    etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
                    etNum.setTextSize();
                }
                etNum.textAfterChange = etNum.getTextChange();
                etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
                etNum.setCursor(etNum.cursor);
                getCalculationToPreResult(etNum.getText().toString(), etNum.num);
                break;

            case R.id.btnFour:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickFour();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickFour();
                    btnEqual.setText("=");
                }
                break;

            case R.id.btnFive:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickFive();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickFive();
                }
                break;

            case R.id.btnSix:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickSix();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickSix();
                }
                break;

            case R.id.btnSub:
                button.setVibrator();
                etNum.textToChange = etNum.getTextChange();
                etNum.setBooleanAdd(true);
                etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
                etNum.num = etNum.getSub(etNum.setSub(), etNum.textToChange, etNum.cursor);
                etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
                onClickStateCalculation(etNum.num);
                if (etNum.num.length() > 0)
                {
                    etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
                    etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
                    etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
                    etNum.setTextSize();
                }
                etNum.textAfterChange = etNum.getTextChange();
                etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
                etNum.setCursor(etNum.cursor);
                getCalculationToPreResult(etNum.getText().toString(), etNum.num);
                break;

            case R.id.btnSeven:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickSeven();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickSeven();
                }
                break;

            case R.id.btnEight:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickEight();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickEight();
                }
                break;

            case R.id.btnNine:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickNine();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickNine();
                }
                break;

            case R.id.btnAdd:
                button.setVibrator();
                etNum.textToChange = etNum.getTextChange();
                etNum.setBooleanAdd(true);
                etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
                etNum.num = etNum.getAdd_Mult_Div(etNum.setAdd(), etNum.textToChange, etNum.cursor);
                etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
                onClickStateCalculation(etNum.num);
                if (etNum.num.length() > 0)
                {
                    etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
                    etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
                    etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
                    etNum.setTextSize();
                }
                etNum.textAfterChange = etNum.getTextChange();
                etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
                etNum.setCursor(etNum.cursor);
                getCalculationToPreResult(etNum.getText().toString(), etNum.num);
                break;

            case R.id.btnZero:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickZero();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickZero();
                }
                break;

            case R.id.btnPoint:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickPoint();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickPoint();
                }
                break;

            case R.id.btnBr:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickBr();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickBr();
                }
                break;

            case R.id.btnEqual:
                button.setVibrator();
                if (!stateX)
                {
                    etNum.textToChange = etNum.getTextChange();
                    etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
                    etNum.textToHistoryEndEqual = etNum.getTextToHistoryEndEqual(etNum.textToChange);
                    etNum.textToEqual = etNum.getTextToEqual(etNum.textToHistoryEndEqual);
                    getCalculationOrError();
                    ExtendEditText.listCalculatingCourse.clear();
                }
                else
                {
                    etNum.textToChange = etNum.getTextChange();
                    etNum.textToHistoryEndEqual = etNum.getTextToHistoryEndEqual(etNum.textToChange);
                    etNum.textToEqual = etNum.getTextToEqual(etNum.textToHistoryEndEqual);
                    getCalculationOrErrorGraph();
                }
                break;

            case R.id.btnSin:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickSin();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickSin();
                }
                break;

            case R.id.btnAsin:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickAsin();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickAsin();
                }
                break;

            case R.id.btnCos:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickCos();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickCos();
                }
                break;

            case R.id.btnAcos:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickAcos();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickAcos();
                }
                break;

            case R.id.btnTan:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickTan();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickTan();
                }
                break;

            case R.id.btnAtan:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickAtan();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickAtan();
                }
                break;

            case R.id.btnSqrt:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickSqrt();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickSqrt();
                }
                break;

            case R.id.btnFac:
                button.setVibrator();
                setSlidingPanelClose(panelState);
                etNum.textToChange = etNum.getTextChange();
                etNum.setBooleanAdd(true);
                etNum.cursor = etNum.getCursorAfterTouch(etNum.onTouch, etNum.cursor);
                etNum.num = etNum.getPow_Pr_Fac_X(etNum.setFac(), etNum.textToChange, etNum.cursor);
                etNum.num = etNum.setLimitedText(etNum.num, etNum.limitedText, false);
                onClickStateCalculation(etNum.num);
                if (etNum.num.length() > 0)
                {
                    etNum.setTextAdd(etNum.num, etNum.textToChange, etNum.cursor);
                    etNum.setSplittingLevelText(etNum.getSplittingLevelText(etNum.getListNumbersForSplitting(etNum.getTextEtNum())));
                    etNum.setWrapText(etNum.getWrapText(etNum.getListNumbersForWrap(etNum.getTextEtNum())));
                    etNum.setTextSize();
                }
                etNum.textAfterChange = etNum.getTextChange();
                etNum.cursor = etNum.getCursor(etNum.textToChange, etNum.textAfterChange, etNum.num, etNum.cursor, etNum.add);
                etNum.setCursor(etNum.cursor);
                getCalculationToPreResult(etNum.getText().toString(), etNum.num);
                break;

            case R.id.btnLn:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickLn();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickLn();
                }
                break;

            case R.id.btnLog:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickLog();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickLog();
                }
                break;

            case R.id.btnP:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickP();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickP();
                }
                break;

            case R.id.btnE:
                if (stateCalculation)
                {
                    etNum.setLongC();
                    getClickE();
                    btnEqual.setText("=");
                }
                else
                {
                    getClickE();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        MenuItem item = menu.findItem(R.id.action_angle);
        switch (TAG_ANGLE)
        {
            case "RAD":
                item.setIcon(R.drawable.ic_action_rad);
                break;
            case "GRAD":
                item.setIcon(R.drawable.ic_action_grad);
                break;
            case "DEG":
                item.setIcon(R.drawable.ic_action_deg);
                break;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    static String TAG_ANGLE;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_angle:
                switch (TAG_ANGLE)
                {
                    case "RAD":
                        item.setIcon(R.drawable.ic_action_grad);
                        TAG_ANGLE = "GRAD";
                        break;
                    case "GRAD":
                        item.setIcon(R.drawable.ic_action_deg);
                        TAG_ANGLE = "DEG";
                        break;
                    case "DEG":
                        item.setIcon(R.drawable.ic_action_rad);
                        TAG_ANGLE = "RAD";
                        break;
                }
                getCalculationToPreResult(etNum.getText().toString(), etNum.num);
                button.setVibrator();
                break;

            case R.id.action_history:
                intent = new Intent(this, HistoryActivity.class);
                button.setVibrator();
                startActivity(intent);
                break;

            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                button.setVibrator();
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}