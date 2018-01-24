package com.comp1601.tipntax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TipCalcActivity extends AppCompatActivity {

    private  final String TAG = this.getClass().getSimpleName() + " @" + System.identityHashCode(this);
    public final static String TIP_N_TAX_COMPUTED_TOTAL = "TIP_N_TAX_COMPUTED_TOTAL";

    private Button mCalculateButton;
    private EditText mPriceView;
    private EditText mTipPercentageView;
    private EditText mTotalView;
    private TipNTaxCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calc);

        mCalculateButton = (Button) findViewById(R.id.calculate_total_button);
        mPriceView = (EditText) findViewById(R.id.priceEditText);
        mTipPercentageView = (EditText) findViewById(R.id.percentEditText);
        mTotalView = (EditText) findViewById(R.id.totalEditText);
        calculator = new TipNTaxCalculator();

        mPriceView.setText("0.00");
        mTotalView.setText("0.00");
        String tipPercentageString = "" + calculator.getTipPercentage();
        mTipPercentageView.setText(tipPercentageString);

        double price = getIntent().getDoubleExtra(MainActivity.TIP_N_TAX_MAIN_AMOUNT, 0.0);
        String priceStr = ""+price;
        mPriceView.setText(priceStr);

        mCalculateButton.setOnClickListener((View v) -> {
            Log.i(TAG, "Calculate Button Clicked");
            String amountStr = mPriceView.getText().toString();
            double amount = Double.parseDouble(amountStr);
            Log.i(TAG, "TipCalcActivity::Amount = " + amount);

            String tipPercentStr = mTipPercentageView.getText().toString();
            double tipPercentage = Double.parseDouble(tipPercentStr);
            calculator.setTipPercentage(tipPercentage);
            Log.i(TAG, "TipCalcActivity::TipPercentage = " + tipPercentage);

            double total = calculator.calculate(amount);
            Log.i(TAG, "TipCalcActivity::Total = " + total);
            String totalStr = "" + total;
            mTotalView.setText(totalStr);

            Intent data = new Intent();
            data.putExtra(TIP_N_TAX_COMPUTED_TOTAL, total);
            setResult(RESULT_OK, data);
        });

    }
}
