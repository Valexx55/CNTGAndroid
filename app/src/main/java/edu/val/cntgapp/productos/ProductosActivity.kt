package edu.`val`.cntgapp.productos

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.util.Constantes
import edu.`val`.cntgapp.util.RedUtil
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

/**
 *
 * Esta clase, vamos a consumir el API web
 * https://my-json-server.typicode.com/miseon920/json-api/products
 *
 * y vamos a representar los productos
 * @see ListadoProductos
 */
class ProductosActivity : AppCompatActivity() {

    lateinit var productoService: ProductoService
    lateinit var listadoProductos: ListadoProductos
    lateinit var recyclerView:RecyclerView
    lateinit var productosAdapter: ProductosAdapter
    lateinit var progressBar: ProgressBar
    lateinit var slider: Slider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        //crear el prodcutoService, que es el objeto encargado de traerse los datos
        //como nos indica Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productoService = retrofit.create(ProductoService::class.java)




        if (RedUtil.hayInternet(this))
        {
            //CARGAR PRODCUTOS
            //CORUTINA - PEQUEÑO PROGRAMA PARALELO, QUE SE ENCARGAR DE HACER LA COMUNICACIÓN HTTP
            lifecycleScope.launch {
                //aquí, pedimos los datos

                //this@ProductosActivity //sería el this de fuera
                Log.d(Constantes.ETIQUETA_LOG, "Pidiendo Datos")
                Log.d(Constantes.ETIQUETA_LOG, "Mostrar  Datos recibidos")

                listadoProductos = productoService.obtenerProductos()
                Log.d(Constantes.ETIQUETA_LOG, "Hemos RX ${listadoProductos.size} productos")
                //it es una variable auxiliar, predefinida en Kotlin
                //para cuando recorro una colección (itero)
                //que va asumiendo el valor de cada elemento de la colección
                listadoProductos.forEach{
                    Log.d(Constantes.ETIQUETA_LOG, "Producto ${it.toString()}")
                }

                this@ProductosActivity.recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProductos)
                val layoutRecycler: RecyclerView.LayoutManager = LinearLayoutManager(this@ProductosActivity, RecyclerView.VERTICAL,false)
                recyclerView.layoutManager = layoutRecycler
                productosAdapter = ProductosAdapter(listadoProductos)
                recyclerView.adapter = productosAdapter

                Log.d(Constantes.ETIQUETA_LOG, "Mostrar  Datos recibidos")
                this@ProductosActivity.progressBar = findViewById<ProgressBar>(R.id.barraProgreso)
                this@ProductosActivity.progressBar.visibility = View.INVISIBLE
                actualizarPostCarga()
            }
        } else {
            Toast.makeText(this, "SIN CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show()
            Log.w(Constantes.ETIQUETA_LOG, "SIN CONEXIÓN A INTERNET")
        }

        Log.d(Constantes.ETIQUETA_LOG, "en Oncreate")
    }

    private fun actualizarPostCarga() {
        //INICIALIZAR EL SLIDER
        this.slider = findViewById<Slider>(R.id.sliderprecio)
        this.slider.visibility = View.VISIBLE

        //obtenemos el producto producto más caro
        var productoMasCaro = listadoProductos.maxBy { it.price.toFloat() }
        //var precioMasCaro = listadoProductos.map { it.price.toFloat() }.max()
        //obtenemos el  producto más barato
        var prodcutoMasBarato = listadoProductos.minBy { it.price.toFloat() }
        //obtenemos el valor producto medio
        var precioMedio = listadoProductos.map { it.price.toFloat() }.average()


        slider.value = precioMedio.toFloat() // valor por defecto donde aparece ubicado
        slider.valueFrom = prodcutoMasBarato.price.toFloat()// valor mínimo del slider
        slider.valueTo = productoMasCaro.price.toFloat()// valor máximo del slider

        //este método dibuja la banderita/el valor donde se para el slider
        slider.setLabelFormatter {
            "${it.roundToInt()} precio máx"
        }

        slider.addOnChangeListener { slider, valor, isUser ->
            Log.d(Constantes.ETIQUETA_LOG, "Valor actual = $valor $isUser")
            var listadoProductosFiltrados = ListadoProductos()
            listadoProductos.filter { producto -> producto.price.toFloat() <= valor }.toCollection(listadoProductosFiltrados)
            productosAdapter.listaProductos = listadoProductosFiltrados
            productosAdapter.notifyDataSetChanged() //los datos de la lista han cambiado, repíntate

        }

    }

    override fun onStart() {
        Log.d(Constantes.ETIQUETA_LOG, "en Start")
        super.onStart()
    }
}