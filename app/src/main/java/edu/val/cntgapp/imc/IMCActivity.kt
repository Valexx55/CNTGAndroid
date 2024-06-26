package edu.`val`.cntgapp.imc

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.util.Constantes


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
        val botonCalculo = findViewById<Button>(R.id.botonCalcular)
        /*botonCalculo.setOnClickListener{
            Log.d(Constantes.ETIQUETA_LOG, "Ha tocado el botón")

        }*/

    }

    fun calcularIMC(view: View)//:Unit cuando no devuelve nada
    {
        Log.d(Constantes.ETIQUETA_LOG, "Botón calcular IMC ha sido tocado")

        //1obtener peso
        val etpeso = findViewById<EditText>(R.id.editTextPeso)
        val peso = etpeso.text.toString().toFloat()
        //2obtner altura
        val etaltura = findViewById<EditText>(R.id.editTextAltura)
        val altura = etaltura.text.toString().toFloat()
        //3calcular el imc
        //val imc = peso / (altura*altura)
        val oimc= IMC(peso, altura)
        val imc = oimc.calcularImc()
        //4informar resultado
        val aviso = Toast.makeText(this, "Su IMC es $imc", Toast.LENGTH_LONG)
        aviso.show()
        //TODO transitar a la actividad resultado, para mostrar con una foto
        //el tipo de IMC que tiene el usuario
        val intentResultado : Intent = Intent(this, ResultadoImcActivity::class.java)
        intentResultado.putExtra("IMC_RESULTADO", imc)//guardo en el saco el imc
        startActivity(intentResultado)//lanzo la otra pantalla de resultado EXPLÍCITO
    }

    //para dibujar mi menú en la app bar / tool bar hay que sobreescribir el método onCreateOptionsMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_imc, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //este método será invocado por Android cuando el usuario toque alguna opción del App Bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(Constantes.ETIQUETA_LOG, "Ha tocado el menú ")
        when (item.itemId)
        {
            R.id.opcionLimpiar -> {
                Log.d(Constantes.ETIQUETA_LOG, "Ha tocado limpiar ")
                limpiarFormulario()

            }
            R.id.opcionSalir -> {
                Log.d(Constantes.ETIQUETA_LOG, "Ha tocado Salir ")
                salir()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun salir() {
        //mostrar un diálogo típico de estás seguro??=

        var alertDialog = AlertDialog.Builder(this)
            .setTitle("SALIR")
            .setMessage("¿Desea salir?")
            .setNegativeButton("NO"){ dialog:DialogInterface, opcion:Int ->
                dialog.cancel()
            }
            .setPositiveButton("SÍ"){ dialog:DialogInterface, opcion:Int ->
                this.finish()

            }.create()

        alertDialog.show()
        //finishAffinity()//es para salir de la app
        //finish()//salir de la actividad
    }

    private fun limpiarFormulario() {
        findViewById<EditText>(R.id.editTextPeso).text.clear()
        findViewById<EditText>(R.id.editTextAltura).text.clear()


    }
}