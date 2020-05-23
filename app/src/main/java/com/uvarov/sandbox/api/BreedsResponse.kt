package com.uvarov.sandbox.api

import com.google.gson.annotations.SerializedName

class BreedsResponse() : BaseResponse() {

    @SerializedName("message")
    lateinit var breeds: Map<String, List<String>>
}