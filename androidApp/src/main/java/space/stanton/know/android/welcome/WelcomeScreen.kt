package space.stanton.know.android.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.stanton.know.android.AppScreen
import space.stanton.know.android.theme.Palette
import space.stanton.know.android.ui.AppButton

@Composable
fun WelcomeScreen(onBegin: () -> Unit = {}) {
    AppScreen {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Know your Dev Stuff", style = TitleStyle)
            Spacer(Modifier.size(40.dp))
            AppButton("Begin", onClick = onBegin)
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