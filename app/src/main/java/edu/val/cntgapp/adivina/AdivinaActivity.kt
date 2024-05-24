package edu.`val`.cntgapp.adivina

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.util.Constantes

class AdivinaActivity : AppCompatActivity() {

    var numeroRandom:Int=0
    var contadorIntentos = 5
    var mensaje:String=""
    lateinit var textoAdivina:EditText
    lateinit var textoMensajeIntentos:TextView


    override fun onStart() {
        super.onStart()
        Log.d(Constantes.ETIQUETA_LOG, "En ONSTART")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Constantes.ETIQUETA_LOG, "En ONSTART")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Constantes.ETIQUETA_LOG, "En RESUME")
    }
    override fun onCreate(bundle: Bundle?) { //cuando el tipo lleva ? es que puede ser nulo
        super.onCreate(bundle)
        setContentView(R.layout.activity_adivina)
        vernum()
        textoAdivina = findViewById<EditText>(R.id.editTextNumero)
        textoMensajeIntentos = findViewById<TextView>(R.id.textContadorIntentos)
        //si bundle es distinto de null, significa que tiene cotenido, se ha dado la vuelta
        //si el bundle es null, quiere decir, que es la primera ve
        numeroRandom = bundle?.getInt("NUM_SECRETO") ?: (0..100).random()//si el bundle es distinto de null, llama a getInt si no, con el Elvis, vale 0
        contadorIntentos = bundle?.getInt("NUM_INTENTOS") ?: 5//si el bundle es distinto de null, llama a getInt si no, con el Elvis, vale 0
        mensaje = bundle?.getString("MENSAJE") ?: "Tienes 5 intentos"
        textoMensajeIntentos.text = mensaje


    }



    fun numeroPensado(view: View) {

        // No entiendo porque carallo despues del primer intento sigue pillando 5 intentos
        contadorIntentos = contadorIntentos -1
        val strNum = textoAdivina.text.toString()
        val numeroIntroducido = if (strNum=="")
        {
            0
        } else{
            textoAdivina.text.toString().toInt()
        }

        Log.d(Constantes.ETIQUETA_LOG, "numeroIntroducido = $numeroIntroducido")

        when {

            contadorIntentos == 0 ->{


                val mensaje_derrota = resources.getString(R.string.mensaje_derrota)

                val aviso = Toast.makeText(this, mensaje_derrota, Toast.LENGTH_SHORT)
                //MÉTODO CARLOS, poniendo como segundo parametro el atributo R
                //val aviso2 = Toast.makeText(this, R.string.mensaje_derrota, Toast.LENGTH_SHORT)
                aviso.show()
               // finish()
                this.mensaje ="Te quedan $contadorIntentos intentos"
                textoMensajeIntentos.text = this.mensaje
                findViewById<Button>(R.id.botonreiniciar).visibility=View.VISIBLE
                findViewById<Button>(R.id.buttonIntento).isClickable=false
            }

            numeroIntroducido.toInt() < numeroRandom ->{


                val aviso = Toast.makeText(this, "Tu número $numeroIntroducido es menor al que he pensado. Prueba de nuevo. Te quedan $contadorIntentos intentos", Toast.LENGTH_SHORT)
                aviso.show()
                this.mensaje ="Te quedan $contadorIntentos intentos"
                textoMensajeIntentos.text = this.mensaje
                //textoMensajeIntentos.text = "Te quedan $contadorIntentos intentos"
            }
            numeroIntroducido.toInt() > numeroRandom ->{

                val aviso = Toast.makeText(this, "Tu número $numeroIntroducido es mayor al que he pensado. Prueba de nuevo.  Te quedan $contadorIntentos intentos", Toast.LENGTH_SHORT)
                aviso.show()
                this.mensaje ="Te quedan $contadorIntentos intentos"
                textoMensajeIntentos.text = this.mensaje
            }
            numeroIntroducido.toInt() == numeroRandom ->{

                val mensaje_victoria = resources.getString(R.string.mensaje_victoria)
                val aviso = Toast.makeText(this, mensaje_victoria, Toast.LENGTH_SHORT)
                aviso.show()
               // finish()
                findViewById<Button>(R.id.botonreiniciar).visibility=View.VISIBLE
                findViewById<Button>(R.id.buttonIntento).isClickable=false

            }
        }
    }

    fun vernum() {
        Log.d(Constantes.ETIQUETA_LOG, "numero que pensé es $numeroRandom")
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        //Android, invoca a este método automáticamente cuando la actividad se va a recrear
        //me la oportunidad de guardar la información no visual de la actividad
        bundle.putInt("NUM_SECRETO", numeroRandom)
        bundle.putInt("NUM_INTENTOS", contadorIntentos)
        bundle.putString("MENSAJE", mensaje)
    }

    fun reiniciar(view: View) {

        //recreate()
        finish()
        startActivity(intent)
        //finishAffinity()//para finalizar del ttodo
    }
}



