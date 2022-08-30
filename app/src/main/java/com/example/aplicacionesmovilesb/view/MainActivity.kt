package com.example.aplicacionesmovilesb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aplicacionesmovilesb.Model.Expositor
import com.example.aplicacionesmovilesb.R
import com.example.aplicacionesmovilesb.databinding.ActivityMainBinding
import com.example.aplicacionesmovilesb.view.ui.fragments.HomeFragment
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var i = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController : NavController = findNavController(R.id.fragment_contenido);
        binding.bnvMenu.setupWithNavController(navController)
        //uploadExpositores(i)
        //i += 1
        //setContentView(R.layout.fragment_ubicacion)
    }

    fun uploadExpositores(i: Int){
        if(i==0){
            val jsonArr = JSONArray( application.assets.open("expositores.json").bufferedReader().use{
                it.readText()
            })
            FirebaseFirestore.setLoggingEnabled(true)
            val firebaseFirestore = FirebaseFirestore.getInstance()

            for(i in 0 until jsonArr.length()){
                val aux = jsonArr.get(i) as JSONObject
                val expositor = Expositor()
                expositor.name = aux.getString("nombre")
                expositor.job = aux.getString("profesion")
                expositor.workplace = aux.getString("lugartrabajo")
                expositor.biography = aux.getString("biografia")
                expositor.twitter = aux.getString("twitter")
                expositor.img = aux.getString("imagen")
                expositor.category = aux.getInt("categoria")

                firebaseFirestore.collection("expositores").document().set(expositor)
            }
        }
    }
}