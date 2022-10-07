package com.jjmf.coffee.Core



sealed class EstadosResult<out T>{

    //LOADING
    object Cargando : EstadosResult<Nothing>()

    //COMPLETE
    data class Correcto<T> (val datos:T? ) : EstadosResult<T>()

    //FAILURE
    data class Error(val mensajeError :String, val codigoError : Int? = null) : EstadosResult<Nothing>()
}