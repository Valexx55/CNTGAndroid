package edu.`val`.cntgapp.imc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.`val`.cntgapp.R


//imc = PESO / ALTURA AL CUADRADO


class IMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcularIMC(view: View)//:Unit cuando no devuelve nada
    {
        Log.d("MIAPP", "Botón calcular IMC ha sido tocado")

        //1obtener peso
        val etpeso = findViewById<EditText>(R.id.editTextPeso)
        val peso = etpeso.text.toString().toFloat()
        //2obtner altura
        val etaltura = findViewById<EditText>(R.id.editTextAltura)
        val altura = etaltura.text.toString().toFloat()
        //3calcular el imc
        val imc = peso / (altura*altura)
        //4informar resultado
        val aviso = Toast.makeText(this, "Su IMC es $imc", Toast.LENGTH_LONG)
        aviso.show()
        //TODO transitar a la actividad resultado, para mostrar con una foto
        //el tipo de IMC que tiene el usuario
        val intentResultado : Intent = Intent(this, ResultadoImcActivity::class.java)
        intentResultado.putExtra("IMC_RESULTADO", imc)//guardo en el saco el imc
        startActivity(intentResultado)//lanzo la otra pantalla de resultado EXPLÍCITO
    }
}