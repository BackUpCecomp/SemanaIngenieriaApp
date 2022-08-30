package com.example.aplicacionesmovilesb.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplicacionesmovilesb.R
import com.example.aplicacionesmovilesb.view.MainActivity

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}