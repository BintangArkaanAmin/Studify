package com.example.studify.data

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("user")
	val user: List<UserItem?>? = null
)

data class UserItem(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
