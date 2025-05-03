package space.stanton.know.android.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.stanton.know.android.theme.Palette

@Composable
fun WelcomeScreen(onBegin: () -> Unit = {}) {
    Surface(color = Palette.MidnightBlue) {
        Scaffold(
            Modifier
                .safeContentPadding()
                .background(Palette.MidnightBlue)
        ) { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Know your Dev Stuff", style = TitleStyle)
                Spacer(Modifier.size(40.dp))
                Button(
                    onClick = onBegin,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Palette.DeepMagenta,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 2.dp,
                    )
                ) {
                    Text("Begin", fontSize = 20.sp)
                }
            }
        }
    }
}


val TitleStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 40.sp,
    color = Palette.CreamyYellow,
    textAlign = TextAlign.Center,
)


@Preview
@Composable
fun PreviewWelcomeScreen() {
    WelcomeScreen()
}