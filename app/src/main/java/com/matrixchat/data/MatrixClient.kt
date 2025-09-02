package com.matrixchat.data

import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Basic Matrix API client for demonstration purposes.
 * This is a simplified implementation to show the structure.
 * In a real app, you'd use the official Matrix Android SDK.
 */
class MatrixClient(
    private val homeserverUrl: String
) {
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    
    private var accessToken: String? = null
    private var userId: String? = null
    
    /**
     * Simulate Matrix login for demo purposes.
     * In a real implementation, this would make HTTP requests to the Matrix API.
     */
    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            // Simulate network delay
            delay(2000)
            
            // Mock successful login for demo
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val response = LoginResponse(
                    user_id = "@$username:${extractDomain(homeserverUrl)}",
                    access_token = "mock_access_token_${System.currentTimeMillis()}",
                    home_server = extractDomain(homeserverUrl),
                    device_id = "MOCK_DEVICE_${System.currentTimeMillis()}"
                )
                
                accessToken = response.access_token
                userId = response.user_id
                
                Result.success(response)
            } else {
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Mock room sync for demo purposes.
     * In a real implementation, this would sync with Matrix rooms.
     */
    suspend fun syncRooms(): Result<List<String>> {
        return try {
            delay(1000)
            // Return mock room list
            Result.success(listOf("!general:matrix.org", "!random:matrix.org"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Mock message sending for demo purposes.
     */
    suspend fun sendMessage(roomId: String, message: String): Result<String> {
        return try {
            delay(500)
            // Mock successful message send
            Result.success("mock_event_id_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun isLoggedIn(): Boolean = accessToken != null
    
    fun getCurrentUserId(): String? = userId
    
    fun logout() {
        accessToken = null
        userId = null
    }
    
    private fun extractDomain(url: String): String {
        return url.removePrefix("https://").removePrefix("http://").split("/")[0]
    }
}