package edu.`val`.cntgapp.perros

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Esta clase, encapsula la recogida de datos del API de perros (comunicación por HTTP)
 */
interface PerroService {

    //https://dog.ceo/api/breed/african/images
    @GET("api/breed/{raza}/images")
    suspend fun listarPerrosPorRaza(@Path("raza") raza:String):ListadoPerros
}