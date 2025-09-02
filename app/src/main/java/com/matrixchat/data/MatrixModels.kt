package com.matrixchat.data

data class LoginRequest(
    val identifier: LoginIdentifier,
    val password: String,
    val type: String = "m.login.password",
    val device_id: String? = null,
    val initial_device_display_name: String? = null
)

data class LoginIdentifier(
    val type: String = "m.id.user",
    val user: String
)

data class LoginResponse(
    val user_id: String,
    val access_token: String,
    val home_server: String,
    val device_id: String,
    val well_known: WellKnown? = null
)

data class WellKnown(
    val homeserver: Map<String, String>
)

data class MatrixError(
    val errcode: String,
    val error: String
)