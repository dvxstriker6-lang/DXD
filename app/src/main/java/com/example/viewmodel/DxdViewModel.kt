package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SampleData
import com.example.model.ChatMessage
import com.example.model.Clan
import com.example.model.Conversation
import com.example.model.GameHub
import com.example.model.GamerProfile
import com.example.model.LeaderboardEntry
import com.example.model.NotificationItem
import com.example.model.Post
import com.example.model.Tournament
import com.example.model.VoiceRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
}

enum class MainTab(val title: String) {
    HOME("Home"),
    GAMING("Gaming"),
    CHAT("Chat"),
    NOTIFICATIONS("Alerts"),
    PROFILE("Profile")
}

class DxdViewModel : ViewModel() {

    // Current Navigation Screen State
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Splash)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    // Current Bottom Tab
    private val _currentTab = MutableStateFlow(MainTab.HOME)
    val currentTab: StateFlow<MainTab> = _currentTab.asStateFlow()

    // Auth State
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _currentUser = MutableStateFlow(SampleData.currentUser)
    val currentUser: StateFlow<GamerProfile> = _currentUser.asStateFlow()

    // Home Feed State
    private val _posts = MutableStateFlow(SampleData.initialPosts)
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    // Gaming Community State
    private val _gameHubs = MutableStateFlow(SampleData.gameHubs)
    val gameHubs: StateFlow<List<GameHub>> = _gameHubs.asStateFlow()

    private val _tournaments = MutableStateFlow(SampleData.tournaments)
    val tournaments: StateFlow<List<Tournament>> = _tournaments.asStateFlow()

    private val _clans = MutableStateFlow(SampleData.clans)
    val clans: StateFlow<List<Clan>> = _clans.asStateFlow()

    private val _leaderboard = MutableStateFlow(SampleData.leaderboard)
    val leaderboard: StateFlow<List<LeaderboardEntry>> = _leaderboard.asStateFlow()

    private val _voiceRooms = MutableStateFlow(SampleData.voiceRooms)
    val voiceRooms: StateFlow<List<VoiceRoom>> = _voiceRooms.asStateFlow()

    // Messenger State
    private val _conversations = MutableStateFlow(SampleData.conversations)
    val conversations: StateFlow<List<Conversation>> = _conversations.asStateFlow()

    private val _activeConversation = MutableStateFlow<Conversation?>(null)
    val activeConversation: StateFlow<Conversation?> = _activeConversation.asStateFlow()

    // Notifications State
    private val _notifications = MutableStateFlow(SampleData.notifications)
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    // Filter selections
    private val _communitySubTab = MutableStateFlow("HUBS") // HUBS, TOURNAMENTS, CLANS, LEADERBOARD
    val communitySubTab: StateFlow<String> = _communitySubTab.asStateFlow()

    private val _notifFilter = MutableStateFlow("ALL")
    val notifFilter: StateFlow<String> = _notifFilter.asStateFlow()

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun selectTab(tab: MainTab) {
        _currentTab.value = tab
    }

    fun setCommunitySubTab(subTab: String) {
        _communitySubTab.value = subTab
    }

    fun setNotifFilter(filter: String) {
        _notifFilter.value = filter
    }

    fun login(gamerTag: String) {
        val tag = if (gamerTag.isNotBlank()) gamerTag.trim() else "VORTEX_X"
        _currentUser.update {
            it.copy(
                gamerTag = tag,
                email = "${tag.lowercase()}@dxdgaming.gg"
            )
        }
        _isLoggedIn.value = true
        _currentScreen.value = Screen.Main
    }

    fun register(gamerTag: String, preferredGame: String) {
        val tag = if (gamerTag.isNotBlank()) gamerTag.trim() else "CYBER_ACE"
        _currentUser.update {
            it.copy(
                gamerTag = tag,
                email = "${tag.lowercase()}@dxdgaming.gg",
                favoriteGames = listOf(preferredGame, "Valorant")
            )
        }
        _isLoggedIn.value = true
        _currentScreen.value = Screen.Main
    }

    fun logout() {
        _isLoggedIn.value = false
        _currentScreen.value = Screen.Login
    }

    fun toggleLikePost(postId: String) {
        _posts.update { list ->
            list.map { post ->
                if (post.id == postId) {
                    val wasLiked = post.isLiked
                    post.copy(
                        isLiked = !wasLiked,
                        likesCount = if (wasLiked) post.likesCount - 1 else post.likesCount + 1
                    )
                } else post
            }
        }
    }

    fun createPost(content: String, game: String, badge: String? = null) {
        if (content.isBlank()) return
        val newPost = Post(
            id = "post_${System.currentTimeMillis()}",
            authorId = _currentUser.value.id,
            authorTag = _currentUser.value.gamerTag,
            authorClan = _currentUser.value.clanTag,
            authorLevel = _currentUser.value.level,
            isAuthorPro = true,
            game = if (game.isNotBlank()) game else "Valorant",
            content = content,
            highlightBadge = badge ?: "NEW HIGHLIGHT",
            matchStat = "Live Broadcast",
            likesCount = 1,
            commentsCount = 0,
            sharesCount = 0,
            isLiked = true,
            timestamp = "Just now"
        )
        _posts.update { listOf(newPost) + it }
    }

    fun toggleJoinHub(hubId: String) {
        _gameHubs.update { list ->
            list.map { hub ->
                if (hub.id == hubId) {
                    hub.copy(isJoined = !hub.isJoined)
                } else hub
            }
        }
    }

    fun registerForTournament(tourneyId: String) {
        _tournaments.update { list ->
            list.map { tourney ->
                if (tourney.id == tourneyId) {
                    val nowRegistered = !tourney.isRegistered
                    tourney.copy(
                        isRegistered = nowRegistered,
                        registeredTeams = if (nowRegistered) tourney.registeredTeams + 1 else tourney.registeredTeams - 1
                    )
                } else tourney
            }
        }
    }

    fun openConversation(conversation: Conversation) {
        _activeConversation.value = conversation
        // Mark conversation unread count as 0
        _conversations.update { list ->
            list.map { if (it.id == conversation.id) it.copy(unreadCount = 0) else it }
        }
    }

    fun closeActiveConversation() {
        _activeConversation.value = null
    }

    fun sendMessage(conversationId: String, text: String) {
        if (text.isBlank()) return
        val newMsg = ChatMessage(
            id = "m_${System.currentTimeMillis()}",
            senderId = _currentUser.value.id,
            senderTag = _currentUser.value.gamerTag,
            text = text.trim(),
            timestamp = "Just now",
            isFromMe = true
        )

        _conversations.update { list ->
            list.map { conv ->
                if (conv.id == conversationId) {
                    conv.copy(
                        lastMessage = "You: ${text.trim()}",
                        lastMessageTime = "Just now",
                        messages = conv.messages + newMsg
                    )
                } else conv
            }
        }

        // Also update active conversation if open
        _activeConversation.update { active ->
            if (active?.id == conversationId) {
                active.copy(
                    lastMessage = "You: ${text.trim()}",
                    lastMessageTime = "Just now",
                    messages = active.messages + newMsg
                )
            } else active
        }
    }

    fun markNotificationRead(notifId: String) {
        _notifications.update { list ->
            list.map { if (it.id == notifId) it.copy(isRead = true) else it }
        }
    }

    fun clearAllNotifications() {
        _notifications.value = emptyList()
    }
}
