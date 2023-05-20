package com.example.calculatorhandlingaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private TextView answerTextView;
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText number1EditText = findViewById(R.id.editTextNumber);
            EditText number2EditText = findViewById(R.id.editTextNumber2);

            String number1String = number1EditText.getText().toString().trim();
            String number2String = number2EditText.getText().toString().trim();

            if (number1String.isEmpty() || number2String.isEmpty()) {
                // Handle case when one or both numbers are not entered
                Toast.makeText(MainActivity2.this, "Please input both numbers", Toast.LENGTH_SHORT).show();
                answerTextView.setText("");
                return;
            }

            double number1 = Double.parseDouble(number1String);
            double number2 = Double.parseDouble(number2String);
            double result = 0;

            int id = view.getId();
            if (id == R.id.button1) { // ADD button
                result = number1 + number2;
            } else if (id == R.id.button2) { // SUB button
                result = number1 - number2;
            } else if (id == R.id.button3) { // MUL button
                result = number1 * number2;
            } else if (id == R.id.button4) { // DIV button
                if (number2 == 0) {
                    // Handle case when dividing by zero
                    answerTextView.setText("NaN");
                    Toast.makeText(MainActivity2.this, "Please input number two not Zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = number1 / number2;
            }

            answerTextView.setText(String.valueOf(result));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerTextView = findViewById(R.id.answer);

        final String number1Text = "Number 1";
        final String number2Text = "Number 2";

        EditText editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && editTextNumber.getText().toString().equals(number1Text)) {
                editTextNumber.setText("");
            }
        });

        EditText editTextNumber2 = findViewById(R.id.editTextNumber2);
        editTextNumber2.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && editTextNumber2.getText().toString().equals(number2Text)) {
                editTextNumber2.setText("");
            }
        });

        // Set OnClickListener for the buttons
        Button addButton = findViewById(R.id.button1);
        addButton.setOnClickListener(buttonClickListener);

        Button subButton = findViewById(R.id.button2);
        subButton.setOnClickListener(buttonClickListener);

        Button mulButton = findViewById(R.id.button3);
        mulButton.setOnClickListener(buttonClickListener);

        Button divButton = findViewById(R.id.button4);
        divButton.setOnClickListener(buttonClickListener);
    }

}