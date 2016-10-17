package com.android.example.justjava;

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
            Toast.makeText(this, "You can not order more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "You must order at least 1 Coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        mQuantity--;
        update();
    }
    /**
    * Submit order function - this is called by onClick of the Order button
    */
    public void submitOrder(View view) {
        CheckBox whippedCheckBox = (CheckBox) findViewById(R.id.whipped_topping_checkbox);
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        EditText nameText = (EditText) findViewById(R.id.name_edit_text);
        boolean isWhipped = whippedCheckBox.isChecked();
        boolean isChocolate = chocolateCheckbox.isChecked();
        int price = calculatePrice(isWhipped, isChocolate);
        String name = nameText.getText().toString();

        String message = createOrderSummary(price, isWhipped, isChocolate, name);
        displayMessage(message);
    }

    /**
    * Method that updates the quantity and price
    */
    private void update () {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("Cups: " + mQuantity);
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
     * Method that displayes price text view message
     * @param message to be displayed
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Method that creates an order summar
     * @param price of coffee
     * @param isWhipped boolean value - has whipped cream been added?
     * @param hasChocolate boolean value - has chocolate been added?
     * @param nameText String - order's name
     * @return message of order summary
     */
    private String createOrderSummary(int price, boolean isWhipped, boolean hasChocolate, String nameText) {
        String message;
        message = nameText;
        message += "\nWhip Added? " + isWhipped;
        message += "\nHas Chocolate? " + hasChocolate;
        message += "\nQuantity: " + mQuantity;
        message += "\nTotal: " + NumberFormat.getCurrencyInstance().format(price);
        message += "\nThanks";
        return message;
    }
}
