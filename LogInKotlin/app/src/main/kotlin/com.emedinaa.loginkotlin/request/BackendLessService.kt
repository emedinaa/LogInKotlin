package com.emedinaa.loginkotlin.request

import com.emedinaa.loginkotlin.request.entity.LogInRaw
import com.emedinaa.loginkotlin.request.entity.LogInResponse
import retrofit.Callback
import retrofit.http.Body
import retrofit.http.Headers
import retrofit.http.POST

/**
 * Created by eduardomedina on 13/09/16.
 */
interface BackendLessService {

    @Headers(
        "Content-Type: application/json",
        "application-id: 263C86F0-8FDA-9E91-FF97-C825AEAB0B00",
        "secret-key: FBD7D8FE-B077-08C6-FF8A-A017E0F18400",
        "application-type: REST")

    @POST("/v1/users/login")
    fun logIn(@Body raw:LogInRaw, callback:Callback<LogInResponse>)
}