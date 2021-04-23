package com.arboleda.pruebas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val RQ_ESCUCHA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        botonescucha.setOnClickListener {
            entradaDeVoz()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RQ_ESCUCHA && resultCode == Activity.RESULT_OK){
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            cajadetexto.text = result?.get(0).toString()
        }
    }


     fun entradaDeVoz(){
         if(!SpeechRecognizer.isRecognitionAvailable(this )){
             Toast.makeText(this, "El reconocimiento no esta habilitado", Toast.LENGTH_SHORT).show()
         }else{
             val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
             i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
             i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
             i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla Porfavor")
             startActivityForResult(i,RQ_ESCUCHA)
         }
    }


}