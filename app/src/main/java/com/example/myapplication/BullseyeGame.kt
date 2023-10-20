import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun BullseyeGame() {
    var targetValue by remember { mutableStateOf((0..100).random()) }
    var sliderValue by remember { mutableStateOf(50f) }
    var score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameTitle()
        Spacer(modifier = Modifier.height(16.dp))
        TargetValue(targetValue)
        Spacer(modifier = Modifier.height(16.dp))
        SliderComponent(sliderValue) { newValue ->
            sliderValue = newValue
        }
        Spacer(modifier = Modifier.height(16.dp))
        HitMeButton(targetValue, sliderValue) { points ->
            score += points
            targetValue = (0..100).random()
            sliderValue = 50f
        }
        Spacer(modifier = Modifier.height(16.dp))
        Score(score)
    }
}

@Composable
@Preview
fun GameTitle() {
    Text(text = "Bull's Eye Game", fontSize = 24.sp)
}

@Composable
fun TargetValue(targetValue: Int) {
    Text(text = "Move the slider as close as you can: $targetValue", fontSize = 16.sp)
}

@Composable
fun SliderComponent(sliderValue: Float, onValueChange: (Float) -> Unit) {
    Slider(
        value = sliderValue,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        valueRange = 0f..100f
    )
}

@Composable
fun HitMeButton(targetValue: Int, sliderValue: Float, onClick: (Int) -> Unit) {
    Button(onClick = {
        val difference = Math.abs(sliderValue - targetValue)
        val points = when {
            difference <= 3 -> 5
            difference <= 8 -> 1
            else -> 0
        }
        onClick(points)
    }) {
        Text(text = "Hit Me!")
    }
}

@Composable
fun Score(score: Int) {
    Text(text = "Your Score: $score", fontSize = 20.sp)
}
