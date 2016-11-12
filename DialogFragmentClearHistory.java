package com.project.myv.calculator;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class DialogFragmentClearHistory extends DialogFragment implements OnClickListener
{
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        android.support.v7.app.AlertDialog.Builder adb = new android.support.v7.app.AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle)
                .setTitle(R.string.title_dialog_cl_history)
                .setPositiveButton(R.string.yes, this)
                .setNegativeButton(R.string.no, this)
                .setMessage(R.string.message_text);

        return adb.show();
    }

    public void onClick(DialogInterface dialog, int which)
    {
        switch (which)
        {
            case Dialog.BUTTON_POSITIVE:
                ((HistoryActivity)getActivity()).getPositiveClick();
                break;
            case Dialog.BUTTON_NEGATIVE:
                ((HistoryActivity)getActivity()).getNegativeClick();
                break;
        }
    }
}
