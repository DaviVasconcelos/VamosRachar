package com.example.vamosrachar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import android.text.TextWatcher

class MainActivity : AppCompatActivity() {
    // Faz com que cada variável seja chamada por um meio, no caso os dois são pelo EditText
    private lateinit var valorTotal: EditText
    private lateinit var nPessoas: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pega o valor escrito nas EditText pelo id
        valorTotal = findViewById(R.id.edit)
        nPessoas = findViewById(R.id.edit_2)

        // Adiciona TextChangedListener aos campos de texto
        valorTotal.addTextChangedListener(textWatcher)
        nPessoas.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Não é necessário implementar este método
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Quando o texto é alterado, chama a função calcularValor
            calcularValor()
        }

        override fun afterTextChanged(s: Editable?) {
            // Não é necessário implementar este método
        }
    }

    // Função que divide o valor pelo número de pessoas
    private fun calcularValor() {
        // converte os valores para double ou null
        val vTotal = valorTotal.text.toString().toDoubleOrNull()
        val Pessoas = nPessoas.text.toString().toDoubleOrNull()

        if (vTotal != null && Pessoas != null) {
            // Caso ambos não sejam null (ou seja, sejam double) calcula a razão
            val razao = vTotal / Pessoas

            // Atualiza o valor de result pela razão da função
            val resultadoFinal = findViewById<TextView>(R.id.result)
            resultadoFinal.text = razao.toString()
        }
    }
}