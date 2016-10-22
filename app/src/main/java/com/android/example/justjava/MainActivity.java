package com.android.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
This App is part of Udacity Beginners Lesson 2 - it's an order coffee form
 */
public class MainActivity extends AppCompatActivity {

    protected int mQuantity = 2; //Cups quantity
    private static final int BASE_PRICE = 5; //price constant

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update();
    }
    /**
    * Increment Quantity - this is called by increment "+" button
    */
    public void increment(View view) {
        if (mQuantity == 100) {
            Toast.makeText(this, getString(R.string.toast_too_many_cups), Toast.LENGTH_SHORT).show();
            return;
        }
        mQuantity++;
        update();
    }
    /**
    * Decrement Quantity - this is called by increment "-" button
    */
    public void decrement(View view) {
        if (mQuantity == 1) {
            Toast.makeText(this, getString(R.string.toast_too_few_cups), Toast.LENGTH_SHORT).show();
            return;
        }
        mQuantity--;
        update();
    }
    /**
    * Submit order function - this is called by onClick of the Order button
    * creates an order summary, subject and sends in email.
    */
    public void submitOrder(View view) {
        CheckBox whippedCheckBox = (CheckBox) findViewById(R.id.whipped_topping_checkbox);
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        EditText nameText = (EditText) findViewById(R.id.name_edit_text);
        boolean isWhipped = whippedCheckBox.isChecked();
        boolean isChocolate = chocolateCheckbox.isChecked();
        int price = calculatePrice(isWhipped, isChocolate);
        String name = nameText.getText().toString();

        //Compose the message and send
        String message = createOrderSummary(price, isWhipped, isChocolate, name);
        String subject = getString(R.string.order_subject, name);
        composeEmail(subject, message);

    }

    /**
    * Method that updates the quantity and price
    */
    private void update () {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + mQuantity);
    }

    /**
     * Method that calculates price
     * @return price
     * @param isChocolate - boolean of chocolate price
     * @param isWhipped - boolean of whipped price
     */
    private int calculatePrice(boolean isWhipped, boolean isChocolate) {
        int whipped = 0;
        int chocolate = 0;

        if (isWhipped) {
            whipped = 1;
        }
        if (isChocolate) {
            chocolate = 2;
        }

        return (BASE_PRICE + whipped + chocolate) * mQuantity;
    }

    /**
     * Method that creates an order summary
     * @param price of coffee
     * @param hasWhip boolean value - has whipped cream been added?
     * @param hasChocolate boolean value - has chocolate been added?
     * @param nameText String - order's name
     * @return message of order summary
     */
    private String createOrderSummary(int price, boolean hasWhip, boolean hasChocolate, String nameText) {
        String message;
        message = getString(R.string.order_summary_name, nameText);
        message += "\n" + getString(R.string.order_summary_whipped, hasWhip);
        message += "\n" + getString(R.string.order_summary_chocolate, hasChocolate);
        message += "\n" + getString(R.string.order_summary_quantity, mQuantity);
        message += "\n" + getString(R.string.order_summary_total, NumberFormat.getCurrencyInstance().format(price));
        message += "\n" + getString(R.string.thank_you);
        return message;
    }

    /**
     * composeEmail - creates a new intent and sends email with subject, message body strings
     * @param subject subject of the email to send
     * @param messageBody message body of the email
     */
    public void composeEmail(String subject, String messageBody) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, messageBody);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}