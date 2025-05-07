package space.stanton.know.ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object CommonTypography {

    val normalTextStyle = TextStyle(
        color = CommonPalette.CreamyYellow,
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
    val scoreTextStyle = TextStyle(
        fontSize = 90.sp,
        brush = Brush.Companion.radialGradient(
            colorStops = arrayOf(
                0f to Color.White,
                0.9f to CommonPalette.NeonPink,
                1f to CommonPalette.NeonPink,
            )
        ),
        shadow = Shadow(color = Color.White, blurRadius = 20f),
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
    )
}