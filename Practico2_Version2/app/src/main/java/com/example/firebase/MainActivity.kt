package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.firebase.Empleado
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.opcion1) {
            Toast.makeText(this, "Se seleccionó la primer opción", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Empleado::class.java)
            startActivity(intent)
        }

        if (id == R.id.opcion2) {
            Toast.makeText(this, "Se seleccionó la segunda opción", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Alumno::class.java)
            startActivity(intent)
        }

        when (item.itemId) {
           R.id.action_sign_out->{
               FirebaseAuth.getInstance().signOut().also {
                   Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show()

                   val intent = Intent(this, RegisterActivity::class.java)
                   startActivity(intent)
                   finish()
               }
           }
        }
        return super.onOptionsItemSelected(item)
    }

}