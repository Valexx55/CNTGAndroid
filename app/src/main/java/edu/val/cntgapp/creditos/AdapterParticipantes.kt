package edu.`val`.cntgapp.creditos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.kotlinbasico.Participante

/**
 * Esta clase, es el Adapter del Recycler (Lista)
 * A él, le irá dando las filas con los participantes que deben mostrarnse
 */
class AdapterParticipantes (var listaParticipantes:List<Participante>) : Adapter<ParticipanteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ParticipanteViewHolder
    {
        //"inflar / crear fila"
        var participanteViewHolder: ParticipanteViewHolder

            var layoutInflater = LayoutInflater.from(parent.context)
            var fila_participante = layoutInflater.inflate(R.layout.fila_participante, parent, false)
            participanteViewHolder = ParticipanteViewHolder(fila_participante)


        return  participanteViewHolder
    }

    override fun getItemCount(): Int {
         return this.listaParticipantes.size
    }

    override fun onBindViewHolder(holder: ParticipanteViewHolder, position: Int)
    {
       //"cargar/rellenar el viewHolder/Fila
        var participante = this.listaParticipantes.get(position)
        holder.rellenarViewHolderParticipante(participante)

    }
}