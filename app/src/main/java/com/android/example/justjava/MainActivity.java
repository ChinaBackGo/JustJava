package com.android.example.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.StringCharacterIterator;

/**
This App is part of Udacity Beginners Lesson 2 - it's an order coffee form
 */
public class MainActivity extends AppCompatActivity {

    protected int mQuantity = 2; //Cups quantity
    private static final int PRICE = 5; //price constant

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
        mQuantity++;
        update();
    }
    /**
    * Decrement Quantity - this is called by increment "-" button
    */
    public void decrement(View view) {
        mQuantity--;
        update();
    }
    /**
    * Submit order function - this is called by onClick of the Order button
    */
    public void submitOrder(View view) {
        int price = calculatePrice();
        CheckBox whippedCheckBox = (CheckBox) findViewById(R.id.whipped_topping_checkbox);
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        EditText nameText = (EditText) findViewById(R.id.name_edit_text);
        String message = createOrderSummary(price, whippedCheckBox.isChecked(), chocolateCheckbox.isChecked(), nameText.getText().toString());
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
     */
    private int calculatePrice() {
        return mQuantity * PRICE;
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
