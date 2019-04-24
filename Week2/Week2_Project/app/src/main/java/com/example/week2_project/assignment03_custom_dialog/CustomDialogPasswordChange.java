package com.example.week2_project.assignment03_custom_dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.week2_project.R;

public class CustomDialogPasswordChange extends DialogFragment implements View.OnClickListener {

    private Button doChangePasswordButton;
    private Button doCloseDialogButton;
    private CheckBox dontWatchChangePasswordCheckBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_dialog_password_change, container, false);
        initView(view);

        return view;
    }

    void initView(View view) {

        doChangePasswordButton = view.findViewById(R.id.btn_do_change_pw);
        doCloseDialogButton = view.findViewById(R.id.bt_do_close_custom_dialog_pw_change);
        dontWatchChangePasswordCheckBox = view.findViewById(R.id.cb_dont_watch_custom_dialog_pw_change);

        doChangePasswordButton.setOnClickListener(this);
        doCloseDialogButton.setOnClickListener(this);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog PasswordChange = super.onCreateDialog(savedInstanceState);
        PasswordChange.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return PasswordChange;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_do_change_pw:
                Intent changePasswordIntent = new Intent(getContext(), PasswordChangeActivity.class);
                startActivity(changePasswordIntent);
                break;
            case R.id.bt_do_close_custom_dialog_pw_change:
                if (dontWatchChangePasswordCheckBox.isChecked()) {
                    SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(getString(R.string.preference_watch_key), true);
                    editor.commit();
                }
                dismiss();
                break;
        }
    }
}
