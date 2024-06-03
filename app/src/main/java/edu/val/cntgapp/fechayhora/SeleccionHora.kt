package edu.`val`.cntgapp.fechayhora

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import edu.`val`.cntgapp.util.Constantes
import java.util.Calendar

class SeleccionHora: DialogFragment(), OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var reloj:Dialog
        var hora:Int
        var minutos:Int
        var calendario: Calendar = Calendar.getInstance() //me da la fecha/hora actuales del Sistema

            hora = calendario.get(Calendar.HOUR_OF_DAY)
            minutos = calendario.get(Calendar.MINUTE)
            reloj = TimePickerDialog(requireActivity(), this, hora, minutos, true)

        return reloj
    }

    override fun onTimeSet(reloj: TimePicker?, horaSeleccionada: Int, minutosSeleccionados: Int) {
        val horaFinal:String
        val hora:String
        val minuto:String

        hora = if (horaSeleccionada<10) "0$horaSeleccionada" else horaSeleccionada.toString()
        minuto = if (minutosSeleccionados<10) "0$minutosSeleccionados" else minutosSeleccionados.toString()
        horaFinal = "$hora:$minuto" //FORMATO HH:MM

        //activity, es la actividad "padre" desde donde eclosiona/nace a la que pertenece este fragment
        //los fragment / dialogFragment no tienen vida separada de una Activity

        var ventanaPadre = activity as SeleccionFechaYHoraActivity
        ventanaPadre.actualizarHoraSeleccionada(horaFinal)

        Log.d(Constantes.ETIQUETA_LOG, "Hora Final = $horaFinal")
    }
}