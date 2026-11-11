package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.CyberBadge
import com.example.ui.components.NeonGamingCard
import com.example.ui.components.NeonPrimaryButton
import com.example.ui.components.NeonTextField
import com.example.ui.theme.DxdBrightRed
import com.example.ui.theme.DxdDarkObsidian
import com.example.ui.theme.DxdDarkRedContainer
import com.example.ui.theme.DxdNeonRed
import com.example.ui.theme.DxdPureBlack
import com.example.ui.theme.DxdRedGlowBorder
import com.example.ui.theme.DxdSurfaceVariant
import com.example.ui.theme.DxdTextMuted
import com.example.ui.theme.DxdTextPrimary
import com.example.ui.theme.DxdTextSecondary
import com.example.ui.theme.OrbitronFontFamily
import com.example.ui.theme.RajdhaniFontFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: (String, String) -> Unit,
    onNavigateBackToLogin: () -> Unit
) {
    var gamerTag by remember { mutableStateOf("SHADOW_ACE") }
    var clanTag by remember { mutableStateOf("DXD") }
    var email by remember { mutableStateOf("shadow@dxd.gg") }
    var password by remember { mutableStateOf("hunter99") }
    var selectedGame by remember { mutableStateOf("Valorant") }
    var agreeToTerms by remember { mutableStateOf(true) }

    val gamesList = listOf("Valorant", "Apex Legends", "Call of Duty: Warzone", "CS2", "League of Legends")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DxdPureBlack)
            .testTag("register_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 560.dp)
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            // Top Back Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                IconButton(
                    onClick = onNavigateBackToLogin,
                    modifier = Modifier.testTag("register_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to Login",
                        tint = DxdNeonRed
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "RECRUIT REGISTRATION",
                    fontFamily = OrbitronFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                )
            }

            // Live Gamer Card Preview
            NeonGamingCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                borderColor = DxdNeonRed,
                glowIntensity = 0.6f
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DxdDarkRedContainer.copy(alpha = 0.4f))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CyberBadge(text = "NEW RECRUIT • LVL 1", color = DxdBrightRed)
                        Text(
                            text = "STATUS: READY",
                            fontFamily = OrbitronFontFamily,
                            fontSize = 10.sp,
                            color = DxdTextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "[${clanTag.ifBlank { "DXD" }}] ${gamerTag.ifBlank { "PLAYER" }}#001",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Primary Game: $selectedGame",
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 13.sp,
                        color = DxdTextSecondary
                    )
                }
            }

            // Registration Inputs Form
            NeonGamingCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "ACCOUNT CREDENTIALS",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp,
                        color = DxdBrightRed
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        NeonTextField(
                            value = clanTag,
                            onValueChange = { if (it.length <= 5) clanTag = it.uppercase() },
                            label = "Clan Tag",
                            modifier = Modifier.weight(0.35f),
                            testTag = "register_clan_tag_input"
                        )

                        NeonTextField(
                            value = gamerTag,
                            onValueChange = { gamerTag = it.uppercase() },
                            label = "Gamer Tag",
                            leadingIcon = Icons.Default.Badge,
                            modifier = Modifier.weight(0.65f),
                            testTag = "register_gamer_tag_input"
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    NeonTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email Address",
                        leadingIcon = Icons.Default.Email,
                        testTag = "register_email_input"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    NeonTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        leadingIcon = Icons.Default.Lock,
                        visualTransformation = PasswordVisualTransformation(),
                        testTag = "register_password_input"
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Primary Game Selection
                    Text(
                        text = "SELECT PRIMARY COMPETITIVE GAME",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp,
                        color = DxdTextPrimary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        gamesList.forEach { game ->
                            val isSelected = selectedGame == game
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) DxdNeonRed else DxdSurfaceVariant)
                                    .border(
                                        width = 1.dp,
                                        color = if (isSelected) DxdBrightRed else Color(0xFF2B2E40),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable { selectedGame = game }
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = game,
                                    fontFamily = RajdhaniFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = if (isSelected) Color.White else DxdTextSecondary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Terms checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = agreeToTerms,
                            onCheckedChange = { agreeToTerms = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = DxdNeonRed,
                                checkmarkColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "I agree to DXD Fair Play Rules & Code of Conduct",
                            fontFamily = RajdhaniFontFamily,
                            fontSize = 13.sp,
                            color = DxdTextSecondary
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    NeonPrimaryButton(
                        text = "Initialize Gamer Profile",
                        onClick = {
                            if (gamerTag.isNotBlank() && agreeToTerms) {
                                onRegisterSuccess(gamerTag, selectedGame)
                            }
                        },
                        enabled = gamerTag.isNotBlank() && agreeToTerms,
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "register_submit_button"
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Switch back to login
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already enlisted? ",
                    fontFamily = RajdhaniFontFamily,
                    fontSize = 14.sp,
                    color = DxdTextSecondary
                )
                Text(
                    text = "Sign In",
                    fontFamily = RajdhaniFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = DxdNeonRed,
                    modifier = Modifier
                        .clickable(onClick = onNavigateBackToLogin)
                        .testTag("register_to_login_link")
                )
            }
        }
    }
}
