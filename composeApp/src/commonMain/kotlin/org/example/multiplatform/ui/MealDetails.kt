package org.example.multiplatform.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil3.compose.AsyncImage
import org.example.multiplatform.models.MealResponse
import kotlin.math.min


@Composable
fun GetMealDetailScreen(meal: MealResponse?) {
    val scrollStateColumn = rememberScrollState()
    val scrollState = rememberLazyListState()
    val offset = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    var profilePicState by remember { mutableStateOf(MealsProfilePictureState.Normal) }
    val transition = updateTransition(targetState = profilePicState, label = "")
    // val imageSizeDp: Dp by animateDpAsState(if (expandableState) 200.dp else 100.dp)
    val size by animateDpAsState(targetValue = max(100.dp, 160.dp * offset))
    val imageSizeDp by transition.animateDp(targetValueByState = { it.imageSize }, label = "")
    val borderColor by transition.animateColor(targetValueByState = { it.borderColor }, label = "")
    val borderSize by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column(modifier = Modifier.verticalScroll(scrollStateColumn)) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                shape = CircleShape,
                border = BorderStroke(
                    width = borderSize,
                    color = borderColor
                )
            ) {
                AsyncImage(
                    model = meal?.imageUrl.orEmpty(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(imageSizeDp)
                        .padding(4.dp)
                )
            }

            Text(
                text = meal?.name.orEmpty(),
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4
            )

            Text(
                text = meal?.description.orEmpty(),
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.subtitle2
            )

            Button(
                onClick = {
                    profilePicState = if (profilePicState == MealsProfilePictureState.Normal) {
                        MealsProfilePictureState.Expanded
                    } else {
                        MealsProfilePictureState.Normal
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Change Image State")
            }

        }
    }
}

enum class MealsProfilePictureState(
    val imageSize: Dp,
    val borderColor: Color,
    val borderWidth: Dp
) {
    Normal(120.dp, Color.LightGray, 2.dp),
    Expanded(200.dp, Color.Green, 4.dp)

}