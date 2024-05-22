package edu.`val`.cntgapp.versiones



import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.`val`.cntgapp.R

//ESTO ES UNA PANTALLA
//EN EL CÓDIGO FUENTE, AQUÍ, PROGRAMAMAMOS LA FUNCIONALIDAD
//ASOCIADO A ESTA CLASE ESTÁ EL ARCHIVO DE LAYOUT (APARIENCIA)
//LA APARIENCIA ES UN XML (ESTÁTICA)

class VersionesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//TODO EXPLICAR BIEN EL LOG CON EL DEBUG
        //HACEMOS UNA APP QUE INFORME DEL API (NUMERO DEL SDK)
        //DE ANDROID DONDE SE ESTÁ EJECUTANDO LA APLICACIÓN
        Log.d("MIETIQUETA", "HA pasado por Oncreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val strVersion = obtenerVersionAndroid()

        Log.d("MIETIQUETA", "La versión del móvil es $strVersion")//plantilla para evitar concatenar
        //Log.d("MIETIQUETA","El nombre de la versión es ${Build.VERSION.CODENAME}")//plantilla para evitar concatenar

        val cajaTexto:TextView = findViewById<TextView>(R.id.textViewNombre)//1 pillo la caja
        cajaTexto.text = strVersion//2 le doy un contenido
    }

    fun obtenerVersionAndroid(): String {
        var nombreVersion: String? =
            null //? --> significa que nomberVersion puede ser String o null

        //TODO obtener la versión


        nombreVersion = when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.Q -> "ANDROID Q 10"
            Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> "ANDROID U 14"
            else -> "Versión distinta a la 10 o 14"
        }

        return nombreVersion
    }

}