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
import com.google.firebase.auth.FirebaseAuth
import edu.`val`.cntgapp.MenuPrincipalActivity
import edu.`val`.cntgapp.R

class AutenticacionActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_autenticacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.firebaseAuth = FirebaseAuth.getInstance()
    }

    fun login(view: View) {

        var correo = findViewById<EditText>(R.id.editTextText3).text.toString()
        var password = findViewById<EditText>(R.id.editTextText4).text.toString()


        this.firebaseAuth.signInWithEmailAndPassword(correo, password)
            .addOnCompleteListener {
                    resultado ->
                if (resultado.isSuccessful) {
                    Toast.makeText(this, "USUARIO AUTENTICADO OK", Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(Intent(this, MenuPrincipalActivity::class.java))
                } else {
                    Toast.makeText(this, "ERROR AL AUTENTICAR AL USUARIO", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }
}