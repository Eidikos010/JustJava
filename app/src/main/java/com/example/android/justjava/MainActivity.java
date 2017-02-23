/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;
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
        int price = calculatePrice(hasChocolate, hasWhippedCream);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, userNameText);

        //Send by email the order
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); //Only email apps should handle this.
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + userNameText);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     * <p>
     * param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean chocolate, boolean cream) {
        int topping = 0;

        if (chocolate) {
            topping += 2;
        }

        if (cream) {
            topping += 1;
        }

        return quantity * (5 + topping);
    }

    public void increment(View view) {

        if (quantity == 100) {
            Toast.makeText(MainActivity.this, "Ε πόσους θα πιεις ρε μαλάκα;", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    public void decrement(View view) {

        if (quantity == 1) {
            Toast.makeText(MainActivity.this, "Μήπως να σου δώσουμε και λεφτά;", Toast.LENGTH_SHORT).show();
            return;
        }

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