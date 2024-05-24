package edu.`val`.cntgapp.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.`val`.cntgapp.R

class BusquedaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_busqueda)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun buscar(view: View) {

        val busqueda:String =  findViewById<EditText>(R.id.cajaBusquedaTexto).text.toString()
        //INTENT PARA VER LA PÁGINA WEB
        val url = "https://www.google.com/search?q="+busqueda
        val uri = Uri.parse(url)//paso de string a uri
        val intentImplicito = Intent(Intent.ACTION_VIEW, uri )//QUIERO VER, UNA WEB
        if (intentImplicito.resolveActivity(packageManager)!=null)
        {
            startActivity(intentImplicito)
        } else {
            Toast.makeText(this, "NO HAY UNA APP PARA VER WEBS", Toast.LENGTH_LONG).show()
        }




    }
}