package space.stanton.know.ui

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import space.stanton.know.presentation.QuizScore
import space.stanton.know.ui.CommonTypography.normalTextStyle
import space.stanton.know.ui.CommonTypography.scoreTextStyle

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
            var visible: Boolean by remember { mutableStateOf(false) }
            LaunchedEffect(score) {
                visible = true
            }

            val scope = rememberCoroutineScope()

            Text("You scored", style = normalTextStyle, modifier = Modifier.clickable {

                scope.launch {
                    visible = false
                    delay(3000)
                    visible = true
                }
            })


            val animatedScale by animateFloatAsState(
                targetValue = if (visible) 3.0f else 0.3f,
                animationSpec = tween(
                    600,
                    delayMillis = 1000,
                    easing = CubicBezierEasing(0.17f, 0.67f, 0.87f, 1.44f)
                ),
                label = "score_scale"
            )

            Text(
                "${score.score}",
                style = scoreTextStyle,
                modifier = Modifier.graphicsLayer {
                    scaleX = animatedScale
                    scaleY = animatedScale
                    alpha = animatedScale.coerceIn(0f..1f)
                }
            )

            Text("out of ${score.total}", style = normalTextStyle)
            AppButton("Back to start", onClick = toStart)
        }
    }
}


