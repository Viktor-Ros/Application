package com.example.myapplication3;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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
    private LinearLayout linearLayout;
    private TextView tvBelkResult;
    private TextView tvZhirResult;
    private TextView tvUglResult;
    private TextView tvKallResult;
    private double belk1, belk2, zhir1, zhir2, ugl1, ugl2, kall1, kall2;




    private final int USERID = 6000;
    private int countID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);
        tvBelkResult = (TextView) findViewById(R.id.rltw5);
        tvZhirResult = (TextView) findViewById(R.id.rltw6);
        tvUglResult = (TextView) findViewById(R.id.rltw7);
        tvKallResult = (TextView) findViewById(R.id.rltw8);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LinearLayout l = new LinearLayout(getApplicationContext());
                l.setBackgroundColor(Color.WHITE);
                l.setPadding(5,5,5,5);
                l.setOrientation(LinearLayout.HORIZONTAL);
                l.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                600));
                l.setId(USERID + countID);
                linearLayout.addView(l);

                Button b = new Button(getApplicationContext());
                b.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                100,
                                ViewGroup.LayoutParams.MATCH_PARENT));
                b.setBackgroundColor(Color.BLACK);
                b.setTextColor(Color.WHITE);
                b.setPadding(30,80,30,80);
                b.setText("Перекус №" + Integer.toString(countID));
                b.setId(USERID + countID);
                l.addView(b);

                LinearLayout ln1 = new LinearLayout(getApplicationContext());
                ln1.setBackgroundColor(Color.WHITE);
                ln1.setOrientation(LinearLayout.VERTICAL);
                ln1.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                5,
                                ViewGroup.LayoutParams.MATCH_PARENT));
                ln1.setId(USERID + countID);
                l.addView(ln1);

                LinearLayout ln2 = new LinearLayout(getApplicationContext());
                ln2.setBackgroundColor(Color.BLACK);
                ln2.setOrientation(LinearLayout.VERTICAL);
                ln2.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                15));
                ln2.setId(USERID + countID);
                linearLayout.addView(ln2);

                final LinearLayout l2 = new LinearLayout(getApplicationContext());
                l2.setPadding(0,0,0,0);
                l2.setBackgroundColor(Color.BLACK);
                l2.setOrientation(LinearLayout.VERTICAL); //осталось от вертикального лайаута
                l2.setLayoutParams(new
                        LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));
                l2.setId(USERID + countID);
                l.addView(l2);

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

                                                LayoutInflater li = getLayoutInflater();

                                                View it = li.inflate(R.layout.product_layout, l2, false);

                                                l2.addView(it);

                                                TextView tv_mass = (TextView) it.findViewById(R.id.tv_mass);
                                                TextView tv_belk_num = (TextView) it.findViewById(R.id.tv_belk_num);
                                                TextView tv_zhir_num = (TextView) it.findViewById(R.id.tv_zhir_num);
                                                TextView tv_ugl_num = (TextView) it.findViewById(R.id.tv_ugl_num);
                                                TextView tv_kkal_num = (TextView) it.findViewById(R.id.tv_kkal_num);

                                                tv_mass.setText(Double.toString(mass_double)+"г");
                                                tv_belk_num.setText(Double.toString(belk_double*mass_double/100));
                                                tv_zhir_num.setText(Double.toString(zhir_double*mass_double/100));
                                                tv_ugl_num.setText(Double.toString(ugl_double*mass_double/100));
                                                tv_kkal_num.setText(Double.toString(kall_double));

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
                l.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                countID++;
            }
        });//тут кончается слушатель на первую кнопку
    }
}