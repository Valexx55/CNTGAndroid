package edu.`val`.cntgapp.productos

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.util.Constantes
import edu.`val`.cntgapp.util.RedUtil
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

                Log.d(Constantes.ETIQUETA_LOG, "Mostrar  Datos recibidos")
            }
        } else {
            Toast.makeText(this, "SIN CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show()
            Log.w(Constantes.ETIQUETA_LOG, "SIN CONEXIÓN A INTERNET")
        }

        Log.d(Constantes.ETIQUETA_LOG, "en Oncreate")
    }

    override fun onStart() {
        Log.d(Constantes.ETIQUETA_LOG, "en Start")
        super.onStart()
    }
}