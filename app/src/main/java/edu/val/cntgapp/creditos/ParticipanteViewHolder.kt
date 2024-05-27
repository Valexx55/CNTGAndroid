package edu.`val`.cntgapp.creditos

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.kotlinbasico.Participante

/**
 * Esta clase, contiene a cada item o fila
 * cada fila, que está definidad en fila_participante. xml
 * se inflará
 */
class ParticipanteViewHolder(itemView:View): ViewHolder(itemView)  {

    //VISTAS QUE TENEMOS EN LA FILA DEL XML
    val nombre:TextView = itemView.findViewById<TextView>(R.id.nombreParticipante)
    val iconoGH:ImageView = itemView.findViewById<ImageView>(R.id.nombreParticipante)
    val iconoIn:ImageView = itemView.findViewById<ImageView>(R.id.nombreParticipante)

    fun rellenarViewHolderParticipante (participante: Participante)
    {
        nombre.text = participante.nombre
        //TODO tratar los enlaces TAG - Vista setTag
    }

}