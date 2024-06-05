package edu.`val`.cntgapp.mapa

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.`val`.cntgapp.R
import edu.`val`.cntgapp.databinding.ActivityMapsBinding
import edu.`val`.cntgapp.util.Constantes
import java.util.Locale

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    lateinit var locationManager: LocationManager //para ver si tengo activo o no el GPS -servicio del SISTEMA-
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient //esta me va a dar la ubicación
    lateinit var locationRequest: LocationRequest //petición de ubicación
    lateinit var locationCallback: LocationCallback //respuesta a petición de ubicación


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        this.locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
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
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                torreHercules,
                12f
            )
        ) //a más zoom, más cerca se ve
        //ap.z
    }

    fun mostrarUbicacionMapa(view: View) {
        Log.d(Constantes.ETIQUETA_LOG, "mostrar ubicación mapa...")
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 535)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(
                Constantes.ETIQUETA_LOG,
                "EL usuario nos ha concedido los permisos para acceder a su ubicación"
            )
            if (gpsEstaActivado()) {
                accederALaUbicacionGPS()
            } else {
                solicitarActivacionGPS()
            }
        } else {
            Log.d(
                Constantes.ETIQUETA_LOG,
                "EL usuario NO nos ha concedido los permisos para acceder a su ubicación"
            )

            Toast.makeText(this, "SIN PERMISO PARA ACCEDER A SU UBICACIÓN", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun solicitarActivacionGPS() {
        //con esto vamos a ajuster, para pedir que el usuario active el GPS
        val intentPedirGps = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivityForResult(intentPedirGps, 55)
    }

    //esta función, será invocada a la vuelta de los ajustes de ubicación
    //y traerá el resultado con la decisión del usuario
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (gpsEstaActivado()) {
            Log.d(Constantes.ETIQUETA_LOG, "EL usuario ha activado la ubicación GPS")
            accederALaUbicacionGPS()
        } else {
            Log.d(Constantes.ETIQUETA_LOG, "EL usuario NO ha activado la ubicación GPS")
            Toast.makeText(this, "GPS DESACTIVADO - SIN ACCESO A SU UBICACIÓN", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun accederALaUbicacionGPS() {
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //creamos la petición
        this.locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)//alta precición GPS
            .setInterval(5000)//cada 5 segundos actualice

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(resultadoUbicacion: LocationResult) {
                //super.onLocationResult(p0)
                if (resultadoUbicacion != null) {
                    Log.d(
                        Constantes.ETIQUETA_LOG,
                        "Ubicacion obtenida ${resultadoUbicacion.lastLocation}"

                    )
                    this@MapsActivity.mostrarUbicacion(resultadoUbicacion.lastLocation)
                    this@MapsActivity.fusedLocationProviderClient.removeLocationUpdates(
                        locationCallback
                    )
                }
            }
        }
        //accedo al GPS
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            this.fusedLocationProviderClient.requestLocationUpdates(
                this.locationRequest,
                this.locationCallback,
                null
            )
        }

    }

    fun mostrarDireccionPostal (ubicacion:Location)
    {
        val geocoder =  Geocoder(this, Locale("es"))//el objeto que permite pasar de dirección Física a POSTAR

        val direcciones = geocoder.getFromLocation(ubicacion.latitude, ubicacion.longitude, 1)

        if (direcciones!=null && direcciones.size>0)
        {
            val direccion = direcciones[0]
            Log.d(Constantes.ETIQUETA_LOG, "Dirección obtenida = ${direccion.getAddressLine(0)}  CP ${direccion.postalCode} LOCALIDAD ${direccion.locality} ")
        }
    }
    private fun mostrarUbicacion(lastLocation: Location) {

        val ubicacion_Actual = LatLng(lastLocation.latitude, lastLocation.longitude)
        this.mMap.addMarker(MarkerOptions().position(ubicacion_Actual).title("ESTOY AQUÍ"))
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion_Actual))
        mostrarDireccionPostal(lastLocation)

    }

    private fun gpsEstaActivado(): Boolean {
        var activadoGps: Boolean = false

        activadoGps = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        return activadoGps
    }
}