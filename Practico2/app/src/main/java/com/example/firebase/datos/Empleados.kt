package com.example.firebase.datos

class Empleados {
    fun key(key: String?) {
    }

    var salario: Double? = null
    var nombre: String? = null
    var resultado: Double? = null
    var key: String? = null
    var per: MutableMap<String, Boolean> = HashMap()

    constructor() {}
    constructor( nombre: String?, salario: Double?,resultado: Double?) {
        this.nombre = nombre
        this.salario = salario
        this.resultado = resultado
    }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nombre" to nombre,
            "salario" to salario,
            "resultado" to resultado,
            "key" to key,
            "per" to per
        )
    }
}