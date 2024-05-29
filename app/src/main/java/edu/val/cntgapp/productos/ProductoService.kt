package edu.`val`.cntgapp.productos

import retrofit2.http.GET

/**
 * Esta clase, encapsula la comuniicaicón HTTP para recibir el listado de productos
 *
 */
interface ProductoService {

    @GET("miseon920/json-api/products")
    suspend fun obtenerProductos():ListadoProductos
}