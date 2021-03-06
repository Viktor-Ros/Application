package com.example.myapplication3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class DialogButtonOff {

    private AlertDialog eatingDialog;
    private EditText userEatingName;



    private AlertDialog productDialog;
    private EditText usereat;
    private EditText usermass;
    private EditText userbelk;
    private EditText userzhir;
    private EditText userugl;


    public DialogButtonOff(AlertDialog alertDialog, final EditText usereat, final EditText usermass, final EditText userbelk, final EditText userzhir, final EditText userugl) {

        this.productDialog = alertDialog;
        this.usereat = usereat;
        this.usermass = usermass;
        this.userbelk = userbelk;
        this.userzhir = userzhir;
        this.userugl = userugl;

    }

    public void ButtonOkOffProduct(){

        productDialog.setOnShowListener(new DialogInterface.OnShowListener() {


            @Override
            public void onShow(DialogInterface dialog) {
                final Button buttonPositive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                buttonPositive.setEnabled(false);

                TextWatcher loginTextWatcher = new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        String userEatDialog = usereat.getText().toString().trim();
                        String userMassDialog = usermass.getText().toString().trim();
                        String userBelkDialog = userbelk.getText().toString().trim();
                        String userZhirDialog = userzhir.getText().toString().trim();
                        String userUglDialog = userugl.getText().toString().trim();

                        buttonPositive.setEnabled(!userEatDialog.isEmpty()
                                && !userMassDialog.isEmpty() && !userBelkDialog.isEmpty()
                                && !userZhirDialog.isEmpty() && !userUglDialog.isEmpty());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                };

                usereat.addTextChangedListener(loginTextWatcher);
                usermass.addTextChangedListener(loginTextWatcher);
                userbelk.addTextChangedListener(loginTextWatcher);
                userzhir.addTextChangedListener(loginTextWatcher);
                userugl.addTextChangedListener(loginTextWatcher);
            }
        });
    }

    public DialogButtonOff(AlertDialog alertDialog, final EditText userEatingName) {

        this.eatingDialog = alertDialog;
        this.userEatingName = userEatingName;

    }

    public void ButtonOkOffEating(){

        eatingDialog.setOnShowListener(new DialogInterface.OnShowListener() {


            @Override
            public void onShow(DialogInterface dialog) {
                final Button buttonPositive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                buttonPositive.setEnabled(false);

                TextWatcher loginTextWatcher = new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        String userEatDialog = userEatingName.getText().toString().trim();


                        buttonPositive.setEnabled(!userEatDialog.isEmpty());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                };

                userEatingName.addTextChangedListener(loginTextWatcher);
            }
        });
    }


}
