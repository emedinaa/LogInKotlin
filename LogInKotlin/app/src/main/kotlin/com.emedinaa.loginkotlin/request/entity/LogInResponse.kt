package com.emedinaa.loginkotlin.request.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by eduardomedina on 13/09/16.
 */
class LogInResponse: BaseResponse() {

    var name:String=""

    @SerializedName("___class")
    var type: String? = null

    @SerializedName("user-token")
    var token: String? = null

    var email: String? = null

    var objectId: String? = null

}