package space.stanton.know.ui

import androidx.compose.ui.text.TextStyle
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
        color = CommonPalette.NeonPink,
        textAlign = TextAlign.Center,
    )
}