package edu.`val`.cntgapp.fechayhora

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.databinding.ActivityPerrosBinding
import edu.`val`.cntgapp.databinding.ActivitySeleccionFechaYhoraBinding
import edu.`val`.cntgapp.util.Constantes

class SeleccionFechaYHoraActivity : AppCompatActivity(), OnFocusChangeListener {

    lateinit var binding:ActivitySeleccionFechaYhoraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionFechaYhoraBinding.inflate(layoutInflater)
        setContentView(binding.main)

        binding.cajaFecha.onFocusChangeListener = this //con esto, digo que cuando la caja tome el foco
        binding.cajaHora.onFocusChangeListener = this //el cursor esté ahí, se llama esa función


    }

    //tanto si las cajas ganan el foco, como si lo pierden, se invoca a esta función
    override fun onFocusChange(caja: View?, tieneFoco: Boolean) {

        if (tieneFoco)
        {
            Log.d(Constantes.ETIQUETA_LOG, "FOCO GANADO")
            when (caja?.id)
            {
                R.id.cajaHora ->
                {
                    Log.d(Constantes.ETIQUETA_LOG, "La caja hora ha ganado el foco")
                    //creamos el fragmet
                    val dialogoFragmentReloj: DialogFragment = SeleccionHora()
                    //lo visualizamos
                    dialogoFragmentReloj.show(supportFragmentManager, "RELOJ")

                }
                R.id.cajaFecha ->
                {
                    Log.d(Constantes.ETIQUETA_LOG, "La caja fecha ha ganado el foco")
                }

            }
        }
    }

    fun actualizarHoraSeleccionada (hora:String)
    {
        this.binding.cajaHora.setText(hora)

    }
}