package com.roerdev.pruebaapiionix.consumers;

import com.roerdev.pruebaapiionix.dto.ConsultaRutDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IonixAPI {

    @GET("test-tecnico/search")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Call<ConsultaRutDto> consultaRut(@Query("rut") String ruc);
}
