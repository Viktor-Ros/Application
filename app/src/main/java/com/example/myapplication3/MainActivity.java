package com.example.myapplication3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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

        final NutritionFact myNutritionFact1 = new NutritionFact();

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

                                                NutritionFact myNutritionFact2 = new NutritionFact(myNutritionFact1, usermass.getText(), userbelk.getText(), userzhir.getText(), userugl.getText());

                                                tvBelkResult.setText(myNutritionFact1.getCountBelk());
                                                tvZhirResult.setText(myNutritionFact1.getCountZhir());
                                                tvUglResult.setText(myNutritionFact1.getCountUgl());
                                                tvKallResult.setText(myNutritionFact1.getCountKall());

                                                LayoutInflater li2 = getLayoutInflater();

                                                View it2 = li2.inflate(R.layout.product_layout2, l2, false);

                                                TextView tv_eat = (TextView) it2.findViewById(R.id.tv_eat);
                                                TextView tv_mass = (TextView) it2.findViewById(R.id.tv_mass);
                                                TextView tv_belk_num = (TextView) it2.findViewById(R.id.tv_belk_num);
                                                TextView tv_zhir_num = (TextView) it2.findViewById(R.id.tv_zhir_num);
                                                TextView tv_ugl_num = (TextView) it2.findViewById(R.id.tv_ugl_num);
                                                TextView tv_kkal_num = (TextView) it2.findViewById(R.id.tv_kkal_num);

                                                tv_eat.setText(usereat.getText().toString());
                                                tv_mass.setText(myNutritionFact2.getMass());
                                                tv_belk_num.setText(myNutritionFact2.getBelk());
                                                tv_zhir_num.setText(myNutritionFact2.getZhir());
                                                tv_ugl_num.setText(myNutritionFact2.getUgl());
                                                tv_kkal_num.setText(myNutritionFact2.getCall());

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

                        DialogButtonOff myDialog = new DialogButtonOff();//для отображения кнопки диалога при заполнении всех показателей
                        myDialog.ButtonOkOff(alertDialog, usereat, usermass, userbelk, userzhir, userugl);

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