/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean checked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order, the plus and the minus button is clicked.
     */
    public void submitOrder(View view) {
        
        //Gets the text for the Name
        EditText nameText = (EditText) findViewById(R.id.nameText);
        String userNameText = nameText.getText().toString();

        //Gets the boolean value for the Whipped Cream Box
        CheckBox whippedCreamCkeckBox = (CheckBox) findViewById(R.id.whipped_cream_ckeckbox);
        boolean hasWhippedCream = whippedCreamCkeckBox.isChecked();

        //Gets the boolean value for the Chocolate Box
        CheckBox chocolateCkeckBox = (CheckBox) findViewById(R.id.chocolate_ckeckbox);
        boolean hasChocolate = chocolateCkeckBox.isChecked();

        //Calls the methods for calculating the price and then creating the order summary
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, userNameText);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     * <p>
     * param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Create the summary that is displayed uppon order
     *
     * @return String of the whole message
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String orderSummary = "Name: " + name;
        orderSummary += "\nAdd whipped Cream? " + hasWhippedCream;
        orderSummary += "\nAdd chocolate? " + hasChocolate;
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: $" + price + "\nThank you!";
        return orderSummary;
    }
}