package edu.`val`.cntgapp.creditos

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.kotlinbasico.Participante
import edu.`val`.cntgapp.util.Constantes

/**
 * Esta clase, contiene a cada item o fila
 * cada fila, que está definidad en fila_participante. xml
 * se inflará
 */
class ParticipanteViewHolder(itemView:View): ViewHolder(itemView)  {

    //VISTAS QUE TENEMOS EN LA FILA DEL XML
    val nombre:TextView = itemView.findViewById<TextView>(R.id.nombreParticipante)
    val iconoGH:ImageView = itemView.findViewById<ImageView>(R.id.logoGH)
    val iconoIn:ImageView = itemView.findViewById<ImageView>(R.id.logoIN)

    fun rellenarViewHolderParticipante (participante: Participante)
    {
        nombre.text = participante.nombre
        iconoGH.tag = participante.urlGitHub
        iconoIn.tag = participante.urlLinkedin
        iconoGH.setOnClickListener(this::abrirWeb)//method reference
        iconoIn.setOnClickListener(this::abrirWeb)//method reference

    }

    //DRY dont repeat yoursef
    fun abrirWeb (view: View):Unit{

        var url = view.tag as String//casting de Object a String
        if (!url.isEmpty()) //si el participante, tiene url
        {
            Log.d(Constantes.ETIQUETA_LOG, "Icono tocado $url")
            var intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            if (intentWeb.resolveActivity(this.itemView.context.packageManager)!=null)//y tiene navegador
            {
                this.itemView.context.startActivity(intentWeb)    //tiro
            }
        } else {
            //toast enlace no disponible
        }

    }

}