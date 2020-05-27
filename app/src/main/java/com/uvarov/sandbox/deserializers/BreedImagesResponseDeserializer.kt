package com.uvarov.sandbox.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.uvarov.sandbox.api.BreedImagesResponse
import com.uvarov.sandbox.model.BreedImage
import java.lang.reflect.Type

class BreedImagesResponseDeserializer : JsonDeserializer<BreedImagesResponse> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): BreedImagesResponse {
        val root = json.asJsonObject
        val message = root["message"].asJsonArray
        val images = message.map { BreedImage(it.asString) }
        val status = root["status"].asString
        return BreedImagesResponse(images, status)
    }
}