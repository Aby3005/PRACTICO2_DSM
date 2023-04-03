package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.firebase.datos.Alumnos
import com.example.firebase.Alumno.Companion.database

class AddAlumno : AppCompatActivity() {
    private var edtNota1: EditText? = null
    private var edtNota2: EditText? = null
    private var edtNota3: EditText? = null
    private var edtNota4: EditText? = null
    private var edtNota5: EditText? = null
    private var edtNombre: EditText? = null
    private var key = ""
    private var nombre = ""
    private var nota1 = ""
    private var nota2 = ""
    private var nota3 = ""
    private var nota4 = ""
    private var nota5 = ""
    private var accion = ""
    private lateinit var  database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_alumno)
        inicializar()
    }

    private fun inicializar() {
        edtNombre = findViewById<EditText>(R.id.edtNombre)
        edtNota1 = findViewById<EditText>(R.id.edtNota1)
        edtNota2 = findViewById<EditText>(R.id.edtNota2)
        edtNota3 = findViewById<EditText>(R.id.edtNota3)
        edtNota4 = findViewById<EditText>(R.id.edtNota4)
        edtNota5 = findViewById<EditText>(R.id.edtNota5)

        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val edtNota1 = findViewById<EditText>(R.id.edtNota1)
        val edtNota2 = findViewById<EditText>(R.id.edtNota2)
        val edtNota3 = findViewById<EditText>(R.id.edtNota3)
        val edtNota4 = findViewById<EditText>(R.id.edtNota4)
        val edtNota5 = findViewById<EditText>(R.id.edtNota5)

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        if (datos != null) {
            key = datos.getString("key").toString()
        }
        if (datos != null) {
            edtNombre.setText(intent.getStringExtra("nombre").toString())
        }
        if (datos != null) {
            edtNota1.setText(intent.getStringExtra("nota1").toString())
        }
        if (datos != null) {
            edtNota2.setText(intent.getStringExtra("nota2").toString())
        }
        if (datos != null) {
            edtNota3.setText(intent.getStringExtra("nota3").toString())
        }
        if (datos != null) {
            edtNota4.setText(intent.getStringExtra("nota4").toString())
        }
        if (datos != null) {
            edtNota5.setText(intent.getStringExtra("nota5").toString())
        }
        if (datos != null) {
            accion = datos.getString("accion").toString()
        }

    }

    fun guardar(v: View?) {
        val nombre: String = edtNombre?.text.toString()
        val nota1: Double = edtNota1?.text.toString().toDouble()
        val nota2: Double = edtNota2?.text.toString().toDouble()
        val nota3: Double = edtNota3?.text.toString().toDouble()
        val nota4: Double = edtNota4?.text.toString().toDouble()
        val nota5: Double = edtNota5?.text.toString().toDouble()

        val resultado = (nota1+nota2+nota3+nota4+nota5)/5
        val estado = if (resultado >=6) "Aprobado" else "Reprobado"

        database= FirebaseDatabase.getInstance().getReference("alumnos")

        // Se forma objeto persona
        val alumno = Alumnos(nombre, nota1, nota2, nota3, nota4, nota5, resultado, estado)

        if (accion == "a") { //Agregar registro
            database.child(nombre).setValue(alumno).addOnSuccessListener {
                Toast.makeText(this,"Se guardo con exito", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed ", Toast.LENGTH_SHORT).show()
            }
        } else  // Editar registro
        {
            val key = database.child("nombre").push().key
            if (key == null) {
                Toast.makeText(this,"Llave vacia", Toast.LENGTH_SHORT).show()
            }
            val alumnosValues = alumno.toMap()
            val childUpdates = hashMapOf<String, Any>(
                "$nombre" to alumnosValues
            )
            database.updateChildren(childUpdates)
            Toast.makeText(this,"Se actualizo con exito", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    fun cancelar(v: View?) {
        finish()
    }
}