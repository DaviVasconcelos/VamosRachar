package com.example.vamosrachar

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.Locale

class MainActivity : AppCompatActivity() {
    // Faz com que cada variável seja chamada por um meio, no caso os dois são pelo EditText
    private lateinit var valorTotal: EditText
    private lateinit var nPessoas: EditText

    private lateinit var tts: TextToSpeech
    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pega o valor escrito nas EditText pelo id
        valorTotal = findViewById(R.id.edit)
        nPessoas = findViewById(R.id.edit_2)

        // Adiciona TextChangedListener aos campos de texto
        valorTotal.addTextChangedListener(textWatcher)
        nPessoas.addTextChangedListener(textWatcher)

        // Variáveis necessárias para o tts
        var speakButton = findViewById<Button>(R.id.micButton)
        var el = findViewById<TextView>(R.id.result)

        tts = TextToSpeech(this){status->
            // Caso a linguagem não seja suportada, ele falará
            if(status == TextToSpeech.SUCCESS){
                val result = tts.setLanguage(Locale.getDefault())
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(this, "language is not suported", Toast.LENGTH_LONG).show()
                }
            }
        }

        // Função principal do tts
        speakButton.setOnClickListener{
            if (el.text.toString().trim().isNotEmpty()){
                tts.speak(el.text.toString().trim(), TextToSpeech.QUEUE_FLUSH, null, null)
            } else {
                Toast.makeText(this, "Required", Toast.LENGTH_LONG).show()
            }
        }
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