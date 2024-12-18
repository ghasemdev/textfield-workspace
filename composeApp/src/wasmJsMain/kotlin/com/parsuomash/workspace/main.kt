package com.parsuomash.workspace

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.ComposeViewport
import androidx.window.core.layout.WindowWidthSizeClass
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val focusManager = LocalFocusManager.current
        val scrollState = rememberScrollState()

        val phone1 = rememberTextFieldState()
        var phone2 by remember { mutableStateOf("") }

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .statusBarsPadding()
                    .clickableWithoutRipple {
                        focusManager.clearFocus()
                    },
                backgroundColor = Color(0xFF152132),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(
                                if (currentWindowAdaptiveInfo()
                                        .windowSizeClass
                                        .windowWidthSizeClass == WindowWidthSizeClass.COMPACT
                                ) 1f else .5f
                            )
                            .fillMaxHeight()
                            .verticalScroll(scrollState)
                            .padding(top = 32.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            modifier = Modifier.padding(bottom = 24.dp),
                            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fugiat volutpat nam option dignissim cupiditat iusto id ipsum eos congue odio. Aliquid commodi quod.",
                            style = MaterialTheme.typography.button,
                            fontSize = 14.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Right,
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                        ) {
                            BasicTextField(
                                state = phone1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 54.dp)
                                    .background(
                                        color = Color(0xFF19273B),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(16.dp),
                                inputTransformation = InputTransformation
                                    .maxLength(11)
                                    .then {
                                        if (!asCharSequence().isDigitsOnly()) {
                                            revertAllChanges()
                                        }
                                    },
                                textStyle = MaterialTheme.typography.subtitle1.copy(
                                    color = Color.White,
                                    fontSize = 20.sp,
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Phone,
                                ),
                                lineLimits = TextFieldLineLimits.SingleLine,
                                cursorBrush = SolidColor(Color(0xFF50CD89)),
                                decorator = { content ->
                                    if (phone1.text.isEmpty()) {
                                        Text(
                                            text = "phone1",
                                            color = Color(0xFFCCCACF),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Normal,
                                        )
                                    }
                                    content()
                                }
                            )

                            TextField(
                                value = phone2,
                                onValueChange = {
                                    if (it.isDigitsOnly() && it.length <= 11) {
                                        phone2 = it
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 54.dp),
                                textStyle = MaterialTheme.typography.subtitle1.copy(
                                    color = Color.White,
                                    fontSize = 20.sp,
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Phone,
                                ),
                                singleLine = true,
                                placeholder = {
                                    Text(
                                        text = "phone2",
                                        color = Color(0xFFCCCACF),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Normal,
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color(0xFF19273B),
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    cursorColor = Color(0xFF50CD89),
                                ),
                                shape = RoundedCornerShape(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeightIn(54.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF40BED0),
                                contentColor = Color(0xFFFFFFFF),
                                disabledBackgroundColor = Color(0xFF40BED0).copy(alpha = ContentAlpha.disabled),
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                disabledElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                focusedElevation = 0.dp,
                            ),
                            onClick = {},
                        ) {
                            Text(
                                text = "confirm",
                                style = MaterialTheme.typography.body1,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun CharSequence.isDigitsOnly(): Boolean = all { it.isDigit() }

fun Modifier.clickableWithoutRipple(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
) = this.composed(
    inspectorInfo = debugInspectorInfo {
        name = CLICKABLE
        properties[ENABLED] = enabled
        properties[ON_CLICK_LABEL] = onClickLabel
        properties[ROLE] = role
        properties[ON_CLICK] = onClick
    },
) {
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClick,
        role = role,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    )
}

private const val CLICKABLE = "clickable"
private const val ENABLED = "enabled"
private const val ON_CLICK_LABEL = "onClickLabel"
private const val ROLE = "role"
private const val ON_CLICK = "onClick"

