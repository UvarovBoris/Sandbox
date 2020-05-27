package com.uvarov.sandbox.api

import com.uvarov.sandbox.model.BreedImage

class BreedImagesResponse(val breedImages: List<BreedImage>, status: String) : BaseResponse(status)