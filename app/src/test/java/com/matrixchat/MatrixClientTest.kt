package com.matrixchat

import com.matrixchat.data.MatrixClient
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

/**
 * Basic unit test for the Matrix client functionality.
 */
class MatrixClientTest {
    
    @Test
    fun `matrix client login with valid credentials should succeed`() = runBlocking {
        val client = MatrixClient("https://matrix.org")
        val result = client.login("testuser", "testpass")
        
        assertTrue("Login should succeed with valid credentials", result.isSuccess)
        
        val response = result.getOrNull()
        assertNotNull("Login response should not be null", response)
        assertEquals("User ID should be formatted correctly", "@testuser:matrix.org", response?.user_id)
        assertTrue("Client should be logged in", client.isLoggedIn())
    }
    
    @Test
    fun `matrix client login with empty credentials should fail`() = runBlocking {
        val client = MatrixClient("https://matrix.org")
        val result = client.login("", "")
        
        assertTrue("Login should fail with empty credentials", result.isFailure)
        assertFalse("Client should not be logged in", client.isLoggedIn())
    }
    
    @Test
    fun `matrix client should sync rooms when logged in`() = runBlocking {
        val client = MatrixClient("https://matrix.org")
        
        // First login
        val loginResult = client.login("testuser", "testpass")
        assertTrue("Login should succeed", loginResult.isSuccess)
        
        // Then sync rooms
        val syncResult = client.syncRooms()
        assertTrue("Room sync should succeed", syncResult.isSuccess)
        
        val rooms = syncResult.getOrNull()
        assertNotNull("Rooms list should not be null", rooms)
        assertTrue("Should have at least one room", rooms?.isNotEmpty() == true)
    }
    
    @Test
    fun `matrix client should send messages when logged in`() = runBlocking {
        val client = MatrixClient("https://matrix.org")
        
        // First login
        client.login("testuser", "testpass")
        
        // Then send message
        val result = client.sendMessage("!test:matrix.org", "Hello World")
        assertTrue("Message sending should succeed", result.isSuccess)
        
        val eventId = result.getOrNull()
        assertNotNull("Event ID should not be null", eventId)
        assertTrue("Event ID should start with mock prefix", eventId?.startsWith("mock_event_id_") == true)
    }
}