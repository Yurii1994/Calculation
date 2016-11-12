package com.project.myv.calculator;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, SeekBar.OnSeekBarChangeListener
{
    SaveLoadPreferences saveLoadPreferences = new SaveLoadPreferences();
    DeprecatedMethods deprecatedMethods = new DeprecatedMethods();
    Button button = new Button(this);
    static int countStartSettings = 0;
    SwitchCompat propertiesPaste;
    SwitchCompat exitText;
    SwitchCompat textVertical;
    SwitchCompat limitedText;
    SwitchCompat limitedNumber;
    SwitchCompat hibernate;
    TextView viewSeekBarProgress;
    public  static SeekBar seekBar;
    static int progressSeekBar;
    static boolean stateHibernate = false;
    String versionName;
    LinearLayout open_source;

    TextView hint_rounding;
    int ROUNDING;
    String rounding_hint1;
    String rounding_hint2;
    String rounding_hint;
    DialogFragmentRounding dialogFragmentRounding;

    SwitchCompat switchPoint;
    SwitchCompat switchPreResults;
    int countChangePreResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogFragmentRounding = new DialogFragmentRounding();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            String appName = getString(R.string.app_name);
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            int color = deprecatedMethods.getColor(this, R.color.Blue1);
            ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(appName, icon, color);
            setTaskDescription(taskDesc);
        }

        setContentView(R.layout.activity_settings);

        try
        {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        }
        catch (Exception e)
        {
            //not error
        }

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView settings_category1 = (TextView)findViewById(R.id.settings_category1);
        settings_category1.setTypeface(MainActivity.etNum.faceMedium);

        TextView title_rounding = (TextView)findViewById(R.id.title_rounding);
        title_rounding.setTypeface(MainActivity.etNum.faceRegular);

        hint_rounding = (TextView)findViewById(R.id.hint_rounding);
        hint_rounding.setTypeface(MainActivity.etNum.faceRegular);

        TextView title_point = (TextView)findViewById(R.id.title_point);
        title_point.setTypeface(MainActivity.etNum.faceRegular);

        TextView hint_point = (TextView)findViewById(R.id.hint_point);
        hint_point.setTypeface(MainActivity.etNum.faceRegular);

        TextView title_pre_results = (TextView)findViewById(R.id.title_pre_results);
        title_pre_results.setTypeface(MainActivity.etNum.faceRegular);

        TextView hint_pre_results = (TextView)findViewById(R.id.hint_pre_results);
        hint_pre_results.setTypeface(MainActivity.etNum.faceRegular);

        TextView settings_category2 = (TextView)findViewById(R.id.settings_category2);
        settings_category2.setTypeface(MainActivity.etNum.faceMedium);

        TextView title1 = (TextView)findViewById(R.id.title1);
        title1.setTypeface(MainActivity.etNum.faceRegular);

        TextView hint1 = (TextView)findViewById(R.id.hint1);
        hint1.setTypeface(MainActivity.etNum.faceRegular);

        TextView settings_category3 = (TextView)findViewById(R.id.settings_category3);
        settings_category3.setTypeface(MainActivity.etNum.faceMedium);

        TextView title2 = (TextView)findViewById(R.id.title2);
        title2.setTypeface(MainActivity.etNum.faceRegular);

        TextView hint2 = (TextView)findViewById(R.id.hint2);
        hint2.setTypeface(MainActivity.etNum.faceRegular);

        TextView title3 = (TextView)findViewById(R.id.title3);
        title3.setTypeface(MainActivity.etNum.faceRegular);

        TextView hint3 = (TextView)findViewById(R.id.hint3);
        hint3.setTypeface(MainActivity.etNum.faceRegular);

        TextView title4 = (TextView)findViewById(R.id.title4);
        title4.setTypeface(MainActivity.etNum.faceRegular);

        TextView hint4 = (TextView)findViewById(R.id.hint4);
        hint4.setTypeface(MainActivity.etNum.faceRegular);

        TextView title5 = (TextView)findViewById(R.id.title5);
        title5.setTypeface(MainActivity.etNum.faceRegular);

        TextView hint5 = (TextView)findViewById(R.id.hint5);
        hint5.setTypeface(MainActivity.etNum.faceRegular);

        TextView settings_category4 = (TextView)findViewById(R.id.settings_category4);
        settings_category4.setTypeface(MainActivity.etNum.faceMedium);

        TextView title6 = (TextView)findViewById(R.id.title6);
        title6.setTypeface(MainActivity.etNum.faceRegular);

        viewSeekBarProgress = (TextView)findViewById(R.id.viewSeekBarProgress);
        viewSeekBarProgress.setTypeface(MainActivity.etNum.faceRegular);

        TextView title7 = (TextView)findViewById(R.id.title7);
        title7.setTypeface(MainActivity.etNum.faceRegular);

        TextView hint7 = (TextView)findViewById(R.id.hint7);
        hint7.setTypeface(MainActivity.etNum.faceRegular);

        TextView settings_category5 = (TextView)findViewById(R.id.settings_category5);
        settings_category5.setTypeface(MainActivity.etNum.faceMedium);

        TextView title8 = (TextView)findViewById(R.id.title8);
        title8.setTypeface(MainActivity.etNum.faceRegular);

        TextView title9 = (TextView)findViewById(R.id.title9);
        title9.setTypeface(MainActivity.etNum.faceRegular);
        String text = title9.getText() + " " + versionName;
        title9.setText(text);

        open_source = (LinearLayout)findViewById(R.id.open_source);

        countStartSettings = saveLoadPreferences.loadIntegerPreferences("Settings", "COUNT_START_SETTINGS", this);
        countStartSettings++;
        saveLoadPreferences.saveIntegerPreferences("Settings", "COUNT_START_SETTINGS", countStartSettings, this);

        propertiesPaste = (SwitchCompat)findViewById(R.id.propertiesPaste);
        propertiesPaste.setOnClickListener(this);

        exitText = (SwitchCompat)findViewById(R.id.exitText);
        exitText.setOnClickListener(this);

        textVertical = (SwitchCompat)findViewById(R.id.textVertical);
        textVertical.setOnClickListener(this);

        limitedText = (SwitchCompat)findViewById(R.id.limitedText);
        limitedText.setOnClickListener(this);

        limitedNumber = (SwitchCompat)findViewById(R.id.limitedNumber);
        limitedNumber.setOnClickListener(this);

        limitedNumber = (SwitchCompat)findViewById(R.id.limitedNumber);
        limitedNumber.setOnClickListener(this);

        hibernate = (SwitchCompat)findViewById(R.id.hibernate);
        hibernate.setOnClickListener(this);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(80);
        seekBar.setOnSeekBarChangeListener(this);

        switchPoint = (SwitchCompat)findViewById(R.id.switch_point);
        switchPoint.setOnClickListener(this);

        switchPreResults = (SwitchCompat)findViewById(R.id.switch_pre_results);
        switchPreResults.setOnClickListener(this);

        if (countStartSettings == 1)
        {
            propertiesPaste.setChecked(true);
            exitText.setChecked(true);
            exitText.setChecked(true);
            exitText.setChecked(true);
            textVertical.setChecked(true);
            limitedText.setChecked(true);
            limitedNumber.setChecked(true);
            progressSeekBar = (int) (80 * 0.25);
            button.milliseconds = progressSeekBar;
            seekBar.setProgress(progressSeekBar);
            hibernate.setChecked(false);
            ROUNDING = 6;
            saveLoadPreferences.saveBooleanPreferences("Settings", "ROUNDING_6", true, this);
            switchPoint.setChecked(false);
            saveLoadPreferences.saveBooleanPreferences("Settings", "POINT", false, this);
            switchPreResults.setChecked(true);
            saveLoadPreferences.saveBooleanPreferences("Settings", "PRE_RESULTS", true, this);
        }
        else
        {
            HistoryActivity.propertiesPaste = saveLoadPreferences.loadBooleanPreferences("Settings", "PROPERTIES_PASTE", this);
            propertiesPaste.setChecked(HistoryActivity.propertiesPaste);

            MainActivity.etNum.exitText = saveLoadPreferences.loadBooleanPreferences("Settings", "EXIT_TEXT", this);
            exitText.setChecked(MainActivity.etNum.exitText);

            MainActivity.etNum.textVertical = saveLoadPreferences.loadBooleanPreferences("Settings", "TEXT_VERTICAL", this);
            textVertical.setChecked(MainActivity.etNum.textVertical);

            MainActivity.etNum.limitedText = saveLoadPreferences.loadBooleanPreferences("Settings", "LIMITED_TEXT", this);
            limitedText.setChecked(MainActivity.etNum.limitedText);

            MainActivity.etNum.limitedNumber = saveLoadPreferences.loadBooleanPreferences("Settings", "LIMITED_NUMBER", this);
            limitedNumber.setChecked(MainActivity.etNum.limitedNumber);

            progressSeekBar = saveLoadPreferences.loadIntegerPreferences("Settings", "PROGRESS_SEEKBAR", this);
            button.milliseconds = progressSeekBar;
            seekBar.setProgress(progressSeekBar);

            stateHibernate = saveLoadPreferences.loadBooleanPreferences("Settings", "STATE_HIBERNATE", this);
            hibernate.setChecked(stateHibernate);

            int countStartRounding = saveLoadPreferences.loadIntegerPreferences("Settings", "ROUNDING_COUNT_START", this);
            if (countStartRounding == 0)
            {
                ROUNDING = 6;
            }
            else
            {
                ROUNDING = getRounding(this);
            }

            boolean statePoint = saveLoadPreferences.loadBooleanPreferences("Settings", "POINT", this);
            switchPoint.setChecked(statePoint);

            countChangePreResult = saveLoadPreferences.loadIntegerPreferences("Settings", "COUNT_CHANGE_PRE_RESULTS", this);
            if (countChangePreResult >= 1)
            {
                boolean statePreResults = saveLoadPreferences.loadBooleanPreferences("Settings", "PRE_RESULTS", this);
                switchPreResults.setChecked(statePreResults);
            }
            else
            {
                switchPreResults.setChecked(true);
                saveLoadPreferences.saveBooleanPreferences("Settings", "PRE_RESULTS", true, this);
            }

        }

        rounding_hint1 = getResources().getText(R.string.rounding_hint1).toString();
        rounding_hint2 = getResources().getText(R.string.rounding_hint2).toString();
        rounding_hint = getHintRounding(ROUNDING);
        hint_rounding.setText(rounding_hint);
    }

    private String getHintRounding(int ROUNDING)
    {
        String rounding_hint;
        if (ROUNDING != 0)
        {
            rounding_hint = rounding_hint1 + " " + ROUNDING + " " + rounding_hint2;
        }
        else
        {
            rounding_hint = getResources().getText(R.string.rounding_not).toString();
        }
        return rounding_hint;
    }

    public void getPositiveClickRounding()
    {
        ROUNDING = getRounding(this);
        rounding_hint = getHintRounding(ROUNDING);
        hint_rounding.setText(rounding_hint);
    }

    public int getRounding(Context context)
    {
        int rounding = 0;
        boolean button1 = saveLoadPreferences.loadBooleanPreferences("Settings", "ROUNDING_NOT", context);
        boolean button2 = saveLoadPreferences.loadBooleanPreferences("Settings", "ROUNDING_2", context);
        boolean button3 = saveLoadPreferences.loadBooleanPreferences("Settings", "ROUNDING_4", context);
        boolean button4 = saveLoadPreferences.loadBooleanPreferences("Settings", "ROUNDING_6", context);
        boolean button5 = saveLoadPreferences.loadBooleanPreferences("Settings", "ROUNDING_8", context);
        boolean button6 = saveLoadPreferences.loadBooleanPreferences("Settings", "ROUNDING_10", context);
        boolean button7 = saveLoadPreferences.loadBooleanPreferences("Settings", "ROUNDING_12", context);
        if (button1)
        {
            rounding = 0;
        }
        else
        if (button2)
        {
            rounding = 2;
        }
        else
        if (button3)
        {
            rounding = 4;
        }
        else
        if (button4)
        {
            rounding = 6;
        }
        else
        if (button5)
        {
            rounding = 8;
        }
        else
        if (button6)
        {
            rounding = 10;
        }
        else
        if (button7)
        {
            rounding = 12;
        }
        return rounding;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            onBackPressed();
            button.setVibrator();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.propertiesPaste:
                HistoryActivity.propertiesPaste = propertiesPaste.isChecked();
                break;

            case R.id.exitText:
                MainActivity.etNum.exitText = exitText.isChecked();
                break;

            case R.id.textVertical:
                MainActivity.etNum.textVertical = textVertical.isChecked();
                break;

            case R.id.limitedText:
                MainActivity.etNum.limitedText = limitedText.isChecked();
                break;

            case R.id.limitedNumber:
                MainActivity.etNum.limitedNumber = limitedText.isChecked();
                break;

            case R.id.open_source:
                displayLicensesDialogFragment();
                break;

            case R.id.rounding:
                dialogFragmentRounding.show(getFragmentManager(), "dialogFragment");
                dialogFragmentRounding.setCancelable(false);
                break;

            case R.id.switch_point:
                MainActivity.etNum.point  = switchPoint.isChecked();
                MainActivity.btnPoint.setText(MainActivity.etNum.getStatePoint(switchPoint.isChecked()));
                setEtNumTextPoint(switchPoint.isChecked());
                break;

            case R.id.switch_pre_results:
                countChangePreResult++;
                saveLoadPreferences.saveIntegerPreferences("Settings", "COUNT_CHANGE_PRE_RESULTS", countChangePreResult, this);
                break;
        }
    }

    private void setEtNumTextPoint(boolean point)
    {
        String str = MainActivity.etNum.getText().toString();
        if (str.length() > 0)
        {
            if (point)
            {
                str = str.replace(",", ".");
            }
            else
            {
                str = str.replace(".", ",");
            }
            saveLoadPreferences.saveStringPreferences("EditText", "TEXT", str, this);
        }
    }

    public void getVibration()
    {
        button.setVibrator();
    }

    @Override
    public boolean onLongClick(View v)
    {
        switch (v.getId())
        {
            case R.id.propertiesPaste:
                HistoryActivity.propertiesPaste = propertiesPaste.isChecked();
                break;

            case R.id.exitText:
                MainActivity.etNum.exitText = exitText.isChecked();
                break;

            case R.id.textVertical:
                MainActivity.etNum.textVertical = textVertical.isChecked();
                break;

            case R.id.limitedText:
                MainActivity.etNum.limitedText = limitedText.isChecked();
                break;

            case R.id.limitedNumber:
                MainActivity.etNum.limitedText = limitedText.isChecked();
                break;

            case R.id.switch_point:
                MainActivity.etNum.point  = switchPoint.isChecked();
                MainActivity.btnPoint.setText(MainActivity.etNum.getStatePoint(switchPoint.isChecked()));
                setEtNumTextPoint(switchPoint.isChecked());
                break;

            case R.id.switch_pre_results:
                countChangePreResult++;
                saveLoadPreferences.saveIntegerPreferences("Settings", "COUNT_CHANGE_PRE_RESULTS", countChangePreResult, this);
                break;
        }
        return true;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        saveLoadPreferences.saveBooleanPreferences("Settings", "PROPERTIES_PASTE", propertiesPaste.isChecked(), this);
        saveLoadPreferences.saveBooleanPreferences("Settings", "TEXT_VERTICAL", textVertical.isChecked(), this);
        saveLoadPreferences.saveBooleanPreferences("Settings", "LIMITED_TEXT", limitedText.isChecked(), this);
        saveLoadPreferences.saveBooleanPreferences("Settings", "LIMITED_NUMBER", limitedNumber.isChecked(), this);
        saveLoadPreferences.saveBooleanPreferences("Settings", "EXIT_TEXT", exitText.isChecked(), this);
        saveLoadPreferences.saveIntegerPreferences("Settings", "PROGRESS_SEEKBAR", progressSeekBar, this);
        saveLoadPreferences.saveBooleanPreferences("Settings", "STATE_HIBERNATE", hibernate.isChecked(), this);
        saveLoadPreferences.saveBooleanPreferences("Settings", "POINT", switchPoint.isChecked(), this);
        saveLoadPreferences.saveBooleanPreferences("Settings", "PRE_RESULTS", switchPreResults.isChecked(), this);
    }

    int progressInPercent;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        String strProgress = "";
        if (progressSeekBar != progress)
        {
            float a = 100f / seekBar.getMax();
            progressInPercent = Math.round(a * progress);
            strProgress = "" + progressInPercent + "%";
        }
        progressSeekBar = progress;
        viewSeekBarProgress.setText(strProgress);
        button.milliseconds = progressSeekBar;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        viewSeekBarProgress.setText("");
        MainActivity.etNum.showToastMessage(getResources().getText(R.string.intensity_vibration)+ " " + progressInPercent + "%");
        button.setVibrator();
    }

    private void displayLicensesDialogFragment()
    {
        DialogFragmentLicenses dialog = DialogFragmentLicenses.newInstance();
        dialog.show(getFragmentManager(), "LicensesDialog");
    }
}
