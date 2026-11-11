package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DxdBrightRed
import com.example.ui.theme.DxdDarkObsidian
import com.example.ui.theme.DxdNeonRed
import com.example.ui.theme.DxdPureBlack
import com.example.ui.theme.DxdRedGlowBorder
import com.example.ui.theme.DxdTextMuted
import com.example.ui.theme.DxdTextSecondary
import com.example.ui.theme.OrbitronFontFamily
import com.example.ui.theme.RajdhaniFontFamily
import com.example.viewmodel.DxdViewModel
import com.example.viewmodel.MainTab

@Composable
fun MainScreen(
    viewModel: DxdViewModel
) {
    val currentTab by viewModel.currentTab.collectAsState()
    val currentUser by viewModel.currentUser.collectAsState()
    val posts by viewModel.posts.collectAsState()
    val gameHubs by viewModel.gameHubs.collectAsState()
    val tournaments by viewModel.tournaments.collectAsState()
    val clans by viewModel.clans.collectAsState()
    val leaderboard by viewModel.leaderboard.collectAsState()
    val voiceRooms by viewModel.voiceRooms.collectAsState()
    val conversations by viewModel.conversations.collectAsState()
    val activeConversation by viewModel.activeConversation.collectAsState()
    val notifications by viewModel.notifications.collectAsState()
    val communitySubTab by viewModel.communitySubTab.collectAsState()
    val notifFilter by viewModel.notifFilter.collectAsState()

    // BackHandler: if active conversation is open, closing it is handled inside MessengerScreen.
    // If on a secondary tab, back button returns to Home!
    if (activeConversation == null && currentTab != MainTab.HOME) {
        BackHandler {
            viewModel.selectTab(MainTab.HOME)
        }
    }

    val totalUnreadMessages = conversations.sumOf { it.unreadCount }
    val totalUnreadAlerts = notifications.count { !it.isRead }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("main_screen"),
        containerColor = DxdPureBlack,
        bottomBar = {
            if (activeConversation == null) {
                DxdBottomNavigationBar(
                    currentTab = currentTab,
                    onTabSelected = { viewModel.selectTab(it) },
                    chatBadgeCount = totalUnreadMessages,
                    alertsBadgeCount = totalUnreadAlerts
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentTab,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "screenTransition"
            ) { tab ->
                when (tab) {
                    MainTab.HOME -> {
                        HomeScreen(
                            currentUser = currentUser,
                            posts = posts,
                            onLikePost = { viewModel.toggleLikePost(it) },
                            onCreatePost = { content, game, badge ->
                                viewModel.createPost(content, game, badge)
                            },
                            onNavigateToTournament = {
                                viewModel.selectTab(MainTab.GAMING)
                                viewModel.setCommunitySubTab("TOURNAMENTS")
                            },
                            onNavigateToNotifications = {
                                viewModel.selectTab(MainTab.NOTIFICATIONS)
                            }
                        )
                    }

                    MainTab.GAMING -> {
                        GamingScreen(
                            currentSubTab = communitySubTab,
                            onSubTabSelected = { viewModel.setCommunitySubTab(it) },
                            gameHubs = gameHubs,
                            tournaments = tournaments,
                            clans = clans,
                            leaderboard = leaderboard,
                            onToggleJoinHub = { viewModel.toggleJoinHub(it) },
                            onRegisterTournament = { viewModel.registerForTournament(it) }
                        )
                    }

                    MainTab.CHAT -> {
                        MessengerScreen(
                            conversations = conversations,
                            activeConversation = activeConversation,
                            voiceRooms = voiceRooms,
                            onSelectConversation = { viewModel.openConversation(it) },
                            onCloseConversation = { viewModel.closeActiveConversation() },
                            onSendMessage = { convId, text -> viewModel.sendMessage(convId, text) }
                        )
                    }

                    MainTab.NOTIFICATIONS -> {
                        NotificationsScreen(
                            notifications = notifications,
                            currentFilter = notifFilter,
                            onFilterChanged = { viewModel.setNotifFilter(it) },
                            onMarkAsRead = { viewModel.markNotificationRead(it) },
                            onClearAll = { viewModel.clearAllNotifications() }
                        )
                    }

                    MainTab.PROFILE -> {
                        ProfileScreen(
                            currentUser = currentUser,
                            onLogout = { viewModel.logout() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DxdBottomNavigationBar(
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    chatBadgeCount: Int,
    alertsBadgeCount: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DxdDarkObsidian)
            .border(
                width = 1.dp,
                color = DxdRedGlowBorder.copy(alpha = 0.4f),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                tab = MainTab.HOME,
                isSelected = currentTab == MainTab.HOME,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                label = "Home",
                onClick = { onTabSelected(MainTab.HOME) }
            )

            BottomNavItem(
                tab = MainTab.GAMING,
                isSelected = currentTab == MainTab.GAMING,
                selectedIcon = Icons.Filled.SportsEsports,
                unselectedIcon = Icons.Outlined.SportsEsports,
                label = "Gaming",
                onClick = { onTabSelected(MainTab.GAMING) }
            )

            BottomNavItem(
                tab = MainTab.CHAT,
                isSelected = currentTab == MainTab.CHAT,
                selectedIcon = Icons.Filled.Forum,
                unselectedIcon = Icons.Outlined.Forum,
                label = "Chat",
                badgeCount = chatBadgeCount,
                onClick = { onTabSelected(MainTab.CHAT) }
            )

            BottomNavItem(
                tab = MainTab.NOTIFICATIONS,
                isSelected = currentTab == MainTab.NOTIFICATIONS,
                selectedIcon = Icons.Filled.Notifications,
                unselectedIcon = Icons.Outlined.Notifications,
                label = "Alerts",
                badgeCount = alertsBadgeCount,
                onClick = { onTabSelected(MainTab.NOTIFICATIONS) }
            )

            BottomNavItem(
                tab = MainTab.PROFILE,
                isSelected = currentTab == MainTab.PROFILE,
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person,
                label = "Profile",
                onClick = { onTabSelected(MainTab.PROFILE) }
            )
        }
    }
}

@Composable
fun BottomNavItem(
    tab: MainTab,
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    label: String,
    badgeCount: Int = 0,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .testTag("nav_tab_${tab.name.lowercase()}"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BadgedBox(
            badge = {
                if (badgeCount > 0) {
                    Badge(
                        containerColor = DxdNeonRed,
                        contentColor = Color.White
                    ) {
                        Text(
                            text = if (badgeCount > 9) "9+" else "$badgeCount",
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp
                        )
                    }
                }
            }
        ) {
            Icon(
                imageVector = if (isSelected) selectedIcon else unselectedIcon,
                contentDescription = label,
                tint = if (isSelected) DxdBrightRed else DxdTextMuted,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label.uppercase(),
            fontFamily = OrbitronFontFamily,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontSize = 9.sp,
            letterSpacing = 0.5.sp,
            color = if (isSelected) DxdBrightRed else DxdTextMuted
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Active glowing neon indicator dot
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(if (isSelected) DxdNeonRed else Color.Transparent)
        )
    }
}
