package com.uvarov.sandbox.api

import com.uvarov.sandbox.model.Breed

class BreedsResponse : BaseResponse() {

    lateinit var breeds: List<Breed>
}