package space.stanton.know.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import space.stanton.know.presentation.QuizScore

@Composable
fun ScoreScreen(score: QuizScore, toStart: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxHeight()
            .background(CommonPalette.MidnightBlue),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("You scored", style = normalTextStyle)
            var visible: Boolean by remember { mutableStateOf(false) }
            LaunchedEffect(score) {
//                delay(500)
                visible = true
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + expandVertically()
            ) {
                Text("${score.score}", style = scoreTextStyle)
            }
            Text("out of ${score.total}", style = normalTextStyle)
            AppButton("Back to start", onClick = toStart)
        }
    }
}


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

