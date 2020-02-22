package com.example.myapplication3;

import android.text.Editable;

import java.text.DecimalFormat;
import java.text.NumberFormat;

class NutritionFact {

    double belk, zhir, ugl, mass, kall, belk1, zhir1, ugl1, kall1, mass1;

    NumberFormat indicatorFormat = new DecimalFormat("#.#");//функция для отображения показателей без лишних нулей

    NutritionFact(NutritionFact other, Editable mass, Editable belk, Editable zhir, Editable ugl) {

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

    NutritionFact() {

    }

    String getCall(){

        return indicatorFormat.format((mass*(belk*4 + zhir*9 + ugl*4))/100);
    }

    String getMass(){

        return indicatorFormat.format(mass) + "г";

    }

    String getBelk(){

        return indicatorFormat.format(belk*mass/100);

    }

    String getZhir(){

        return indicatorFormat.format(zhir*mass/100);

    }

    String getUgl(){

        return indicatorFormat.format(ugl*mass/100);
    }

    String getCountBelk() {

        return indicatorFormat.format(this.belk = this.belk1 + this.belk);

    }

    String getCountZhir() {

        return indicatorFormat.format(this.zhir = this.zhir1 + this.zhir);

    }

    String getCountUgl() {

        return indicatorFormat.format(this.ugl = this.ugl1 + this.ugl);

    }

    String getCountKall() {

        return indicatorFormat.format(this.kall = this.kall1 + this.kall);

    }
}