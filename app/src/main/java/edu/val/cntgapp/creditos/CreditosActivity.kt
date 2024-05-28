package edu.`val`.cntgapp.creditos

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.kotlinbasico.Participante

class CreditosActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var listaParcipantes:List<Participante>
    lateinit var adapterParticipantes: AdapterParticipantes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_creditos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        this.listaParcipantes = Participantes.obtenerListaParticipantes()
        this.adapterParticipantes = AdapterParticipantes(this.listaParcipantes)
        this.recyclerView.adapter = this.adapterParticipantes//cuando vayas a pintarte recycler, le tienes que pedir los datos al adapter
        val layoutRecycler: RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)//estilo de la lista/recycler
        this.recyclerView.layoutManager = layoutRecycler

    }


}