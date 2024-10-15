package com.example.calculadoraa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnsuma, btnresta, btnmulti, btndivi, btn0, btn1, btn2, btn3, btn4, btn5, btn6,
            btn7, btn8, btn9, btnIgual, btnClear;
    TextView textViewResultado;

    double valor1, valor2;
    String operacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn0 = findViewById(R.id.button_0);
        btn1 = findViewById(R.id.button_1);
        btn2 = findViewById(R.id.button_2);
        btn3 = findViewById(R.id.button_3);
        btn4 = findViewById(R.id.button_4);
        btn5 = findViewById(R.id.button_5);
        btn6 = findViewById(R.id.button_6);
        btn7 = findViewById(R.id.button_7);
        btn8 = findViewById(R.id.button_8);
        btn9 = findViewById(R.id.button_9);
        btnsuma = findViewById(R.id.button_mas);
        btnresta = findViewById(R.id.button_menos);
        btndivi = findViewById(R.id.button_divide);
        btnmulti = findViewById(R.id.button_multi);
        btnIgual = findViewById(R.id.button_igual);
        btnClear = findViewById(R.id.button_borrar);
        textViewResultado = findViewById(R.id.display);

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                textViewResultado.append(button.getText());
            }
        };

        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);

        btnsuma.setOnClickListener(view -> operacionSeleccionada("+"));
        btnresta.setOnClickListener(view -> operacionSeleccionada("-"));
        btnmulti.setOnClickListener(view -> operacionSeleccionada("*"));
        btndivi.setOnClickListener(view -> operacionSeleccionada("/"));

        btnIgual.setOnClickListener(view -> calcularResultado());
        btnClear.setOnClickListener(view -> clear());
    }

    private void operacionSeleccionada(String operacion) {
        try {
            valor1 = Double.parseDouble(textViewResultado.getText().toString());
            this.operacion = operacion;
            textViewResultado.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingrese un número válido", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcularResultado() {
        try {
            valor2 = Double.parseDouble(textViewResultado.getText().toString());
            double resultado = 0;
            switch (operacion) {
                case "+":
                    resultado = valor1 + valor2;
                    break;
                case "-":
                    resultado = valor1 - valor2;
                    break;
                case "*":
                    resultado = valor1 * valor2;
                    break;
                case "/":
                    if (valor2 != 0) {
                        resultado = valor1 / valor2;
                    } else {
                        Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
                default:
                    return;
            }
            textViewResultado.setText(String.valueOf(resultado));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingrese un número válido", Toast.LENGTH_SHORT).show();
        }
    }

    private void clear() {
        textViewResultado.setText("");
        valor1 = 0;
        valor2 = 0;
        operacion = null;
    }
}
