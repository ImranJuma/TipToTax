package com.comp1601.tipntax;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.security.auth.Subject;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName() + " @" + System.identityHashCode(this);
    private final String SUBJECT = "Tip&TAX Calculator Report";
    public static final String TIP_N_TAX_MAIN_AMOUNT = "tip_n_tax_main_activity_amount";
    private final static int TIP_N_TAX_TOTAL_RESULT = 0;

    private Button mCalculateButton, mVisitButton, sendEmailButton;
    private EditText mAmountView, mURLEditText, emailContentText, emailRecipentText;
    private TipNTaxCalculator calculator;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == TIP_N_TAX_TOTAL_RESULT) {
            if (data == null) return;
            double computedTotal = data.getDoubleExtra(TipCalcActivity.TIP_N_TAX_COMPUTED_TOTAL, TipNTaxCalculator.InvalidResult);
            String computedTotalStr = "" + computedTotal;
            mAmountView.setText(computedTotalStr);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculateButton = (Button) findViewById(R.id.calculate_button);
        mAmountView = (EditText) findViewById(R.id.amount_edit_text);
        mVisitButton = findViewById(R.id.visit_button);
        mURLEditText = findViewById(R.id.url_edit_text);
        calculator = new TipNTaxCalculator();
        sendEmailButton = findViewById(R.id.send_button);
        emailRecipentText = findViewById(R.id.email_address_edit_text);
        emailContentText = findViewById(R.id.message_content_edit_text);

        mCalculateButton.setOnClickListener((View v) -> {
            Log.i(TAG, "Calculate Button Clicked");
            String amountStr = mAmountView.getText().toString();
            double amount = Double.parseDouble(amountStr);
            Log.i(TAG, "MainActivity::Amount = " + amount);
            //double total = calculator.calculate(amount);
            //Log.i(TAG, "Total = " + total);
            //mAmountView.setText("" + total);
            Intent intent = new Intent(MainActivity.this, TipCalcActivity.class);
            intent.putExtra(TIP_N_TAX_MAIN_AMOUNT, amount);
            //startActivity(intent);
            startActivityForResult(intent, TIP_N_TAX_TOTAL_RESULT);
        });

        mVisitButton.setOnClickListener((View v) -> {
            Log.i(TAG, "Visit Button Licked");
            String URL = mURLEditText.getText().toString();
            if (URL.length() > 0) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                startActivity(webIntent);
            }
        });

        sendEmailButton.setOnClickListener((View v) -> {
            String emailAdress = emailRecipentText.getText().toString();
            String message = emailContentText.getText().toString();

            String emailURI = "mailto:" + emailAdress;

            Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(emailURI));
            mailIntent.putExtra(Intent.EXTRA_TEXT, message);
            mailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);

            startActivity(Intent.createChooser(mailIntent, "Email client..."));

        });
    }
}