package com.example.myapplication3;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        tvBelkResult = (TextView) findViewById(R.id.rltw5 );
        tvZhirResult = (TextView) findViewById(R.id.rltw6 );
        tvUglResult = (TextView) findViewById(R.id.rltw7 );
        tvKallResult = (TextView) findViewById(R.id.rltw8 );

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



                                                RelativeLayout rl = new RelativeLayout(getApplicationContext());
                                                rl.setBackgroundColor(Color.BLACK);
                                                rl.setPadding(0,0,0,20);
                                                rl.setLayoutParams(new RelativeLayout.LayoutParams(
                                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                                                l2.addView(rl);



                                                //ТЕКСТОВЫЕ VIEW

                                                int a = 1;

                                                // НАЗВАНИЕ ПРОДУКТА

                                                TextView tv_eat = new TextView(getApplicationContext());
                                                tv_eat.setBackgroundColor(Color.BLACK);
                                                tv_eat.setTextColor(Color.WHITE);
                                                tv_eat.setTextSize(15);
                                                tv_eat.setText(usereat.getText());
                                                tv_eat.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_eatParams =
                                                        new RelativeLayout.LayoutParams(
                                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                                        60);
                                                tv_eatParams.setMargins(20,20,0,0);
                                                rl.addView(tv_eat, tv_eatParams);
                                                a++;

                                                //МАССА ПРОДУКТА ЧИСЛОВАЯ

                                                TextView tv_mass = new TextView(getApplicationContext());
                                                tv_mass.setBackgroundColor(Color.BLACK);
                                                tv_mass.setTextColor(Color.WHITE);
                                                tv_mass.setTextSize(15);
                                                tv_mass.setGravity(Gravity.LEFT);
                                                tv_mass.setText(Double.toString(mass_double)+"г");
                                                tv_mass.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_massParams =
                                                        new RelativeLayout.LayoutParams(160,80);
                                                tv_massParams.addRule(RelativeLayout.BELOW, tv_eat.getId());
                                                tv_massParams.setMargins(20,20,0,0);
                                                rl.addView(tv_mass, tv_massParams);
                                                a++;

                                                //БЕЛКИ
                                                TextView tv_belk = new TextView(getApplicationContext());
                                                tv_belk.setBackgroundColor(Color.BLACK);
                                                tv_belk.setText("Белки");
                                                tv_belk.setTextColor(Color.WHITE);
                                                tv_belk.setTextSize(10);
                                                tv_belk.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_belkParams =
                                                        new RelativeLayout.LayoutParams(
                                                                ViewGroup.LayoutParams.WRAP_CONTENT, 40);
                                                tv_belkParams.addRule(RelativeLayout.BELOW, tv_eat.getId());
                                                tv_belkParams.addRule(RelativeLayout.RIGHT_OF,tv_mass.getId());
                                                tv_belkParams.setMargins(40,20,0,0);
                                                rl.addView(tv_belk, tv_belkParams);
                                                a++;

                                                //ЖИРЫ
                                                TextView tv_zhir = new TextView(getApplicationContext());
                                                tv_zhir.setBackgroundColor(Color.BLACK);
                                                tv_zhir.setText("Жиры");
                                                tv_zhir.setTextColor(Color.WHITE);
                                                tv_zhir.setTextSize(10);
                                                tv_zhir.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_zhirParams =
                                                        new RelativeLayout.LayoutParams(
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,40);
                                                tv_zhirParams.addRule(RelativeLayout.BELOW, tv_eat.getId());
                                                tv_zhirParams.addRule(RelativeLayout.RIGHT_OF,tv_belk.getId());
                                                tv_zhirParams.setMargins(100,20,0,0);
                                                rl.addView(tv_zhir, tv_zhirParams);
                                                a++;

                                                //УГЛЕВОДЫ
                                                TextView tv_ugl = new TextView(getApplicationContext());
                                                tv_ugl.setBackgroundColor(Color.BLACK);
                                                tv_ugl.setText("Углеводы");
                                                tv_ugl.setTextColor(Color.WHITE);
                                                tv_ugl.setTextSize(10);
                                                tv_ugl.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_uglParams =
                                                        new RelativeLayout.LayoutParams(
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,40);
                                                tv_uglParams.addRule(RelativeLayout.BELOW, tv_eat.getId());
                                                tv_uglParams.addRule(RelativeLayout.RIGHT_OF,tv_zhir.getId());
                                                tv_uglParams.setMargins(100,20,0,0);
                                                rl.addView(tv_ugl, tv_uglParams);
                                                a++;

                                                //КАЛЛОРИИ
                                                TextView tv_kkal = new TextView(getApplicationContext());
                                                tv_kkal.setBackgroundColor(Color.BLACK);
                                                tv_kkal.setText("Ккал");
                                                tv_kkal.setTextColor(Color.WHITE);
                                                tv_kkal.setTextSize(15);
                                                tv_kkal.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_kkalParams =
                                                        new RelativeLayout.LayoutParams(
                                                                ViewGroup.LayoutParams.WRAP_CONTENT, 60);
                                                tv_kkalParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,tv_eat.getId());
                                                tv_kkalParams.setMargins(0,20,20,0);
                                                rl.addView(tv_kkal, tv_kkalParams);
                                                a++;



                                                //ЧИСЛОВЫЕ View

                                                //КОЛ-ВО БЕЛКОВ
                                                TextView tv_belk_num = new TextView(getApplicationContext());
                                                tv_belk_num.setBackgroundColor(Color.BLACK);
                                                tv_belk_num.setTextColor(Color.WHITE);
                                                tv_belk_num.setTextSize(10);
                                                tv_belk_num.setGravity(Gravity.CENTER);
                                                tv_belk_num.setText(Double.toString(belk_double*mass_double/100));
                                                tv_belk_num.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_belk_numParams =
                                                        new RelativeLayout.LayoutParams(80,40);
                                                tv_belk_numParams.addRule(RelativeLayout.BELOW, tv_belk.getId());
                                                tv_belk_numParams.addRule(RelativeLayout.RIGHT_OF, tv_mass.getId());
                                                tv_belk_numParams.setMargins(40,20,0,0);
                                                rl.addView(tv_belk_num, tv_belk_numParams);
                                                a++;

                                                //КОЛ-ВО ЖИРОВ
                                                TextView tv_zhir_num = new TextView(getApplicationContext());
                                                tv_zhir_num.setBackgroundColor(Color.BLACK);
                                                tv_zhir_num.setTextColor(Color.WHITE);
                                                tv_zhir_num.setTextSize(10);
                                                tv_zhir_num.setGravity(Gravity.CENTER);
                                                tv_zhir_num.setText(Double.toString(zhir_double*mass_double/100));
                                                tv_zhir_num.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_zhir_numParams =
                                                        new RelativeLayout.LayoutParams(80,40);
                                                tv_zhir_numParams.addRule(RelativeLayout.BELOW, tv_zhir.getId());
                                                tv_zhir_numParams.addRule(RelativeLayout.RIGHT_OF, tv_belk.getId());
                                                tv_zhir_numParams.setMargins(110,20,0,0);
                                                rl.addView(tv_zhir_num, tv_zhir_numParams);
                                                a++;

                                                //КОЛ-ВО УГЛЕВОДОВ

                                                TextView tv_ugl_num = new TextView(getApplicationContext());
                                                tv_ugl_num.setBackgroundColor(Color.BLACK);
                                                tv_ugl_num.setTextColor(Color.WHITE);
                                                tv_ugl_num.setTextSize(10);
                                                tv_ugl_num.setGravity(Gravity.CENTER);
                                                tv_ugl_num.setText(Double.toString(ugl_double*mass_double/100));
                                                tv_ugl_num.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_ugl_numParams =
                                                        new RelativeLayout.LayoutParams(80,40);
                                                tv_ugl_numParams.addRule(RelativeLayout.BELOW, tv_ugl.getId());
                                                tv_ugl_numParams.addRule(RelativeLayout.RIGHT_OF, tv_zhir.getId());
                                                tv_ugl_numParams.setMargins(120,20,0,0);
                                                rl.addView(tv_ugl_num, tv_ugl_numParams);
                                                a++;

                                                //КОЛ-ВО КАЛЛОРИЙ

                                                TextView tv_kkal_num = new TextView(getApplicationContext());
                                                tv_kkal_num.setBackgroundColor(Color.BLACK);
                                                tv_kkal_num.setTextColor(Color.WHITE);
                                                tv_kkal_num.setTextSize(18);
                                                tv_kkal_num.setGravity(Gravity.RIGHT);
                                                tv_kkal_num.setText(Double.toString(kall_double));
                                                tv_kkal_num.setId(USERID + countID + a);
                                                RelativeLayout.LayoutParams tv_kkal_numParams =
                                                        new RelativeLayout.LayoutParams(170,60);
                                                tv_kkal_numParams.addRule(RelativeLayout.BELOW,tv_kkal.getId());
                                                tv_kkal_numParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,tv_eat.getId());
                                                tv_kkal_numParams.setMargins(0,60,20,0);
                                                rl.addView(tv_kkal_num, tv_kkal_numParams);
                                                a++;
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