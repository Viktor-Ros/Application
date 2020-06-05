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
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Context context = this;
    private TextView proteinResultTV;
    private TextView carhydResultTV;
    private TextView fatResultTV;
    private TextView kallResultTV;
    private Button buttonAddEating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddEating = (Button) findViewById(R.id.addEating_Button);
        proteinResultTV = (TextView) findViewById(R.id.proteinResultNum_TextView);
        fatResultTV = (TextView) findViewById(R.id.fatResultNum_TextView);
        carhydResultTV = (TextView) findViewById(R.id.carhydResultNum_TextView);
        kallResultTV = (TextView) findViewById(R.id.calResultNum_TextView);

        final LinearLayout activity_layout = (LinearLayout) findViewById(R.id.activity_layout);

        buttonAddEating.setOnClickListener(new View.OnClickListener() {//клик по кнопке НОВАЯ ТРАПЕЗА

            @Override
            public void onClick(View v) {

                LayoutInflater eatingAddLI = LayoutInflater.from(context);
                final View eatingAddV = eatingAddLI.inflate(R.layout.eating_name_dialog, null);
                AlertDialog.Builder mDialogBuilder1 = new AlertDialog.Builder(context);
                mDialogBuilder1.setView(eatingAddV);

                final EditText eatingNameET = (EditText) eatingAddV.findViewById(R.id.eating_EditText);

                mDialogBuilder1.setCancelable(false)
                        .setPositiveButton("OK",//Диалог добавления трапезы
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        LayoutInflater eatingLI = getLayoutInflater();
                                        final View eatingView = eatingLI.inflate(R.layout.eating_layout, activity_layout, false);

                                        final Button eatingButton = eatingView.findViewById(R.id.eating_Button);
                                        eatingButton.setText(eatingNameET.getText());

                                        TextView viewTime = eatingView.findViewById(R.id.time_TextView);
                                        Date time = new Date();
                                        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm");
                                        viewTime.setText(formatTime.format(time));

                                        final LinearLayout product_layout = eatingView.findViewById(R.id.product_Layout);
                                        LinearLayout eating_layout = eatingView.findViewById(R.id.eating_layout);
                                        activity_layout.addView(eatingView);

                                        View eatingOptionsView = new View(getApplicationContext());
                                        eatingOptionsView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 15));
                                        activity_layout.addView(eatingOptionsView);

                                        eatingButton.setOnClickListener(new View.OnClickListener() {//клик по кнопке ТРАПЕЗЫ

                                            @Override
                                            public void onClick(View v) {

                                                LayoutInflater eatingOptionsLI = LayoutInflater.from(context);
                                                View promptsView2 = eatingOptionsLI.inflate(R.layout.eating_options, null);
                                                AlertDialog.Builder mDialogBuilder2 = new AlertDialog.Builder(context);
                                                mDialogBuilder2.setView(promptsView2);

                                                final AlertDialog dialogEatingOptions = mDialogBuilder2.create();
                                                dialogEatingOptions.show();

                                                Button buttonProductAdd = (Button) promptsView2.findViewById(R.id.productAdd_Button);
                                                Button buttonEatingDel = (Button) promptsView2.findViewById(R.id.productDel_Button);
                                                Button buttonEatingRename = (Button) promptsView2.findViewById(R.id.eatingRename_Button);

                                                buttonEatingDel.setOnClickListener(new View.OnClickListener() {//клик по кнопке УДАЛИТЬ ТРАПЕЗУ

                                                    @Override
                                                    public void onClick(View v) {

                                                        DBControl dbcDelEating = new DBControl(context, eatingButton.getText().toString());

                                                        dbcDelEating.dellEating();

                                                        proteinResultTV.setText(dbcDelEating.getCountProtein());//обновление общих параметров БЖУК
                                                        fatResultTV.setText(dbcDelEating.getCountFat());
                                                        carhydResultTV.setText(dbcDelEating.getCountCarhyd());
                                                        kallResultTV.setText(dbcDelEating.getCountCall());

                                                        dialogEatingOptions.cancel();

                                                        activity_layout.removeView(eatingView);
                                                    }});

                                                buttonEatingRename.setOnClickListener(new View.OnClickListener() {//клик по кнопке ИЗМЕНИТЬ НАЗВАНИЕ ТРАПЕЗЫ

                                                    @Override
                                                    public void onClick(View v) {

                                                        LayoutInflater eatingRenameLI = LayoutInflater.from(context);
                                                        final View eatingRenameV = eatingRenameLI.inflate(R.layout.eating_name_dialog, null);
                                                        final AlertDialog.Builder eatingRenameDB = new AlertDialog.Builder(context);
                                                        eatingRenameDB.setView(eatingRenameV);

                                                        final EditText editTextEatingRename = (EditText) eatingRenameV.findViewById(R.id.eating_EditText);

                                                        editTextEatingRename.setText(eatingButton.getText());

                                                        eatingRenameDB.setCancelable(false)
                                                                .setPositiveButton("OK",//Диалог изменения названия трапезы
                                                                        new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {

                                                                                DBControl dbcEatingUpd = new DBControl(context, eatingButton.getText().toString(), editTextEatingRename.getText());
                                                                                dbcEatingUpd.udpNameEating();

                                                                                eatingButton.setText(editTextEatingRename.getText().toString());

                                                                                dialogEatingOptions.cancel();
                                                                            }});

                                                        eatingRenameDB.setCancelable(false)
                                                                .setNegativeButton("Отмена",//Диалог изменения названия трапезы
                                                                        new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {

                                                                                dialogEatingOptions.cancel();
                                                                            }});

                                                        final AlertDialog dialogEatingRename = eatingRenameDB.create();

                                                        DialogButtonOff myDialogRenameEating = new DialogButtonOff(dialogEatingRename, editTextEatingRename);//для отображения кнопки диалога при заполнении всех показателей
                                                        myDialogRenameEating.ButtonOkOffEating();

                                                        dialogEatingRename.show();
                                                    }});

                                                buttonProductAdd.setOnClickListener(new View.OnClickListener() {//клик по кнопке ДОБАВИТЬ ПРОДУКТ

                                                    @Override
                                                    public void onClick(View v) {

                                                        LayoutInflater productAddLI = LayoutInflater.from(context);
                                                        final View productAddV = productAddLI.inflate(R.layout.product_dialog, null);
                                                        final AlertDialog.Builder productAddDB = new AlertDialog.Builder(context);
                                                        productAddDB.setView(productAddV);

                                                        final EditText productNameAddET = (EditText) productAddV.findViewById(R.id.productName_EditText);
                                                        final EditText productMassAddET = (EditText) productAddV.findViewById(R.id.massNum_EditText);
                                                        final EditText productProteinAddET = (EditText) productAddV.findViewById(R.id.proteinNum_EditText);
                                                        final EditText productFatAddET = (EditText) productAddV.findViewById(R.id.fatNum_EditText);
                                                        final EditText productCarhydAddET = (EditText) productAddV.findViewById(R.id.carhydNum_EditText);

                                                        productAddDB.setCancelable(false)
                                                                .setPositiveButton("OK",//Диалог добавления продукта
                                                                        new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {

                                                                                dialogEatingOptions.cancel();

                                                                                LayoutInflater productLabeAddlLI = getLayoutInflater();
                                                                                final View productLabelAddV = productLabeAddlLI.inflate(R.layout.product_layout, product_layout, false);

                                                                                final TextView productNameTV = (TextView) productLabelAddV.findViewById(R.id.productName_TextView);
                                                                                final TextView productMassTV = (TextView) productLabelAddV.findViewById(R.id.mass_TextView);
                                                                                final TextView productProteinTV = (TextView) productLabelAddV.findViewById(R.id.proteinNum_TextView);
                                                                                final TextView productFatTV = (TextView) productLabelAddV.findViewById(R.id.fatNum_TextView);
                                                                                final TextView productCarhydTV = (TextView) productLabelAddV.findViewById(R.id.carhydNum_TextView);
                                                                                final TextView productKallTV = (TextView) productLabelAddV.findViewById(R.id.calNum_TextView);

                                                                                final DBControl dbcAddProduct = new DBControl(context, eatingButton.getText().toString(),

                                                                                        productNameAddET.getText().toString(),
                                                                                        productMassAddET.getText().toString(),
                                                                                        productProteinAddET.getText().toString(),
                                                                                        productFatAddET.getText().toString(),
                                                                                        productCarhydAddET.getText().toString());

                                                                                dbcAddProduct.addProduct();

                                                                                proteinResultTV.setText(dbcAddProduct.getCountProtein());//обновление общих параметров БЖУК
                                                                                fatResultTV.setText(dbcAddProduct.getCountFat());
                                                                                carhydResultTV.setText(dbcAddProduct.getCountCarhyd());
                                                                                kallResultTV.setText(dbcAddProduct.getCountCall());

                                                                                productNameTV.setText(productNameAddET.getText());
                                                                                productMassTV.setText(dbcAddProduct.getMass());
                                                                                productProteinTV.setText(dbcAddProduct.getProtein(productMassAddET.getText().toString()));
                                                                                productFatTV.setText(dbcAddProduct.getFat(productMassAddET.getText().toString()));
                                                                                productCarhydTV.setText(dbcAddProduct.getCarhyd(productMassAddET.getText().toString()));
                                                                                productKallTV.setText(dbcAddProduct.getCall(productMassAddET.getText().toString()));

                                                                                product_layout.addView(productLabelAddV);

                                                                                productLabelAddV.setOnClickListener(new View.OnClickListener() {//КЛИК ПО ПРОДУКТУ

                                                                                            @Override
                                                                                            public void onClick(View v) {

                                                                                                LayoutInflater productUpdLI = LayoutInflater.from(context);
                                                                                                View productUpdV = productUpdLI.inflate(R.layout.product_dialog, null);
                                                                                                AlertDialog.Builder productUpdDB = new AlertDialog.Builder(context);
                                                                                                productUpdDB.setView(productUpdV);

                                                                                                final EditText userProductNameUpd = (EditText) productUpdV.findViewById(R.id.productName_EditText);
                                                                                                final EditText userProductMassUpd = (EditText) productUpdV.findViewById(R.id.massNum_EditText);
                                                                                                final EditText userProteinUpd = (EditText) productUpdV.findViewById(R.id.proteinNum_EditText);
                                                                                                final EditText userFatUpd = (EditText) productUpdV.findViewById(R.id.fatNum_EditText);
                                                                                                final EditText userCarhydUpd = (EditText) productUpdV.findViewById(R.id.carhydNum_EditText);

                                                                                                final DBControl dbcUpdDelProduct = new DBControl(context,
                                                                                                        eatingButton.getText().toString(),//старые значения продукта
                                                                                                        productNameTV.getText().toString(),
                                                                                                        productMassTV.getText().toString()
                                                                                                        );

                                                                                                userProductNameUpd.setText(productNameTV.getText());
                                                                                                userProductMassUpd.setText(productMassTV.getText());
                                                                                                userProteinUpd.setText(dbcUpdDelProduct.getProteinDialog());//на 100 грамм
                                                                                                userFatUpd.setText(dbcUpdDelProduct.getFatDialog());//на 100 грамм
                                                                                                userCarhydUpd.setText(dbcUpdDelProduct.getCarhydDialog());//на 100 грамм

                                                                                                        productUpdDB.setCancelable(false)
                                                                                                        .setPositiveButton("OK",//Диалог изменения продукта
                                                                                                                new DialogInterface.OnClickListener() {
                                                                                                                    public void onClick(DialogInterface dialog, int id) {

                                                                                                                        dbcUpdDelProduct.updProduct(

                                                                                                                                userProductNameUpd.getText().toString(),
                                                                                                                                userProductMassUpd.getText().toString(),
                                                                                                                                userProteinUpd.getText().toString(),
                                                                                                                                userFatUpd.getText().toString(),
                                                                                                                                userCarhydUpd.getText().toString());

                                                                                                                        proteinResultTV.setText(dbcUpdDelProduct.getCountProtein());//обновление общих параметров БЖУК
                                                                                                                        fatResultTV.setText(dbcUpdDelProduct.getCountFat());
                                                                                                                        carhydResultTV.setText(dbcUpdDelProduct.getCountCarhyd());
                                                                                                                        kallResultTV.setText(dbcUpdDelProduct.getCountCall());

                                                                                                                        productNameTV.setText(userProductNameUpd.getText());
                                                                                                                        productMassTV.setText(userProductMassUpd.getText());
                                                                                                                        productProteinTV.setText(dbcUpdDelProduct.getProtein(userProductMassUpd.getText().toString()));//на всю массу
                                                                                                                        productFatTV.setText(dbcUpdDelProduct.getFat(userProductMassUpd.getText().toString()));//на всю массу
                                                                                                                        productCarhydTV.setText(dbcUpdDelProduct.getCarhyd(userProductMassUpd.getText().toString()));//на всю массу
                                                                                                                        productKallTV.setText(dbcUpdDelProduct.getCall(userProductMassUpd.getText().toString()));//на всю массу
                                                                                                                    }});

                                                                                                productUpdDB.setCancelable(false)
                                                                                                        .setNeutralButton("Удалить",//Диалог изменения продукта
                                                                                                                new DialogInterface.OnClickListener() {
                                                                                                                    public void onClick(DialogInterface dialog, int id) {

                                                                                                                        dbcUpdDelProduct.dellProduct();

                                                                                                                        proteinResultTV.setText(dbcUpdDelProduct.getCountProtein());//обновление общих параметров БЖУК
                                                                                                                        fatResultTV.setText(dbcUpdDelProduct.getCountFat());
                                                                                                                        carhydResultTV.setText(dbcUpdDelProduct.getCountCarhyd());
                                                                                                                        kallResultTV.setText(dbcUpdDelProduct.getCountCall());

                                                                                                                        product_layout.removeView(productLabelAddV);
                                                                                                                    }});

                                                                                                productUpdDB.setCancelable(false)
                                                                                                        .setNegativeButton("Отмена",//Диалог изменения продукта
                                                                                                                new DialogInterface.OnClickListener() {
                                                                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                                                                    }});

                                                                                                AlertDialog dialogUpdProduct = productUpdDB.create();

                                                                                                DialogButtonOff myDialogUpdProduct = new DialogButtonOff(dialogUpdProduct, userProductNameUpd, userProductMassUpd,
                                                                                                        userProteinUpd, userFatUpd, userCarhydUpd);//для отображения кнопки диалога при заполнении всех показателей
                                                                                                myDialogUpdProduct.ButtonOkOffProduct();

                                                                                                dialogUpdProduct.show();

                                                                                            }});
                                                                            }});

                                                        productAddDB.setCancelable(false)
                                                                .setNegativeButton("Отмена",//Диалог добавления продукта
                                                                        new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                dialogEatingOptions.cancel();
                                                                            }});

                                                        final AlertDialog dialogProductAdd = productAddDB.create();

                                                        DialogButtonOff myDialogAddProduct = new DialogButtonOff(dialogProductAdd, productNameAddET,
                                                                productMassAddET, productProteinAddET, productFatAddET, productCarhydAddET);//для отображения кнопки диалога при заполнении всех показателей
                                                        myDialogAddProduct.ButtonOkOffProduct();

                                                        dialogProductAdd.show();

                                                    }});
                                            }});
                                        eating_layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                                    }});

                mDialogBuilder1.setCancelable(false)
                        .setNegativeButton("Отмена",//Диалог добавления трапезы
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }});

                final AlertDialog dialogEatingName = mDialogBuilder1.create();

                DialogButtonOff myDialogAddEating = new DialogButtonOff(dialogEatingName, eatingNameET);//для отображения кнопки диалога при заполнении всех показателей
                myDialogAddEating.ButtonOkOffEating();

                dialogEatingName.show();
            }});
    }
}