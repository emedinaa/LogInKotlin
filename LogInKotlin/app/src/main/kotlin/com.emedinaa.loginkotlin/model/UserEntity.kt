package com.emedinaa.loginkotlin.model

import java.io.Serializable

/**
 * Created by eduardomedina on 13/09/16.
 */
class UserEntity:Serializable {

    var name:String?=null
    var email:String?=null
    var token: String? = null
    var objectId: String? = null
}