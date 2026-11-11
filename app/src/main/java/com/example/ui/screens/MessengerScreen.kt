package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Mic
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ChatMessage
import com.example.model.Conversation
import com.example.model.VoiceRoom
import com.example.ui.components.CyberBadge
import com.example.ui.components.GamerAvatar
import com.example.ui.components.NeonGamingCard
import com.example.ui.components.NeonPrimaryButton
import com.example.ui.components.NeonSectionHeader
import com.example.ui.components.NeonTextField
import com.example.ui.theme.DxdBrightRed
import com.example.ui.theme.DxdDarkObsidian
import com.example.ui.theme.DxdDarkRedContainer
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
fun MessengerScreen(
    conversations: List<Conversation>,
    activeConversation: Conversation?,
    voiceRooms: List<VoiceRoom>,
    onSelectConversation: (Conversation) -> Unit,
    onCloseConversation: () -> Unit,
    onSendMessage: (String, String) -> Unit
) {
    // If a conversation is actively open, show the chat detail view
    if (activeConversation != null) {
        BackHandler {
            onCloseConversation()
        }
        ChatDetailScreen(
            conversation = activeConversation,
            onClose = onCloseConversation,
            onSendMessage = { text -> onSendMessage(activeConversation.id, text) }
        )
    } else {
        // Conversations & Voice Rooms Main List
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DxdPureBlack)
                .testTag("messenger_screen")
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 680.dp),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                // Header
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DxdDarkObsidian)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "CLAN MESSENGER & COMMS",
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            letterSpacing = 1.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Encrypted Squad Channels • Voice Comms • Match Invites",
                            fontFamily = RajdhaniFontFamily,
                            fontSize = 13.sp,
                            color = DxdTextMuted
                        )
                    }
                }

                // Active Squad Voice Comms
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    ) {
                        NeonSectionHeader(
                            title = "Squad Voice Channels",
                            subtitle = "Low latency encrypted voice rooms",
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(voiceRooms, key = { it.id }) { room ->
                                VoiceRoomCard(room = room)
                            }
                        }
                    }
                }

                // Direct & Clan Messages Header
                item {
                    NeonSectionHeader(
                        title = "Squad & Direct Transmissions",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }

                items(conversations, key = { it.id }) { conv ->
                    ConversationItemCard(
                        conversation = conv,
                        onClick = { onSelectConversation(conv) }
                    )
                }
            }
        }
    }
}

@Composable
fun VoiceRoomCard(room: VoiceRoom) {
    NeonGamingCard(
        modifier = Modifier.width(220.dp),
        shape = RoundedCornerShape(14.dp),
        borderColor = DxdNeonRed,
        glowIntensity = 0.5f
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(DxdDarkRedContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Headphones,
                        contentDescription = null,
                        tint = DxdBrightRed,
                        modifier = Modifier.size(18.dp)
                    )
                }

                CyberBadge(text = "${room.pingMs}ms", color = DxdNeonGreen)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = room.title,
                fontFamily = OrbitronFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color.White,
                maxLines = 1
            )

            Text(
                text = "${room.game} • ${room.currentMembers}/${room.maxMembers} Connected",
                fontFamily = RajdhaniFontFamily,
                fontSize = 12.sp,
                color = DxdTextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(DxdNeonRed)
                    .clickable { /* Connect Voice */ }
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "JOIN VOICE",
                    fontFamily = OrbitronFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ConversationItemCard(
    conversation: Conversation,
    onClick: () -> Unit
) {
    NeonGamingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GamerAvatar(
                tag = conversation.gamerTag,
                size = 48.dp,
                isOnline = conversation.isOnline
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = conversation.name,
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Text(
                        text = conversation.lastMessageTime,
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 11.sp,
                        color = DxdTextMuted
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = conversation.lastMessage,
                    fontFamily = RajdhaniFontFamily,
                    fontWeight = if (conversation.unreadCount > 0) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 13.sp,
                    color = if (conversation.unreadCount > 0) DxdTextPrimary else DxdTextSecondary,
                    maxLines = 1
                )

                if (conversation.currentActivity != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = conversation.currentActivity,
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 11.sp,
                        color = DxdBrightRed,
                        maxLines = 1
                    )
                }
            }

            if (conversation.unreadCount > 0) {
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(DxdNeonRed),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${conversation.unreadCount}",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ChatDetailScreen(
    conversation: Conversation,
    onClose: () -> Unit,
    onSendMessage: (String) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    var lobbyAccepted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DxdPureBlack)
            .testTag("chat_detail_screen")
    ) {
        // Chat Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DxdDarkObsidian)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = DxdNeonRed
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                GamerAvatar(tag = conversation.gamerTag, size = 38.dp, isOnline = conversation.isOnline)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = conversation.name,
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Text(
                        text = if (conversation.isOnline) "ONLINE • IN SQUAD" else "OFFLINE",
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 11.sp,
                        color = if (conversation.isOnline) DxdNeonGreen else DxdTextMuted
                    )
                }
            }

            IconButton(onClick = { /* Voice Call */ }) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Comms Call",
                    tint = DxdBrightRed
                )
            }
        }

        // Messages List
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(conversation.messages, key = { it.id }) { msg ->
                if (msg.isGameInvite) {
                    // Match / Lobby Invite Card
                    NeonGamingCard(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .align(Alignment.Start),
                        shape = RoundedCornerShape(14.dp),
                        borderColor = DxdNeonRed
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.SportsEsports,
                                    contentDescription = null,
                                    tint = DxdBrightRed
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "GAME LOBBY INVITATION",
                                    fontFamily = OrbitronFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "${msg.senderTag} invited you to join a ${msg.inviteGame ?: "Valorant"} Competitive Match lobby.",
                                fontFamily = RajdhaniFontFamily,
                                fontSize = 13.sp,
                                color = DxdTextSecondary
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            NeonPrimaryButton(
                                text = if (lobbyAccepted) "Lobby Joined ✓" else "Accept & Deploy",
                                onClick = { lobbyAccepted = true },
                                modifier = Modifier.height(38.dp)
                            )
                        }
                    }
                } else {
                    // Standard Message Bubble
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = if (msg.isFromMe) Arrangement.End else Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 14.dp,
                                        topEnd = 14.dp,
                                        bottomStart = if (msg.isFromMe) 14.dp else 2.dp,
                                        bottomEnd = if (msg.isFromMe) 2.dp else 14.dp
                                    )
                                )
                                .background(if (msg.isFromMe) DxdNeonRed else DxdSurfaceVariant)
                                .border(
                                    width = 1.dp,
                                    color = if (msg.isFromMe) DxdBrightRed else Color(0xFF2E3244),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(horizontal = 14.dp, vertical = 10.dp)
                        ) {
                            Column {
                                if (!msg.isFromMe) {
                                    Text(
                                        text = msg.senderTag,
                                        fontFamily = OrbitronFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        color = DxdBrightRed
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                }
                                Text(
                                    text = msg.text,
                                    fontFamily = RajdhaniFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = msg.timestamp,
                                    fontFamily = RajdhaniFontFamily,
                                    fontSize = 10.sp,
                                    color = if (msg.isFromMe) Color.White.copy(alpha = 0.7f) else DxdTextMuted,
                                    modifier = Modifier.align(Alignment.End)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Input Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DxdDarkObsidian)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NeonTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = "Transmit message...",
                modifier = Modifier.weight(1f),
                testTag = "chat_message_input"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(DxdNeonRed)
                    .clickable {
                        if (inputText.isNotBlank()) {
                            onSendMessage(inputText)
                            inputText = ""
                        }
                    }
                    .testTag("chat_send_button"),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
