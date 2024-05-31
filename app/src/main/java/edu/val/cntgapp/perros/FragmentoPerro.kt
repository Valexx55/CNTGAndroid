package edu.`val`.cntgapp.perros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import edu.`val`.cntgapp.R

class FragmentoPerro: Fragment() {

    //en este método, preparamos la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //En este método "se rellena el Fragment"/el item del carrusel

        //inflar el fragment_perro
        var itemCarrusel:View? = inflater.inflate(R.layout.fragment_perro, container, false)

        val urlFoto =  arguments?.getString("url_foto")
        val textoLeyenda = arguments?.getString("texto_leyenda")

        var imageViewPerro =  itemCarrusel?.findViewById<ImageView>(R.id.imageViewPerro)
        var textViewLeyenda =  itemCarrusel?.findViewById<TextView>(R.id.textLeyendaPerro)

        textViewLeyenda?.text = textoLeyenda//1 de 9 pej
        Picasso.get().load(urlFoto).into(imageViewPerro)

        return itemCarrusel
    }
}