package com.emedinaa.loginkotlin.request.entity

/**
 * Created by eduardomedina on 13/09/16.
 */
open class BaseResponse {

    val SUCCESS = 0
    val code: Int = 0
    val message: String? = null


    public fun isSuccess():Boolean{
        if(code==SUCCESS)return true
        return false
    }
}