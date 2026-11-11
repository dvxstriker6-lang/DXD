package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NotificationItem
import com.example.model.NotificationType
import com.example.ui.components.NeonGamingCard
import com.example.ui.components.NeonOutlinedButton
import com.example.ui.components.NeonPrimaryButton
import com.example.ui.theme.DxdBrightRed
import com.example.ui.theme.DxdDarkObsidian
import com.example.ui.theme.DxdDarkRedContainer
import com.example.ui.theme.DxdNeonCyan
import com.example.ui.theme.DxdNeonGold
import com.example.ui.theme.DxdNeonGreen
import com.example.ui.theme.DxdNeonRed
import com.example.ui.theme.DxdPureBlack
import com.example.ui.theme.DxdRedGlowBorder
import com.example.ui.theme.DxdSurfaceVariant
import com.example.ui.theme.DxdTextMuted
import com.example.ui.theme.DxdTextPrimary
import com.example.ui.theme.DxdTextSecondary
import com.example.ui.theme.OrbitronFontFamily
import com.example.ui.theme.RajdhaniFontFamily

@Composable
fun NotificationsScreen(
    notifications: List<NotificationItem>,
    currentFilter: String,
    onFilterChanged: (String) -> Unit,
    onMarkAsRead: (String) -> Unit,
    onClearAll: () -> Unit
) {
    val filters = listOf(
        "ALL" to "All Alerts",
        "TOURNAMENTS" to "Tourneys",
        "INVITES" to "Squad Invites",
        "REWARDS" to "Rewards"
    )

    val filteredList = when (currentFilter) {
        "TOURNAMENTS" -> notifications.filter { it.type == NotificationType.TOURNAMENT }
        "INVITES" -> notifications.filter { it.type == NotificationType.CLAN_INVITE || it.type == NotificationType.FRIEND_REQUEST }
        "REWARDS" -> notifications.filter { it.type == NotificationType.REWARD }
        else -> notifications
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DxdPureBlack)
            .testTag("notifications_screen")
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            // Header Bar
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DxdDarkObsidian)
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "COMMAND DISPATCH",
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            letterSpacing = 1.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Live Scrim Alerts & Match Requests",
                            fontFamily = RajdhaniFontFamily,
                            fontSize = 13.sp,
                            color = DxdTextMuted
                        )
                    }

                    if (notifications.isNotEmpty()) {
                        IconButton(
                            onClick = onClearAll,
                            modifier = Modifier.testTag("notifications_clear_all")
                        ) {
                            Icon(
                                imageVector = Icons.Default.DeleteSweep,
                                contentDescription = "Clear all",
                                tint = DxdBrightRed
                            )
                        }
                    }
                }
            }

            // Filter Chips Bar
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filters) { (key, label) ->
                        val isSelected = currentFilter == key
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) DxdNeonRed else DxdSurfaceVariant)
                                .border(
                                    width = 1.dp,
                                    color = if (isSelected) DxdBrightRed else Color(0xFF2B2E40),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { onFilterChanged(key) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .testTag("notif_filter_$key")
                        ) {
                            Text(
                                text = label.uppercase(),
                                fontFamily = OrbitronFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = if (isSelected) Color.White else DxdTextSecondary
                            )
                        }
                    }
                }
            }

            // Notifications List
            if (filteredList.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 60.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.NotificationsActive,
                            contentDescription = null,
                            tint = DxdTextMuted,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "NO ACTIVE DISPATCHES",
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = DxdTextSecondary
                        )
                        Text(
                            text = "You are all caught up on transmissions",
                            fontFamily = RajdhaniFontFamily,
                            fontSize = 13.sp,
                            color = DxdTextMuted
                        )
                    }
                }
            } else {
                items(filteredList, key = { it.id }) { notif ->
                    NotificationCardItem(
                        item = notif,
                        onRead = { onMarkAsRead(notif.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationCardItem(
    item: NotificationItem,
    onRead: () -> Unit
) {
    var acceptedState by remember { mutableStateOf<Boolean?>(null) }

    val icon: ImageVector = when (item.type) {
        NotificationType.TOURNAMENT -> Icons.Default.EmojiEvents
        NotificationType.CLAN_INVITE -> Icons.Default.GroupAdd
        NotificationType.REWARD -> Icons.Default.CardGiftcard
        NotificationType.MATCH_READY -> Icons.Default.SportsEsports
        else -> Icons.Default.NotificationsActive
    }

    val iconTint: Color = when (item.type) {
        NotificationType.TOURNAMENT -> DxdBrightRed
        NotificationType.CLAN_INVITE -> DxdNeonCyan
        NotificationType.REWARD -> DxdNeonGold
        NotificationType.MATCH_READY -> DxdNeonGreen
        else -> DxdTextSecondary
    }

    NeonGamingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        borderColor = if (!item.isRead) DxdNeonRed else DxdRedGlowBorder.copy(alpha = 0.2f),
        onClick = onRead
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(iconTint.copy(alpha = 0.15f))
                        .border(1.dp, iconTint.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.title,
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color.White
                        )
                        if (!item.isRead) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(DxdNeonRed)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = item.description,
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = DxdTextSecondary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.timeAgo,
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 11.sp,
                        color = DxdTextMuted
                    )
                }
            }

            // Interactive action buttons for clan/squad invites
            if (item.hasActionButtons) {
                Spacer(modifier = Modifier.height(10.dp))
                if (acceptedState == null) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        NeonPrimaryButton(
                            text = "Accept",
                            onClick = { acceptedState = true; onRead() },
                            modifier = Modifier
                                .weight(1f)
                                .height(36.dp),
                            icon = Icons.Default.Check
                        )
                        NeonOutlinedButton(
                            text = "Decline",
                            onClick = { acceptedState = false; onRead() },
                            modifier = Modifier
                                .weight(1f)
                                .height(36.dp),
                            icon = Icons.Default.Close
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(DxdSurfaceVariant)
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (acceptedState == true) "SQUAD INVITATION ACCEPTED ✓" else "INVITATION DECLINED",
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = if (acceptedState == true) DxdNeonGreen else DxdTextMuted
                        )
                    }
                }
            } else if (item.actionLabel != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${item.actionLabel.uppercase()} →",
                    fontFamily = OrbitronFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = DxdBrightRed,
                    modifier = Modifier.clickable { onRead() }
                )
            }
        }
    }
}
