package com.example.myapplication3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    private Button button;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout3;
    private TextView tvBelkResult;
    private TextView tvZhirResult;
    private TextView tvUglResult;
    private TextView tvKallResult;
    private double belk1, belk2, zhir1, zhir2, ugl1, ugl2, kall1, kall2;
    private int countID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        tvBelkResult = (TextView) findViewById(R.id.rltw5);
        tvZhirResult = (TextView) findViewById(R.id.rltw6);
        tvUglResult = (TextView) findViewById(R.id.rltw7);
        tvKallResult = (TextView) findViewById(R.id.rltw8);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LayoutInflater li1 = getLayoutInflater();

                View it1 = li1.inflate(R.layout.product_layout1, linearLayout1, false);

                Button b = (Button) it1.findViewById(R.id.b);
                b.setText("Перекус №" + Integer.toString(countID));

                final LinearLayout l2 = (LinearLayout) it1.findViewById(R.id.l2);

                linearLayout3 = (LinearLayout) it1.findViewById(R.id.linearLayout3);

                linearLayout1.addView(it1);

                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {

                        //Получаем вид с файла product.xml, который применим для диалогового окна:
                        LayoutInflater li = LayoutInflater.from(context);
                        View promptsView = li.inflate(R.layout.product, null);

                        //Создаем AlertDialog
                        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                        //Настраиваем prompt.xml для нашего AlertDialog:
                        mDialogBuilder.setView(promptsView);

                        //Настраиваем отображение поля для ввода текста в открытом диалоге:

                        final EditText usereat = (EditText) promptsView.findViewById(R.id.input_text);
                        final EditText usermass = (EditText) promptsView.findViewById(R.id.input_mass);
                        final EditText userbelk = (EditText) promptsView.findViewById(R.id.input_belk);
                        final EditText userzhir = (EditText) promptsView.findViewById(R.id.input_zhir);
                        final EditText userugl = (EditText) promptsView.findViewById(R.id.input_ugl);

                        //Настраиваем сообщение в диалоговом окне:
                        mDialogBuilder.setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {

                                                //ПЕРЕМЕННЫЕ БЖУК
                                                double  mass_double = Double.parseDouble(usermass.getText().toString());
                                                double  belk_double = Double.parseDouble(userbelk.getText().toString());
                                                double  zhir_double = Double.parseDouble(userzhir.getText().toString());
                                                double  ugl_double = Double.parseDouble(userugl.getText().toString());
                                                double  kall_double = mass_double*(belk_double*4 + zhir_double*9 + ugl_double*4)/100;

                                                belk1 = belk_double*mass_double/100 + belk2;
                                                zhir1 = zhir_double*mass_double/100 + zhir2;
                                                ugl1 = ugl_double*mass_double/100 + ugl2;
                                                kall1 = kall_double + kall2;

                                                belk2 = belk1;
                                                zhir2 = zhir1;
                                                ugl2 = ugl1;
                                                kall2 = kall1;

                                                tvBelkResult.setText(Double.toString(belk2));
                                                tvZhirResult.setText(Double.toString(zhir2));
                                                tvUglResult.setText(Double.toString(ugl2));
                                                tvKallResult.setText(Double.toString(kall2));

                                                LayoutInflater li2 = getLayoutInflater();

                                                View it2 = li2.inflate(R.layout.product_layout2, l2, false);

                                                TextView tv_eat = (TextView) it2.findViewById(R.id.tv_eat);
                                                TextView tv_mass = (TextView) it2.findViewById(R.id.tv_mass);
                                                TextView tv_belk_num = (TextView) it2.findViewById(R.id.tv_belk_num);
                                                TextView tv_zhir_num = (TextView) it2.findViewById(R.id.tv_zhir_num);
                                                TextView tv_ugl_num = (TextView) it2.findViewById(R.id.tv_ugl_num);
                                                TextView tv_kkal_num = (TextView) it2.findViewById(R.id.tv_kkal_num);

                                                tv_eat.setText(usereat.getText().toString());
                                                tv_mass.setText(Double.toString(mass_double)+"г");
                                                tv_belk_num.setText(Double.toString(belk_double*mass_double/100));
                                                tv_zhir_num.setText(Double.toString(zhir_double*mass_double/100));
                                                tv_ugl_num.setText(Double.toString(ugl_double*mass_double/100));
                                                tv_kkal_num.setText(Double.toString(kall_double));

                                                l2.addView(it2);

                                            }
                                        })

                                .setNegativeButton("Отмена",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                dialog.cancel();
                                            }
                                        });

                        //Создаем AlertDialog:
                        AlertDialog alertDialog = mDialogBuilder.create();
                        //и отображаем его:

                        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

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
                                        buttonPositive.setEnabled(!userEatDialog.isEmpty());

                                        String userMassDialog = usermass.getText().toString().trim();
                                        buttonPositive.setEnabled(!userMassDialog.isEmpty());

                                        String userBelkDialog = userbelk.getText().toString().trim();
                                        buttonPositive.setEnabled(!userBelkDialog.isEmpty());

                                        String userZhirDialog = userzhir.getText().toString().trim();
                                        buttonPositive.setEnabled(!userZhirDialog.isEmpty());

                                        String userUglDialog = userugl.getText().toString().trim();
                                        buttonPositive.setEnabled(!userUglDialog.isEmpty());

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
                        //отображаем диалог:
                        alertDialog.show();
                    }
                });

                linearLayout3.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));

                countID++;
            }
        });//тут кончается слушатель на первую кнопку
    }
}