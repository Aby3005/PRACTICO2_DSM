package com.example.firebase

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.firebase.datos.Empleados


class AdaptadorEmpleado(private val context: Activity, var empleados: List<Empleados>) :
    ArrayAdapter<Empleados?>(context, R.layout.activity_adaptador_empleado, empleados){
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        val layoutInflater = context.layoutInflater
        var rowview: View? = null
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        rowview = view ?: layoutInflater.inflate(R.layout.activity_adaptador_empleado, null)
        val tvNombre = rowview!!.findViewById<TextView>(R.id.tvNombre)
        val tvSalario = rowview.findViewById<TextView>(R.id.tvSalario)
        val resultado = rowview!!.findViewById<TextView>(R.id.txtResultado)
        tvNombre.text = "Nombre : " + empleados[position].nombre
        tvSalario.text = "Salario : " + empleados[position].salario
        resultado.text = "Resultado : " + empleados[position].resultado
        return rowview
    }
}