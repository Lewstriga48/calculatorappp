package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText resultTextView;
    private double operand1 = Double.NaN;
    private String pendingOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        Button btnZero = findViewById(R.id.btn_zero);
        Button btnOne = findViewById(R.id.btn_one);
        Button btnTwo = findViewById(R.id.btn_two);
        Button btnThree = findViewById(R.id.btn_three);
        Button btnFour = findViewById(R.id.btn_four);
        Button btnFive = findViewById(R.id.btn_five);
        Button btnSix = findViewById(R.id.btn_six);
        Button btnSeven = findViewById(R.id.btn_seven);
        Button btnEight = findViewById(R.id.btn_eight);
        Button btnNine = findViewById(R.id.btn_nine);

        Button btnAdd = findViewById(R.id.btn_add);
        Button btnSubtract = findViewById(R.id.btn_subtract);
        Button btnMultiply = findViewById(R.id.btn_multiply);
        Button btnDivide = findViewById(R.id.btn_divide);

        Button btnEquals = findViewById(R.id.btn_equals);
        Button btnClean = findViewById(R.id.btn_clean);

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                resultTextView.append(button.getText().toString());
            }
        };

        btnZero.setOnClickListener(numberClickListener);
        btnOne.setOnClickListener(numberClickListener);
        btnTwo.setOnClickListener(numberClickListener);
        btnThree.setOnClickListener(numberClickListener);
        btnFour.setOnClickListener(numberClickListener);
        btnFive.setOnClickListener(numberClickListener);
        btnSix.setOnClickListener(numberClickListener);
        btnSeven.setOnClickListener(numberClickListener);
        btnEight.setOnClickListener(numberClickListener);
        btnNine.setOnClickListener(numberClickListener);

        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String operation = button.getText().toString();
                String value = resultTextView.getText().toString();

                if (!value.isEmpty()) {
                    performOperation(Double.valueOf(value), operation);
                }

                pendingOperation = operation;
                resultTextView.setText("");
            }
        };

        btnAdd.setOnClickListener(operationClickListener);
        btnSubtract.setOnClickListener(operationClickListener);
        btnMultiply.setOnClickListener(operationClickListener);
        btnDivide.setOnClickListener(operationClickListener);

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = resultTextView.getText().toString();
                if (!value.isEmpty() && !Double.isNaN(operand1)) {
                    performOperation(Double.valueOf(value), pendingOperation);
                    pendingOperation = "";
                }
            }
        });

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText("");
                operand1 = Double.NaN;
                pendingOperation = "";
            }
        });
    }

    private void performOperation(double value, String operation) {
        if (Double.isNaN(operand1)) {
            operand1 = value;
        } else {
            if (pendingOperation.equals("+")) {
                operand1 += value;
            } else if (pendingOperation.equals("-")) {
                operand1 -= value;
            } else if (pendingOperation.equals("*")) {
                operand1 *= value;
            } else if (pendingOperation.equals("/")) {
                if (value != 0) {
                    operand1 /= value;
                } else {
                    operand1 = Double.NaN; // İşlem geçersiz olduğunda NaN (Not a Number) değeri kullanılır.
                }
            }
        }
        resultTextView.setText(String.valueOf(operand1));
    }
}
