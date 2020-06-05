package com.example.myapplication3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import java.text.DecimalFormat;
import java.text.NumberFormat;


class DBControl {

    private String mass, protein, fat, carhyd;
    private String massUpd, proteinUpd, fatUpd, carhydUpd;
    private String eatingName, productName;
    private String eatingNameUpd, productNameUpd;
    private Context context;


    public DBControl(Context context, String eatingName, String productName, String mass,
                     String protein, String fat, String carhyd) {//конструктор для добавления продукта

        this.eatingName = eatingName;
        this.productName = productName;
        this.mass = mass;
        this.protein = protein;
        this.fat = fat;
        this.carhyd = carhyd;
        this.context = context;
    }

    public DBControl(Context context, String eatingName, String productName, String mass) {//конструктор для удаления продукта

        this.context = context;
        this.eatingName = eatingName;
        this.productName = productName;
        this.mass = mass;
    }

    DBControl(Context context, String eatingName){//конструктор для удаления трапезы

        this.context = context;
        this.eatingName = eatingName;
    }

    DBControl(Context context, String eatingName, Editable eatingNameUpd){//конструктор для изменения трапезы

        this.context = context;
        this.eatingName = eatingName;
        this.eatingNameUpd = eatingNameUpd.toString();
    }

    //методы для добавления/удаления/изменения продукта

    void addProduct(){//метод для добавления продукта в БД

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.EATING_NAME, eatingName);
        cv.put(DBHelper.PRODUCT_NAME, productName);
        cv.put(DBHelper.PRODUCT_MASS, mass);
        cv.put(DBHelper.PRODUCT_PROTEINS, protein);
        cv.put(DBHelper.PRODUCT_FATS, fat);
        cv.put(DBHelper.PRODUCT_CARHYDS, carhyd);

        database.insert(DBHelper.TABLE_NAME, null, cv);

        printDB(database);
    }

    void dellProduct(){//метод для удаления продукта из БД

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        database.delete(DBHelper.TABLE_NAME,
                DBHelper.EATING_NAME + "='" + eatingName + "' AND " +
                        DBHelper.PRODUCT_NAME + "='" + productName + "' AND " +
                        DBHelper.PRODUCT_MASS + "='" + mass + "'"
                ,null);

        printDB(database);
    }

    void updProduct(String productNameUpd, String massUpd
            , String proteinUpd, String fatUpd, String carhydUpd){//метод для изменения продукта в БД

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select protein, fat, carbohydrat from products " +
                "where eatingName='" + eatingName + "' and productName ='" + productName + "' and productMass='" + mass + "'";

        Cursor rs = database.rawQuery(sq, null);

        int proteinColIndex = rs.getColumnIndex(DBHelper.PRODUCT_PROTEINS);
        int fatColIndex = rs.getColumnIndex(DBHelper.PRODUCT_FATS);
        int carhydColIndex = rs.getColumnIndex(DBHelper.PRODUCT_CARHYDS);

        while (rs.moveToNext()) {

            this.protein = rs.getString(proteinColIndex);
            this.fat = rs.getString(fatColIndex);
            this.carhyd = rs.getString(carhydColIndex);
        }
        rs.close();

        ContentValues cv = new ContentValues();

        this.productNameUpd = productNameUpd;
        this.massUpd = massUpd;
        this.proteinUpd = proteinUpd;
        this.fatUpd = fatUpd;
        this.carhydUpd = carhydUpd;

        cv.put(DBHelper.EATING_NAME, eatingName);
        cv.put(DBHelper.PRODUCT_NAME, this.productNameUpd);
        cv.put(DBHelper.PRODUCT_MASS, this.massUpd);
        cv.put(DBHelper.PRODUCT_PROTEINS, this.proteinUpd);
        cv.put(DBHelper.PRODUCT_FATS, this.fatUpd);
        cv.put(DBHelper.PRODUCT_CARHYDS, this.carhydUpd);

        database.update(DBHelper.TABLE_NAME, cv,
                DBHelper.EATING_NAME + "='" + eatingName + "' AND " +
                        DBHelper.PRODUCT_NAME + "='" + productName + "' AND " +
                        DBHelper.PRODUCT_MASS + "='" + mass + "' AND " +
                        DBHelper.PRODUCT_PROTEINS + "='" + this.protein + "' AND " +
                        DBHelper.PRODUCT_FATS + "='" + this.fat + "' AND " +
                        DBHelper.PRODUCT_CARHYDS + "='" + this.carhyd + "'",null);

        printDB(database);
    }

    /////////////////////////////////////////////////методы для удаления/изменения трапезы

    void dellEating(){//метод для удаления трапезы из БД

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        database.delete(DBHelper.TABLE_NAME,
                DBHelper.EATING_NAME + "='" + eatingName + "'",null);
        printDB(database);
    }

    void udpNameEating(){//метод для изменения названия трапезы в БД

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.EATING_NAME, eatingNameUpd);

        database.update(DBHelper.TABLE_NAME,
                cv,DBHelper.EATING_NAME + "='" + eatingName + "'",null);

        printDB(database);
    }


    /////////////////////////////////////////////////методы для выгрузки цифр БЖУК в TextView продукта

    private static NumberFormat indicatorFormat = new DecimalFormat("#.#");//функция для отображения показателей без лишних нулей

    String getMass(){

        return indicatorFormat.format(Double.parseDouble(mass));
    }

    String getProtein(String massUpd){

        this.mass = massUpd;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select protein from products where eatingName='" + eatingName + "' and productName ='" + productName + "' and productMass='" + mass + "'";

        Cursor rs = database.rawQuery(sq, null);

        int proteinColIndex = rs.getColumnIndex(DBHelper.PRODUCT_PROTEINS);

        while (rs.moveToNext()) {

            this.protein = rs.getString(proteinColIndex);
        }

        rs.close();

        return indicatorFormat.format(Double.parseDouble(protein)*Double.parseDouble(mass)/100);
    }

    String getFat(String massUpd){

        this.mass = massUpd;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select fat from products where eatingName='" + eatingName + "' and productName ='" + productName + "' and productMass='" + mass + "'";

        Cursor rs = database.rawQuery(sq, null);

        int fatColIndex = rs.getColumnIndex(DBHelper.PRODUCT_FATS);

        while (rs.moveToNext()) {

            this.fat = rs.getString(fatColIndex);
        }

        rs.close();

        return indicatorFormat.format(Double.parseDouble(fat)*Double.parseDouble(mass)/100);
    }


    String getCarhyd(String massUpd){

        this.mass = massUpd;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select carbohydrat from products where eatingName='" + eatingName + "' and productName ='" + productName + "' and productMass='" + mass + "'";

        Cursor rs = database.rawQuery(sq, null);

        int carhydColIndex = rs.getColumnIndex(DBHelper.PRODUCT_CARHYDS);

        while (rs.moveToNext()) {

            this.carhyd = rs.getString(carhydColIndex);
        }

        rs.close();

        return indicatorFormat.format(Double.parseDouble(carhyd)*Double.parseDouble(mass)/100);
    }

    String getCall(String massUpd){

        this.mass = massUpd;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select protein, fat, carbohydrat from products where eatingName='" + eatingName + "' and productName ='" + productName + "' and productMass='" + mass + "'";

        Cursor rs = database.rawQuery(sq, null);

        int proteinColIndex = rs.getColumnIndex(DBHelper.PRODUCT_PROTEINS);
        int fatColIndex = rs.getColumnIndex(DBHelper.PRODUCT_FATS);
        int carhydColIndex = rs.getColumnIndex(DBHelper.PRODUCT_CARHYDS);

        while (rs.moveToNext()) {

            this.protein = rs.getString(proteinColIndex);
            this.fat = rs.getString(fatColIndex);
            this.carhyd = rs.getString(carhydColIndex);
        }
        rs.close();

        return indicatorFormat.format((Double.parseDouble(mass)*(Double.parseDouble(protein)*4 + Double.parseDouble(fat)*9 + Double.parseDouble(carhyd)*4))/100);
    }


    String getProteinDialog(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select protein from products where eatingName='" + eatingName + "' and productName ='" + productName + "' and productMass='" + mass + "'";

        Cursor rs = database.rawQuery(sq, null);

        int proteinColIndex = rs.getColumnIndex(DBHelper.PRODUCT_PROTEINS);

        while (rs.moveToNext()) {

            this.protein = rs.getString(proteinColIndex);
        }

        rs.close();

        return this.protein;
    }

    String getFatDialog(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select fat from products where eatingName='" + eatingName + "' and productName ='" + productName + "' and productMass='" + mass + "'";

        Cursor rs = database.rawQuery(sq, null);

        int fatColIndex = rs.getColumnIndex(DBHelper.PRODUCT_FATS);

        while (rs.moveToNext()) {

            this.fat = rs.getString(fatColIndex);
        }

        rs.close();

        return fat;
    }

    String getCarhydDialog(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select carbohydrat from products where eatingName='" + eatingName + "' and productName ='" + productName + "' and productMass='" + mass + "'";

        Cursor rs = database.rawQuery(sq, null);

        int carhydColIndex = rs.getColumnIndex(DBHelper.PRODUCT_CARHYDS);

        while (rs.moveToNext()) {

            this.carhyd = rs.getString(carhydColIndex);
        }

        rs.close();

        return carhyd;
    }



    /////////////////////////////////////////////////методы для выгрузки общей статистики верхние TextView

    String getCountProtein(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select protein, productMass from products";

        Cursor rs = database.rawQuery(sq, null);

        double protein = 0;

        int massColIndex = rs.getColumnIndex(DBHelper.PRODUCT_MASS);
        int proteinColIndex = rs.getColumnIndex(DBHelper.PRODUCT_PROTEINS);

        while (rs.moveToNext()) {

            protein += Double.parseDouble(rs.getString(proteinColIndex)) * Double.parseDouble(rs.getString(massColIndex)) / 100;
        }

        rs.close();

        if(protein == 0){

            return "" ;
        }

        else return indicatorFormat.format(protein);
    }

    String getCountFat(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select fat, productMass from products";

        Cursor rs = database.rawQuery(sq, null);

        double fat = 0;

        int massColIndex = rs.getColumnIndex(DBHelper.PRODUCT_MASS);
        int fatColIndex = rs.getColumnIndex(DBHelper.PRODUCT_FATS);

        while (rs.moveToNext()) {

            fat += Double.parseDouble(rs.getString(fatColIndex)) * Double.parseDouble(rs.getString(massColIndex)) / 100;
        }

        rs.close();

        if(fat == 0){

            return "" ;
        }

        else return indicatorFormat.format(fat);
    }

    String getCountCarhyd(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select carbohydrat , productMass from products";

        Cursor rs = database.rawQuery(sq, null);

        double carhyd = 0;

        int massColIndex = rs.getColumnIndex(DBHelper.PRODUCT_MASS);
        int carhydColIndex = rs.getColumnIndex(DBHelper.PRODUCT_CARHYDS);

        while (rs.moveToNext()) {

            carhyd += Double.parseDouble(rs.getString(carhydColIndex)) * Double.parseDouble(rs.getString(massColIndex)) / 100;
        }

        rs.close();

        if(carhyd == 0){

            return "" ;
        }

        else return indicatorFormat.format(carhyd);
    }

    String getCountCall(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String sq = "select protein, fat, carbohydrat , productMass from products";

        Cursor rs = database.rawQuery(sq, null);

        double call = 0;

        int massColIndex = rs.getColumnIndex(DBHelper.PRODUCT_MASS);
        int proteinColIndex = rs.getColumnIndex(DBHelper.PRODUCT_PROTEINS);
        int fatColIndex = rs.getColumnIndex(DBHelper.PRODUCT_FATS);
        int carhydColIndex = rs.getColumnIndex(DBHelper.PRODUCT_CARHYDS);

        while (rs.moveToNext()) {

            call += Double.parseDouble(rs.getString(massColIndex)) * (
                    Double.parseDouble(rs.getString(proteinColIndex)) * 4 +
                    Double.parseDouble(rs.getString(carhydColIndex)) * 4 +
                    Double.parseDouble(rs.getString(fatColIndex)) * 9
            )/ 100;
        }

        rs.close();

        if(call == 0){

            return "" ;
        }

        else return indicatorFormat.format(call);
    }



    private void printDB(SQLiteDatabase database){//вывод БД в консоль для тестирования

        Cursor rs = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

        int idColIndex = rs.getColumnIndex(DBHelper.KEY_ID);
        int eatingNameColIndex = rs.getColumnIndex(DBHelper.EATING_NAME);
        int productNameColIndex = rs.getColumnIndex(DBHelper.PRODUCT_NAME);
        int massColIndex = rs.getColumnIndex(DBHelper.PRODUCT_MASS);
        int proteinColIndex = rs.getColumnIndex(DBHelper.PRODUCT_PROTEINS);
        int fatColIndex = rs.getColumnIndex(DBHelper.PRODUCT_FATS);
        int carhydColIndex = rs.getColumnIndex(DBHelper.PRODUCT_CARHYDS);

        System.out.println("-----------------------------------------");

        while (rs.moveToNext()) {

            String str = rs.getInt(idColIndex) + " | "

                    + rs.getString(eatingNameColIndex) + "\t | " + rs.getString(productNameColIndex) + "\t | "

                    + rs.getString(massColIndex) + "\t | " + rs.getString(proteinColIndex) + "\t | "

                    + rs.getString(fatColIndex) + "\t | " + rs.getString(carhydColIndex) + "\t | ";

            System.out.println(" ");
            System.out.println(" === " + str);
        }
        System.out.println("-----------------------------------------");
        rs.close();
    }
}