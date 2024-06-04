package edu.`val`.cntgapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import edu.`val`.cntgapp.adivina.AdivinaActivity
import edu.`val`.cntgapp.creditos.CreditosActivity
import edu.`val`.cntgapp.fechayhora.SeleccionFechaYHoraActivity
import edu.`val`.cntgapp.imc.IMCActivity
import edu.`val`.cntgapp.intents.BusquedaActivity
import edu.`val`.cntgapp.perros.PerrosActivity
import edu.`val`.cntgapp.productos.ProductosActivity
import edu.`val`.cntgapp.util.Constantes
import edu.`val`.cntgapp.versiones.VersionesActivity
import edu.`val`.cntgapp.webview.WebActivity

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

    override fun onNavigationItemSelected(opcionMenu: MenuItem): Boolean {
        Log.d(Constantes.ETIQUETA_LOG, "Opcion ${opcionMenu.order} tocada")
        //TODO definir los intents según la opción, lanzando la actividad correspondiente

        var objetoClass: Class<*>? = null
        when (opcionMenu.order)
        {
            0 -> {objetoClass = VersionesActivity::class.java }
            1 -> {
                objetoClass = WebActivity::class.java
                //val uri = Uri.parse("https://cntg.xunta.gal/")//paso de string a uri
                //val intentImplicito = Intent(Intent.ACTION_VIEW, uri )//QUIERO VER, UNA WEB
                //startActivity(intentImplicito)
                //var intentweb = Intent(Intent.ACTION_VIEW, )
            }
            2 -> {objetoClass = CreditosActivity::class.java }
            3 -> {objetoClass = IMCActivity::class.java }
            4 -> {objetoClass = BusquedaActivity::class.java }
            5 -> {objetoClass = SeleccionFechaYHoraActivity::class.java }
            6 -> {objetoClass = AdivinaActivity::class.java }
            7 -> {objetoClass = PerrosActivity::class.java }
            8 -> {objetoClass = ProductosActivity::class.java }

        }
        var intent = Intent(this,objetoClass )
        startActivity(intent)
        this.drawerLayout.closeDrawers()//cierro el menú sea cual sea la opcion tocada
        return true
    }


}