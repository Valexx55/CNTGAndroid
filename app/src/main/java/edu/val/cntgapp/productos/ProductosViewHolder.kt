package edu.`val`.cntgapp.productos

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.`val`.cntgapp.R

class ProductosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val id: TextView = itemView.findViewById<TextView>(R.id.textViewIdProducto)
    val nombre: TextView = itemView.findViewById<TextView>(R.id.textViewNombreProducto)
    val precio: TextView = itemView.findViewById<TextView>(R.id.textViewPrecio)
//    val imageUrl: TextView = itemView.findViewById<TextView>(R.id.textViewImageUrl)

    fun rellenarProductosViewHolder(producto: ListadoProductosItem) {
        id.text = producto.id.toString()
        nombre.text = producto.name
        precio.text = producto.price
//        imageUrl.text = producto.imageUrl
    }
}
