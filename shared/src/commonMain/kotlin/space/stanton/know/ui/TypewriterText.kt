package space.stanton.know.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TypewriterText(text: String, modifier: Modifier = Modifier, delay: Long) {
    var substring: AnnotatedString by remember { mutableStateOf(buildAnnotatedString {}) }

    LaunchedEffect(text) {
        delay(delay)
        for (i in 0..text.length) {
            substring = buildAnnotatedString {
                val normal = text.substring(0,  i)
                val char = text.getOrNull(i)
                println("$normal | ${char ?: 'X'} ")
                append(normal)
                withStyle(SpanStyle(fontSize = 35.sp, color = Color.White)) {
                    append(char ?: ' ')
                }
            }
            delay(150L)
        }
    }

    Text(substring, style = CommonTypography.normalTextStyle, modifier = modifier)
}
