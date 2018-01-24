package com.comp1601.tipntax;

/**
 * Created by ldnel on 2018-01-10.
 */

public class TipNTaxCalculator {
    public static final double DefaultTaxRate = 13.0; //13.0 percent
    public static final double DefaultTipPercentage = 15; //15 percent
    public static final double InvalidResult = -1; //unvalid result

    private double mTaxRate;
    private double mTipPercentage;

    public TipNTaxCalculator(){
        mTaxRate = DefaultTaxRate;
        mTipPercentage = DefaultTipPercentage;

    }
    public TipNTaxCalculator(double aTaxRate, double aTipPercentage){
        mTaxRate = aTaxRate;
        mTipPercentage = aTipPercentage;

    }

    public double getTipPercentage(){return mTipPercentage;}
    public void setTipPercentage(double aPercentage){mTipPercentage = aPercentage;}
    public double calculate(double amount){

        if(amount < 0) return InvalidResult;

        return amount*(1.0 + mTaxRate/100)*(1.0 + mTipPercentage/100);
    }
}
