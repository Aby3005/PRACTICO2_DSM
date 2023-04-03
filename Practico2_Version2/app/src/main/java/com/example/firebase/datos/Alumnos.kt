package com.example.firebase.datos

class Alumnos {
    fun key(key: String?) {
    }

    var nota1: Double? = null
    var nota2: Double? = null
    var nota3: Double? = null
    var nota4: Double? = null
    var nota5: Double? = null
    var nombre: String? = null
    var resultado: Double? = null
    var estado: String? = null
    var key: String? = null
    var per: MutableMap<String, Boolean> = HashMap()

    constructor() {}
    constructor( nombre: String?, nota1: Double?, nota2: Double?, nota3: Double?, nota4: Double?, nota5: Double?,resultado: Double?, estado: String?) {
        this.nombre = nombre
        this.nota1 = nota1
        this.nota2 = nota2
        this.nota3 = nota3
        this.nota4 = nota4
        this.nota5 = nota5
        this.resultado = resultado
        this.estado = estado
    }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nombre" to nombre,
            "nota1" to nota1,
            "nota2" to nota2,
            "nota3" to nota3,
            "nota4" to nota4,
            "nota5" to nota5,
            "resultado" to resultado,
            "estado" to estado,
            "key" to key,
            "per" to per
        )
    }
}