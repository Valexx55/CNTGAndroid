package edu.`val`.cntgapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MenuPrincipalActivity : AppCompatActivity() {

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



    }
}