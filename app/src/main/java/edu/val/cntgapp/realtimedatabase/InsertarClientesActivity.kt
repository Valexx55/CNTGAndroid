package edu.`val`.cntgapp.realtimedatabase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.databinding.ActivityInsertarClientesBinding
import edu.`val`.cntgapp.util.Constantes

const val URL_REAL_TIME_DATABASE_CLIENTES_FIREBASE = "https://cntg-38fd6-default-rtdb.europe-west1.firebasedatabase.app/"
class InsertarClientesActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference //es la conexión a la BD
    lateinit var binding: ActivityInsertarClientesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityInsertarClientesBinding.inflate(layoutInflater)
        setContentView(binding.mainic)

        this.databaseReference = FirebaseDatabase.getInstance(URL_REAL_TIME_DATABASE_CLIENTES_FIREBASE).reference
    }

    fun crearCliente(view: View) {
        //creamos el objeto Cliente
        //suponemos validez del formulario
        //Cliente(val edad:Long, val localidad:String, val nombre:String, val email:String, val nacionalidad:String, val clave:String="")
        val cliente = Cliente(binding.editTextEdadCliente.text.toString().toLong(), binding.editTextLocalidad.text.toString(),
            binding.editTextNombreCliente.text.toString(), binding.editTextEmail.text.toString(),
            binding.editTextNacionalidad.text.toString())

        val idClaveCliente = this.databaseReference.push().key //generamos un id
        cliente.clave = idClaveCliente!!
        //así se insertar en la bd
        this.databaseReference.child("clientes").child(idClaveCliente).setValue(cliente).addOnCompleteListener {
            tarea -> Toast.makeText(this, "CLIENTE INSERTADO", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "ERROR INSERTANDO CLIENTE", Toast.LENGTH_LONG).show()
            Log.e(Constantes.ETIQUETA_LOG, "ERROR INSERTANDO CLIENTE", it)
        }

    }
}