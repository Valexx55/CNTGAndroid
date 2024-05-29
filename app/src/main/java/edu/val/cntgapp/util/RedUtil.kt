package edu.`val`.cntgapp.util

import android.content.Context
import android.net.ConnectivityManager

//aquí vamos a comprobar el estado de conectividad del dispositivo

object RedUtil {

    fun hayInternet(context: Context): Boolean {
        var hay = false
        //este servicio de Android me permite acceder al estado de la red
        val conectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val redEnUso = conectivityManager.activeNetwork//obtengo la red activa
        hay = (redEnUso != null)
        /*if (redEnUso != null) {
            hay = true
        } else {
            hay = false
        }*/

        return hay
    }
}