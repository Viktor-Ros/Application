package com.example.myapplication3;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
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


    private final int USERID = 6000;
    private int countID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LinearLayout l = new LinearLayout(getApplicationContext());
                l.setBackgroundColor(Color.WHITE);
                l.setPadding(5,5,5,5);
                l.setOrientation(LinearLayout.HORIZONTAL);
                l.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600)); //LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
                l.setId(USERID + countID);
                linearLayout.addView(l);

                Button b = new Button(getApplicationContext());
                b.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT)); //LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
                b.setBackgroundColor(Color.BLACK);
                b.setTextColor(Color.WHITE);
                b.setPadding(30,80,30,80);
                b.setText("Перекус №" + Integer.toString(countID));
                b.setId(USERID + countID);
                l.addView(b);

                LinearLayout ln1 = new LinearLayout(getApplicationContext());
                ln1.setBackgroundColor(Color.WHITE);
                ln1.setOrientation(LinearLayout.VERTICAL);
                ln1.setLayoutParams(new LinearLayout.LayoutParams(5, ViewGroup.LayoutParams.MATCH_PARENT));
                ln1.setId(USERID + countID);
                l.addView(ln1);

                LinearLayout ln2 = new LinearLayout(getApplicationContext());
                ln2.setBackgroundColor(Color.BLACK);
                ln2.setOrientation(LinearLayout.VERTICAL);
                ln2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 15));
                ln2.setId(USERID + countID);
                linearLayout.addView(ln2);

                final LinearLayout l2 = new LinearLayout(getApplicationContext());
                l2.setPadding(0,0,0,0);
                l2.setBackgroundColor(Color.BLACK);
                l2.setOrientation(LinearLayout.VERTICAL); //осталось от вертикального лайаута
                l2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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


                        //final EditText massInput = (EditText) promptsView.findViewById(R.id.input_mass);
                        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);

                        //Настраиваем сообщение в диалоговом окне:
                        mDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                //Вводим текст и отображаем в строке ввода на основном экране:
                                                TextView tv = new TextView(getApplicationContext());
                                                tv.setPadding(15,15,15,15);
                                                //tv.setBackgroundColor(Color.GREEN);
                                                tv.setTextColor(Color.WHITE);
                                                tv.setTextSize(1,20);
                                                tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                //tv.setPaddingRelative(15,15,15,15);
                                                tv.setId(USERID + countID);
                                                l2.addView(tv);
                                                tv.setText(userInput.getText());

                                                /*
                                                TextView mass = new TextView(getApplicationContext());
                                                mass.setTextColor(Color.WHITE);
                                                mass.setBackgroundColor(Color.RED);
                                                mass.setTextSize(1,20);
                                                mass.setLayoutParams(new LinearLayout.LayoutParams(15, 25));
                                                mass.setPaddingRelative(50,50,50,50);
                                                mass.setId(USERID + countID);
                                                l2.addView(mass);
                                                mass.setText(massInput.getText());
                                                */
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
                        alertDialog.show();
                    }

                });

                l.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                countID++;
            }
        });

        //тут кончается слушатель на первую кнопку

    }

}