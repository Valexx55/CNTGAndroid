package edu.`val`.cntgapp.mapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.databinding.ActivityMapsBinding
import edu.`val`.cntgapp.util.Constantes

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //Torre de Hércules 43.3809683,-8.4383013,13.27
        val torreHercules = LatLng(43.3809683, -8.4383013)
        mMap.addMarker(MarkerOptions().position(torreHercules).title("La Torre de Hércules"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(torreHercules, 12f)) //a más zoom, más cerca se ve
        //ap.z
    }

    fun mostrarUbicacionMapa(view: View) {
        Log.d(Constantes.ETIQUETA_LOG, "mostrar ubicación mapa...")
    }
}