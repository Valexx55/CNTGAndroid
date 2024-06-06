package edu.`val`.cntgapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.`val`.cntgapp.R


/**
 * ACTIVIDAD DE BIENVENIDA...¿NUEVO REGISTRO O ACCESO?
 */
class MenuLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_login)
        supportActionBar?.hide()//oculto la barra superior AppBar ToolBar
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN//pantalla completa

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun aNuevaCuenta(view: View) {
        val intentNueva = Intent(this, RegistroNuevoUsuarioActivity::class.java)
        startActivity(intentNueva)
    }
    fun acceder(view: View) {
        val intentLogin = Intent(this, AutenticacionActivity::class.java)
        startActivity(intentLogin)

    }
}