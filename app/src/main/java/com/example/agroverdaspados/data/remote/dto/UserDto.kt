package com.example.agroverdaspados.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO = Data Transfer Object
 * Este objeto representa los datos que VIAJAN entre tu app y el servidor
 */

data class UserDto(


    @SerializedName("_id")
    val id: String,
    val email: String,
    val role : String,
    val isActive:Boolean,
    val telefono:String?,
    val direccion:String?,
    // val certificaciones:List<String>?,
    val isVerified:Boolean?,
    val profileId: String,
    val profileCreatedAt: String,
    val profileUpdatedAt: String,
    val nombre:String,
    val preferencias: List<String>
)