package com.example.myapplication3;

import android.text.Editable;

import java.text.DecimalFormat;
import java.text.NumberFormat;

class NutritionFact {

    double belk, zhir, ugl, mass, kall, belk1, zhir1, ugl1, kall1, mass1;

    NumberFormat indicatorFormat = new DecimalFormat("#.#");//функция для отображения показателей без лишних нулей

    public NutritionFact(NutritionFact other, Editable mass, Editable belk, Editable zhir, Editable ugl) {

        this.mass = Double.parseDouble(mass.toString());
        this.belk = Double.parseDouble(belk.toString());
        this.zhir = Double.parseDouble(zhir.toString());
        this.ugl = Double.parseDouble(ugl.toString());
        this.kall = (this.mass*(this.belk*4 + this.zhir*9 + this.ugl*4))/100;

        other.mass1 = (Double.parseDouble(mass.toString()));
        other.belk1 = (Double.parseDouble(belk.toString()))*other.mass1/100;
        other.zhir1 = (Double.parseDouble(zhir.toString()))*other.mass1/100;
        other.ugl1 = (Double.parseDouble(ugl.toString()))*other.mass1/100;
        other.kall1 = other.belk1*4 + other.zhir1*9 + other.ugl1*4;

    }

    public NutritionFact() {

    }

    public String getCall(){

        return indicatorFormat.format((mass*(belk*4 + zhir*9 + ugl*4))/100);
    }

    public String getMass(){

        return indicatorFormat.format(mass) + "г";

    }

    public String getBelk(){

        return indicatorFormat.format(belk*mass/100);

    }

    public String getZhir(){

        return indicatorFormat.format(zhir*mass/100);

    }

    public String getUgl(){

        return indicatorFormat.format(ugl*mass/100);
    }

    String countBelk() {

        return indicatorFormat.format(this.belk = this.belk1 + this.belk);

    }

    String countZhir() {

        return indicatorFormat.format(this.zhir = this.zhir1 + this.zhir);

    }

    String countUgl() {

        return indicatorFormat.format(this.ugl = this.ugl1 + this.ugl);

    }

    String countKall() {

        return indicatorFormat.format(this.kall = this.kall1 + this.kall);

    }
}