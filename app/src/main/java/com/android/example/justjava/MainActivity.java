package com.android.example.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    /*
    This App is part of Udacity Beginners Lesson 2 - it's an order coffee form
     */
    protected int mQuantity = 2;
    private static final int PRICE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update();
    }

    // Increment Quantity - this is called by increment "+" button
    public void increment(View view) {
        mQuantity++;
        update();
    }

    // Decrement Quantity - this is called by increment "-" button
    public void decrement(View view) {
        mQuantity--;
        update();
    }

    // Submit order function - this is called by onClick of the Order button
    public void submitOrder(View view) {
        updatePrice();
    }

    // Method that updates the quantity and price
    private void update () {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("Cups: " + mQuantity);
    }

    // Method that updates the quantity and price
    private void updatePrice () {
        String priceMessage = NumberFormat.getCurrencyInstance().format(calculatePrice());

        priceMessage = "Total: " + priceMessage;

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
       // priceTextView.setText(NumberFormat.getCurrencyInstance().format(mQuantity * PRICE));
        priceTextView.setText(priceMessage + "\nThanks!");
    }

    private int calculatePrice() {
        int priceCoffee = mQuantity * PRICE;
        return priceCoffee;
    }
}
