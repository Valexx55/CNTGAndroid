package edu.`val`.cntgapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import edu.`val`.cntgapp.MenuPrincipalActivity
import edu.`val`.cntgapp.R

class RegistroNuevoUsuarioActivity : AppCompatActivity() {

    //para conectarnos con el servicio de Auth de Firebase
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_nuevo_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.firebaseAuth = FirebaseAuth.getInstance()
    }

    fun registrarNuevoUsuario(view: View) {

        var correoNuevo = findViewById<EditText>(R.id.editTextText).text.toString()
        var passwordNueva = findViewById<EditText>(R.id.editTextText2).text.toString()

        this.firebaseAuth.createUserWithEmailAndPassword(correoNuevo, passwordNueva)
            .addOnCompleteListener()
            { tarea ->
                if (tarea.isSuccessful) {
                    Toast.makeText(this, "NUEVO USUARIO REGISTRADO", Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(Intent(this, AutenticacionActivity::class.java))
                } else {
                    Toast.makeText(this, "ERROR AL REGISTRAR NUEVO USUARIO", Toast.LENGTH_LONG)
                        .show()
                }
            }

    }
}