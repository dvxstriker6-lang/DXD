package com.example.model

enum class NotificationType {
    TOURNAMENT,
    CLAN_INVITE,
    MATCH_READY,
    MENTION,
    REWARD,
    FRIEND_REQUEST
}

data class NotificationItem(
    val id: String = "",
    val type: NotificationType = NotificationType.TOURNAMENT,
    val title: String = "",
    val description: String = "",
    val timeAgo: String = "5m ago",
    val isRead: Boolean = false,
    val actionLabel: String? = null,
    val hasActionButtons: Boolean = false
)
