package edu.`val`.cntgapp.perros

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 *
 * Esta clase, va a proporcionar los items del Carrusel a su ViewPager asociado
 */
class AdapterFragmentosPerros (var fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    var listadoPerros:ListadoPerros? = null
    override fun getItemCount(): Int {
       //return listadoPerros?.message?.size ?: 0//!! con esto, kotlin no chequea la posibilidad de que sea nulo --> Confía, que esto no es null !!
       return listadoPerros!!.message.size//!! con esto, kotlin no chequea la posibilidad de que sea nulo --> Confía, que esto no es null !!
    }

    override fun createFragment(position: Int): Fragment {
        var fragmentoPerro: Fragment? = null //representa el item del carrusel

            fragmentoPerro = FragmentoPerro()

            var bundle = Bundle() //"saco temporal, donde puedo guardar datos"

            var urlFoto = listadoPerros?.message?.get(position)
            var posicion = position +1
            var textoLeyendaContador = "$posicion de ${listadoPerros?.message?.size}"

            bundle.putString("url_foto", urlFoto)//escribo
            bundle.putString("texto_leyenda", textoLeyendaContador)//escribo

            fragmentoPerro.arguments = bundle

        return  fragmentoPerro
    }
}