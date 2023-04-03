package com.example.firebase

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.example.firebase.datos.Empleados


class Empleado : AppCompatActivity() {
    // Ordenamiento para hacer la consultas a los datos
    var consultaOrdenada: Query = refEmpleados.orderByChild("nombre")
    var empleados: MutableList<Empleados>? = null
    var listaEmpleados: ListView? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleado)
        inicializar()
    }

    private fun inicializar() {
        val fab_agregar: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab_agregar)
        listaEmpleados = findViewById<ListView>(R.id.ListaEmpleados)

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaEmpleados!!.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val intent = Intent(getBaseContext(), AddEmpleado::class.java)
                intent.putExtra("accion", "e") // Editar
                intent.putExtra("key", empleados!![i].key)
                intent.putExtra("nombre", empleados!![i].nombre)
                intent.putExtra("salario", empleados!![i].salario)
                startActivity(intent)
            }
        })

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaEmpleados!!.onItemLongClickListener = object : AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long
            ): Boolean {
                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                val ad = AlertDialog.Builder(this@Empleado)
                ad.setMessage("Está seguro de eliminar registro?")
                    .setTitle("Confirmación")
                ad.setPositiveButton("Si"
                ) { dialog, id ->
                    empleados!![position].nombre?.let {
                        refEmpleados.child(it).removeValue()
                    }
                    Toast.makeText(
                        this@Empleado,
                        "Registro borrado!", Toast.LENGTH_SHORT
                    ).show()
                }
                ad.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        Toast.makeText(
                            this@Empleado,
                            "Operación de borrado cancelada!", Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                ad.show()
                return true
            }
        }
        fab_agregar.setOnClickListener(View.OnClickListener { // Cuando el usuario quiere agregar un nuevo registro
            val i = Intent(getBaseContext(), AddEmpleado::class.java)
            i.putExtra("accion", "a") // Agregar
            i.putExtra("key", "")
            i.putExtra("nombre", "")
            i.putExtra("salario", "")
            startActivity(i)
        })
        empleados = ArrayList<Empleados>()

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                empleados!!.removeAll(empleados!!)
                for (dato in dataSnapshot.getChildren()) {
                    val empleado: Empleados? = dato.getValue(Empleados::class.java)
                    empleado?.key(dato.key)
                    if (empleado != null) {
                        empleados!!.add(empleado)
                    }
                }
                val adapter = AdaptadorEmpleado(
                    this@Empleado,
                    empleados as ArrayList<Empleados>
                )
                listaEmpleados!!.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    companion object {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var refEmpleados: DatabaseReference = database.getReference("empleados")
    }
}