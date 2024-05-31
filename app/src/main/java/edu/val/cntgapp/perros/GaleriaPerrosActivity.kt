package edu.`val`.cntgapp.perros

import android.os.Bundle
import android.util.Log
import android.widget.TextView
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

class GaleriaPerrosActivity : AppCompatActivity() {

    var raza:String = ""
    lateinit var textRazaPerro:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria_perros)
        raza = intent.getStringExtra("raza") ?: ""

        Log.d(Constantes.ETIQUETA_LOG, "Hay que buscar fotos de $raza")
        this.textRazaPerro = findViewById<TextView>(R.id.textRazaPerro)
        this.textRazaPerro.text = raza

        if (RedUtil.hayInternet(this))
        {
            var retrofit = Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var perroService:PerroService = retrofit.create(PerroService::class.java)
            lifecycleScope.launch {
                perroService.listarPerrosPorRaza(this@GaleriaPerrosActivity.raza);
            }

        }

    }
}