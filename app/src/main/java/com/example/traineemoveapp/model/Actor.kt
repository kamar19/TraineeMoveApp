package com.example.traineemoveapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Actor (var id: Int? = null,
                  var name: String = "",
                  var profile_path:String = "")