package mx.tecnm.tepic.u5p1_mapeo_parquejuanescutia_equipoalitasexpress

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import mx.tecnm.tepic.u5p1_mapeo_parquejuanescutia_equipoalitasexpress.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var binding: ActivityMainBinding
    var base = FirebaseFirestore.getInstance()
    var posicion = ArrayList<Data>()
    var lista = ArrayList<String>()
    lateinit var locacion : LocationManager
    var c1 : Location = Location("")
    var c2 : Location = Location("")
    var siPermiso = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.EQUIPO.setOnClickListener {
            AlertDialog.Builder(this).setTitle("INTEGRANTES: ")
                .setMessage("Pablo Nicolas Tello Ortega\n" +
                        "Juan Mario Gonzales Borrayo\n"+
                        "Adalberto Martinez Rodriguez")
                .setPositiveButton("OK") { d, i ->
                }.show()
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            &&ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            &&ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            &&ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE),siPermiso)

        }else{
            ActivityCompat.requestPermissions(this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE),siPermiso)
        }

        base.collection("parque")
            .addSnapshotListener { querySnapshot, error ->
                if(error != null){
                    Toast.makeText(this, "ERROR: ${error.message}", Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                posicion.clear()
                for(document in querySnapshot!!){
                    val data = Data()
                    data.nombre = document.getString("nombre").toString()
                    data.pos1 = document.getGeoPoint("pos1")!!
                    data.pos2 = document.getGeoPoint("pos2")!!

                    lista.add(data.toString())
                    posicion.add(data)
                }

                binding.lista.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista)

            }

        binding.lista.setOnItemClickListener { adapterView, view, i, l ->
                var id = lista.get(i)
                var nombre = id.split("\n")
                base.collection("parque").document(nombre[0]).addSnapshotListener { value, error ->
                    if(value!!.getString("nombre")!=null){
                        val extra = Intent(this,MainActivity2::class.java).putExtra("nombre",value.getString("nombre"))
                        startActivity(extra)
                    }
                }

        }


    }



    override fun onMapReady(p0: GoogleMap) {
        var mMap = p0

        // Add a marker in plaza and move the camera
        val parque = LatLng(21.511692, -104.900339)
        val AJ = LatLng(21.513944, -104.899139)
        val TE = LatLng(21.513063, -104.899978)
        val MNH = LatLng(21.512815, -104.899837)
        val FU = LatLng(21.511816, -104.900274)
        val TP = LatLng(21.514304, -104.898938)
        mMap.addMarker(MarkerOptions()
            .position(parque)
            .title("Parque"))
        mMap.addMarker(MarkerOptions()
            .position(TP)
            .title("Teatro Pueblo"))
        mMap.addMarker(MarkerOptions()
            .position(FU)
            .title("Fuente"))
        mMap.addMarker(MarkerOptions()
            .position(MNH)
            .title("Monumento niños heroes"))
        mMap.addMarker(MarkerOptions()
            .position(TE)
            .title("Tianguis"))
        mMap.addMarker(MarkerOptions()
            .position(AJ)
            .title("Areas de juegos"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(parque))
        mMap.uiSettings.isZoomControlsEnabled = true
    }




}
