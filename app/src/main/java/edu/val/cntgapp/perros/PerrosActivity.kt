package edu.`val`.cntgapp.perros

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.databinding.ActivityPerrosBinding

class PerrosActivity : AppCompatActivity() {


    lateinit var binding: ActivityPerrosBinding
    val arrayRazas = arrayOf("affenpinscher", "african", "airedale", "akita", "appenzeller", "australian")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPerrosBinding.inflate(layoutInflater)
        setContentView(binding.main)
        val adapterSpinner = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayRazas)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)//estilo cuando el selector está desplegado
        binding.spinnerRazas.adapter = adapterSpinner


    }

    fun buscarFotos(view: View) {}
}