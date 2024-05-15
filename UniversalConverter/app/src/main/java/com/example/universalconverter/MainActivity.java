package com.example.universalconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerConversion;
    private EditText editTextInput;
    private TextView textViewResult;
    private final String[] conversionTypes = {"Metros a Pies", "Minutos a Segundos", "Dólar a Euro"};
    private double conversionRate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerConversion = findViewById(R.id.spinnerConversion);
        editTextInput = findViewById(R.id.editTextInput);
        textViewResult = findViewById(R.id.textViewResult);
        Button buttonConvert = findViewById(R.id.buttonConvert);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, conversionTypes);
        spinnerConversion.setAdapter(adapter);

        spinnerConversion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        conversionRate = 3.28084; //
                        break;
                    case 1:
                        conversionRate = 60; //
                        break;
                    case 2:
                        conversionRate = 1.13; //
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonConvert.setOnClickListener(v -> {
            if (!editTextInput.getText().toString().isEmpty()) {
                double input = Double.parseDouble(editTextInput.getText().toString());
                double result = input * conversionRate;
                textViewResult.setText(String.format("Resultado: %.2f", result));
            } else {
                textViewResult.setText("Por favor, ingrese un valor.");
            }
        });
    }
}
