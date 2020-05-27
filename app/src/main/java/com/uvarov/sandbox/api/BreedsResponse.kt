package com.uvarov.sandbox.api

import com.uvarov.sandbox.model.Breed

class BreedsResponse(var breeds: List<Breed>, status: String) : BaseResponse(status)