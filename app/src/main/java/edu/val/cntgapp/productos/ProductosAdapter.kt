package edu.`val`.cntgapp.productos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.`val`.cntgapp.R

class ProductosAdapter(var listaProductos: List<ListadoProductosItem>) : RecyclerView.Adapter<ProductosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        var productosViewHolder: ProductosViewHolder

        var layoutInflater = LayoutInflater.from(parent.context)
        var filaProducto = layoutInflater.inflate(R.layout.fila_producto, parent, false)

        productosViewHolder = ProductosViewHolder(filaProducto)

        return productosViewHolder
    }

    override fun getItemCount(): Int {
        return this.listaProductos.size
    }

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        var producto = this.listaProductos.get(position)
        holder.rellenarProductosViewHolder(producto)
    }

}
