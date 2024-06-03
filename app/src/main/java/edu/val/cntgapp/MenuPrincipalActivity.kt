package edu.`val`.cntgapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import edu.`val`.cntgapp.util.Constantes

class MenuPrincipalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    var menuVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        this.drawerLayout = findViewById<DrawerLayout>(R.id.drawerView)
        this.navigationView =  findViewById<NavigationView>(R.id.navigationView)

        //para dibujar la hamburguesa/ el icono del menú
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//dibujar el icono del menu
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)

        this.navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //se invoca al tocar la hamburguesa
        when (item.itemId)
        {
            android.R.id.home ->{
                Log.d(Constantes.ETIQUETA_LOG, "Botón Hamburguesa tocado")
                if (this.menuVisible)
                {
                    this.drawerLayout.closeDrawers()
                } else {
                    this.drawerLayout.openDrawer(GravityCompat.START) //el menú se dibuja de izq a derecha
                }
                this.menuVisible = !this.menuVisible
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        Log.d(Constantes.ETIQUETA_LOG, "Opcion ${p0.order} tocada")
        //TODO definir los intents según la opción, lanzando la actividad correspondiente
        this.drawerLayout.closeDrawers()//cierro el menú sea cual sea la opcion tocada
        return true
    }


}