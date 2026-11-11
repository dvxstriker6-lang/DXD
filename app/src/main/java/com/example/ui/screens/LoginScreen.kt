package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.NeonGamingCard
import com.example.ui.components.NeonOutlinedButton
import com.example.ui.components.NeonPrimaryButton
import com.example.ui.components.NeonTextField
import com.example.ui.theme.DxdBrightRed
import com.example.ui.theme.DxdDarkObsidian
import com.example.ui.theme.DxdNeonRed
import com.example.ui.theme.DxdPureBlack
import com.example.ui.theme.DxdSurfaceVariant
import com.example.ui.theme.DxdTextMuted
import com.example.ui.theme.DxdTextPrimary
import com.example.ui.theme.DxdTextSecondary
import com.example.ui.theme.OrbitronFontFamily
import com.example.ui.theme.RajdhaniFontFamily

@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onExploreAsGuest: () -> Unit
) {
    var gamerTagOrEmail by remember { mutableStateOf("VORTEX_X") }
    var password by remember { mutableStateOf("••••••••") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DxdPureBlack)
            .testTag("login_screen"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 520.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Header with mini logo & title
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(DxdDarkObsidian)
                    .border(1.5.dp, DxdNeonRed, RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_dxd_logo),
                    contentDescription = "DXD Logo",
                    modifier = Modifier.size(72.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "DXD GAMING",
                fontFamily = OrbitronFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                letterSpacing = 2.sp,
                color = DxdTextPrimary
            )

            Text(
                text = "Sign in to deploy to the gaming arena",
                fontFamily = RajdhaniFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = DxdTextSecondary
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Login Form Card
            NeonGamingCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                glowIntensity = 0.5f
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "PLAYER AUTHENTICATION",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        letterSpacing = 1.sp,
                        color = DxdBrightRed
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    NeonTextField(
                        value = gamerTagOrEmail,
                        onValueChange = { gamerTagOrEmail = it },
                        label = "Gamer Tag or Email",
                        leadingIcon = Icons.Default.AlternateEmail,
                        testTag = "login_gamer_tag_input"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    NeonTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Passcode",
                        leadingIcon = Icons.Default.Lock,
                        trailingIcon = {
                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Toggle password visibility",
                                    tint = DxdTextMuted
                                )
                            }
                        },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        testTag = "login_password_input"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Forgot Passcode?",
                            fontFamily = RajdhaniFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = DxdBrightRed,
                            modifier = Modifier.clickable { /* Reset flow */ }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    NeonPrimaryButton(
                        text = "Enter Arena",
                        onClick = { onLoginSuccess(gamerTagOrEmail) },
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "login_submit_button"
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Quick Social SSO Options (Prepared for Firebase Google Sign-In)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color(0xFF232535))
                )
                Text(
                    text = "OR SIGN IN WITH",
                    fontFamily = OrbitronFontFamily,
                    fontSize = 10.sp,
                    letterSpacing = 1.sp,
                    color = DxdTextMuted,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color(0xFF232535))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                NeonOutlinedButton(
                    text = "Google",
                    onClick = { onLoginSuccess("GooglePlayer_DXD") },
                    modifier = Modifier.weight(1f),
                    testTag = "login_google_button"
                )

                NeonOutlinedButton(
                    text = "Discord",
                    onClick = { onLoginSuccess("DiscordGamer_DXD") },
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.SportsEsports,
                    testTag = "login_discord_button"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Guest Explorer
            Text(
                text = "⚡ EXPLORE AS GUEST SQUAD",
                fontFamily = OrbitronFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 1.sp,
                color = DxdBrightRed,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onExploreAsGuest)
                    .padding(8.dp)
                    .testTag("login_guest_button")
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Switch to Registration
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "New to the clan? ",
                    fontFamily = RajdhaniFontFamily,
                    fontSize = 14.sp,
                    color = DxdTextSecondary
                )
                Text(
                    text = "Create Gamer Profile",
                    fontFamily = RajdhaniFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = DxdNeonRed,
                    modifier = Modifier
                        .clickable(onClick = onNavigateToRegister)
                        .testTag("login_to_register_link")
                )
            }
        }
    }
}
