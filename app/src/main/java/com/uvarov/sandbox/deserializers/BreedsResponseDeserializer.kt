package com.uvarov.sandbox.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.uvarov.sandbox.api.BreedsResponse
import com.uvarov.sandbox.model.Breed
import java.lang.reflect.Type

class BreedsResponseDeserializer : JsonDeserializer<BreedsResponse> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): BreedsResponse {
        val breedsResponse: BreedsResponse = BreedsResponse()
        val root = json.asJsonObject
        val message = root["message"].asJsonObject
        breedsResponse.breeds = message.entrySet().map { Breed(it.key) }
        breedsResponse.status = root["status"].asString
        return breedsResponse
    }
}