package com.example.projeto

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alturaEditText = findViewById<EditText>(R.id.editTextAltura)
        val pesoEditText = findViewById<EditText>(R.id.editTextPeso)
        val calcularButton = findViewById<Button>(R.id.buttonCalcular)
        val resultadoTextView = findViewById<TextView>(R.id.textView)

        calcularButton.setOnClickListener {
            val altura = alturaEditText.text.toString().trim().toFloatOrNull()
            val peso = pesoEditText.text.toString().trim().toFloatOrNull()

            if (altura == null || peso == null) {
                resultadoTextView.text = "Erro: Insira valores numericos."
                return@setOnClickListener
            }

            if (altura < 0.5 || altura > 2.5) {
                resultadoTextView.text = "Erro: Altura deve estar entre 0.5m e 2.5m."
                return@setOnClickListener
            }

            if (peso < 20 || peso > 300) {
                resultadoTextView.text = "Erro: Peso deve estar entre 20kg e 300kg."
                return@setOnClickListener
            }

            val imc = peso / (altura * altura)
            var classificacao = ""

            if (imc < 18.5) {
                classificacao = "Abaixo do peso"
            } else if (imc >= 18.5 && imc < 24.9) {
                classificacao = "Peso normal"
            } else if (imc >= 24.9 && imc < 29.9) {
                classificacao = "Sobrepeso"
            } else if (imc >= 29.9 && imc < 34.9) {
                classificacao = "Obesidade grau 1"
            } else if (imc >= 34.9 && imc < 39.9) {
                classificacao = "Obesidade grau 2"
            } else {
                classificacao = "Obesidade grau 3"
            }

            resultadoTextView.text = "IMC: %.2f\n%s".format(imc, classificacao)
        }
    }
}
