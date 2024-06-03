package edu.`val`.cntgapp.fechayhora

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import edu.`val`.cntgapp.util.Constantes
import java.util.Calendar

class SeleccionFecha: DialogFragment(), OnDateSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var miCalendario:Dialog
        var calendar: Calendar = Calendar.getInstance()
        var anio = calendar.get(Calendar.YEAR)
        var mes = calendar.get(Calendar.MONTH)
        var dia = calendar.get(Calendar.DATE)

            miCalendario = DatePickerDialog(requireActivity(), this, anio, mes, dia)

        return miCalendario
    }
    override fun onDateSet(p0: DatePicker?, anio: Int, mes: Int, dia: Int) {
       val fechaFinal = "$dia/${mes+1}/$anio"
       Log.d(Constantes.ETIQUETA_LOG, "FECHA SELECCIONADA = $fechaFinal")
       (activity as SeleccionFechaYHoraActivity).actualizarFechaSeleccionada(fechaFinal)
    }
}