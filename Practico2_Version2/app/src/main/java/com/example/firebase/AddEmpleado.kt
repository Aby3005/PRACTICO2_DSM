package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.firebase.datos.Empleados
import com.example.firebase.Empleado.Companion.database

class AddEmpleado : AppCompatActivity() {
    private var edtSalario: EditText? = null
    private var edtNombre: EditText? = null
    private var key = ""
    private var nombre = ""
    private var salario = ""
    private var accion = ""
    private lateinit var  database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_empleado)
        inicializar()
    }

    private fun inicializar() {
        edtNombre = findViewById<EditText>(R.id.edtNombre)
        edtSalario = findViewById<EditText>(R.id.edtSalarioBase)

        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val edtSalario = findViewById<EditText>(R.id.edtSalarioBase)

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        if (datos != null) {
            key = datos.getString("key").toString()
        }
        if (datos != null) {
            edtNombre.setText(intent.getStringExtra("nombre").toString())
        }
        if (datos != null) {
            edtSalario.setText(intent.getStringExtra("salario").toString())
        }
        if (datos != null) {
            accion = datos.getString("accion").toString()
        }

    }

    fun guardar(v: View?) {
        val nombre: String = edtNombre?.text.toString()
        val salario: Double = edtSalario?.text.toString().toDouble()
       val descuento = salario*0.12
       val resultado = salario-descuento

        database= FirebaseDatabase.getInstance().getReference("empleados")

        // Se forma objeto persona
        val empleado = Empleados(nombre, salario, resultado)

        if (accion == "a") { //Agregar registro
            database.child(nombre).setValue(empleado).addOnSuccessListener {
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
            val empleadosValues = empleado.toMap()
            val childUpdates = hashMapOf<String, Any>(
                "$nombre" to empleadosValues
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