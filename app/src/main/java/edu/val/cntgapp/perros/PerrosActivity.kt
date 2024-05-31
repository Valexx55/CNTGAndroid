package edu.`val`.cntgapp.perros

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.databinding.ActivityPerrosBinding
import edu.`val`.cntgapp.util.Constantes

//la propia clase, escucha los eventos de selección del spinner
class PerrosActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    var razaSeleccionada:String = ""
    lateinit var binding: ActivityPerrosBinding
    val arrayRazas = arrayOf("affenpinscher", "african", "airedale", "akita", "appenzeller", "australian")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPerrosBinding.inflate(layoutInflater)
        setContentView(binding.main)
        val adapterSpinner = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayRazas)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)//estilo cuando el selector está desplegado
        binding.spinnerRazas.adapter = adapterSpinner

        binding.spinnerRazas.onItemSelectedListener= this
    }

    fun buscarFotos(view: View) {

        val intentGaleria = Intent(this, GaleriaPerrosActivity::class.java)
        intentGaleria.putExtra("raza", this.razaSeleccionada)
        startActivity(intentGaleria)
    }
    override fun onItemSelected(p0: AdapterView<*>?, opcionTocada: View?, p2: Int, p3: Long) {
        //la primera vez que se carga el Spinner, aUNQUE el usuario no haya seleccionado nada
        //se invoca este método

        this.razaSeleccionada = (opcionTocada as TextView).text.toString()
        Log.d(Constantes.ETIQUETA_LOG, "Item seleccionado $razaSeleccionada")

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //nada que hacer
        //lo tendría que implemntar si una opción de las disponibles en el spinner, deja de estarlo
    }
}


