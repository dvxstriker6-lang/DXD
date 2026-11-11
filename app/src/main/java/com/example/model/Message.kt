package com.example.model

/**
 * Message in chat conversation
 */
data class ChatMessage(
    val id: String = "",
    val senderId: String = "",
    val senderTag: String = "",
    val text: String = "",
    val timestamp: String = "",
    val isFromMe: Boolean = false,
    val isGameInvite: Boolean = false,
    val inviteGame: String? = null,
    val inviteLobbyCode: String? = null
)

/**
 * Messenger conversation item
 */
data class Conversation(
    val id: String = "",
    val name: String = "",
    val gamerTag: String = "",
    val clanTag: String = "DXD",
    val lastMessage: String = "",
    val lastMessageTime: String = "",
    val unreadCount: Int = 0,
    val isGroup: Boolean = false,
    val isOnline: Boolean = true,
    val currentActivity: String? = "In Match: Valorant Ranked",
    val messages: List<ChatMessage> = emptyList()
)

/**
 * Active Voice / Squad room
 */
data class VoiceRoom(
    val id: String = "",
    val title: String = "",
    val game: String = "",
    val currentMembers: Int = 3,
    val maxMembers: Int = 5,
    val pingMs: Int = 18,
    val isLocked: Boolean = false
)
