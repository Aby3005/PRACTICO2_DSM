package com.example.firebase

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.firebase.datos.Alumnos

class AdaptadorAlumno(private val context: Activity, var alumnos: List<Alumnos>) :
    ArrayAdapter<Alumnos?>(context, R.layout.activity_adaptador_alumno, alumnos){
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        val layoutInflater = context.layoutInflater
        var rowview: View? = null
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        rowview = view ?: layoutInflater.inflate(R.layout.activity_adaptador_alumno, null)
        val tvNombre = rowview!!.findViewById<TextView>(R.id.tvNombre)
        val tvNota1 = rowview.findViewById<TextView>(R.id.tvNota1)
        val tvNota2 = rowview.findViewById<TextView>(R.id.tvNota2)
        val tvNota3 = rowview.findViewById<TextView>(R.id.tvNota3)
        val tvNota4 = rowview.findViewById<TextView>(R.id.tvNota4)
        val tvNota5 = rowview.findViewById<TextView>(R.id.tvNota5)
        val resultado = rowview!!.findViewById<TextView>(R.id.txtResultado)
        val estado = rowview!!.findViewById<TextView>(R.id.txtEstado)

        tvNombre.text = "Nombre : " + alumnos[position].nombre
        tvNota1.text = "Nota1 : " + alumnos[position].nota1
        tvNota2.text = "Nota2 : " + alumnos[position].nota2
        tvNota3.text = "Nota3 : " + alumnos[position].nota3
        tvNota4.text = "Nota4 : " + alumnos[position].nota4
        tvNota5.text = "Nota5 : " + alumnos[position].nota5
        resultado.text = "Promedio : " + alumnos[position].resultado
        estado.text = "El estudiante ha : " + alumnos[position].estado
        return rowview
    }
}