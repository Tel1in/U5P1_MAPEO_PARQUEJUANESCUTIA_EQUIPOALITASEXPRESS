package mx.tecnm.tepic.u5p1_mapeo_parquejuanescutia_equipoalitasexpress

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import mx.tecnm.tepic.u5p1_mapeo_parquejuanescutia_equipoalitasexpress.databinding.ActivityMain2Binding
import java.io.File

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    var baseRemota = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var nombre = intent.extras!!.getString("nombre").toString()
        val localfile = File.createTempFile("tempImage","png")

        // if teatro
        if(nombre == "teatro"){

            //imagen 1
            var storeRef  = FirebaseStorage.getInstance().reference.child(nombre + "/teatro1.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                    binding.imagen1.setImageBitmap(bitmap)
            }

            //Imagen 2
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/teatro2.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen2.setImageBitmap(bitmap)
            }

            //imagen 3
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/teatro3.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen3.setImageBitmap(bitmap)
            }
        }//if teatro

        //IF juegos
        if (nombre == "juegos"){
            //imagen 1
            var storeRef  = FirebaseStorage.getInstance().reference.child(nombre + "/juegos1.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen1.setImageBitmap(bitmap)
            }

            //Imagen 2
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/juegos2.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen2.setImageBitmap(bitmap)
            }

            //imagen 3
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/juegos3.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen3.setImageBitmap(bitmap)
            }
        } // if juegos

        //if tianguis
        if(nombre == "tianguis"){
            //imagen 1
            var storeRef  = FirebaseStorage.getInstance().reference.child(nombre + "/tianguis1.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen1.setImageBitmap(bitmap)
            }

            //Imagen 2
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/tianguis2.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen2.setImageBitmap(bitmap)
            }

            //imagen 3
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/tianguis1.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen3.setImageBitmap(bitmap)
            }
        }//if tianguis

        // fuente
        if(nombre == "fuente"){
            //imagen 1
            var storeRef  = FirebaseStorage.getInstance().reference.child(nombre + "/fuente1.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen1.setImageBitmap(bitmap)
            }

            //Imagen 2
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/fuent22.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen2.setImageBitmap(bitmap)
            }

            //imagen 3
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/fuente3.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen3.setImageBitmap(bitmap)
            }
        }//if FUENTE

        // monumentos ninos heroes
        if(nombre == "monumento niños heroes"){
            //imagen 1
            var storeRef  = FirebaseStorage.getInstance().reference.child(nombre + "/monumento1.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen1.setImageBitmap(bitmap)
            }

            //Imagen 2
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/monumento2.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen2.setImageBitmap(bitmap)
            }

            //imagen 3
            storeRef = FirebaseStorage.getInstance().reference.child(nombre + "/monumento3.png")
            storeRef.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imagen3.setImageBitmap(bitmap)
            }
        }//if MONUMENTO NIÑOS HEROES

        binding.txtDescripcion.setText(nombre)
    }//onCreate
}//class