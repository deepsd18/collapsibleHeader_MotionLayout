package com.mi.collapsingToolbarWithMotionCompose.ui.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.mi.collapsingToolbarWithMotionCompose.R

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MarioMotionHandler(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    progress: Float
) {
    val context = LocalContext.current
    // To include raw file, the JSON5 script file
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene_mario)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in a String format
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
    ) {

        /**
         * bg-box
         **/
        Box(
            modifier = Modifier
                .layoutId("collapsing_box")
                .fillMaxWidth()
                .background(color = Color.DarkGray)
        )

        /**
         * Text - Greetings
         */
        Text(
            text = "Good Morning, \nDEEP",
            color = Color.White,
            modifier = Modifier
                .layoutId("greetings"),
            fontSize = 16.sp
        )

        /**
         * EMS-Card
         **/
        Card(
            modifier = Modifier
                .layoutId("ems_card"),
            border = BorderStroke(1.dp, Color.Black),
        ) {
            Text(
                text = "Emergency Message, Service Interrupted",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }

        /**
         * Text - Collapsing
         */
        Text(
            text = "Quick Actions",
            color = Color.Black,
            modifier = Modifier
                .background(Color.LightGray)
                .layoutId("main_text"),
            fontSize = 18.sp
        )

        /**
         * Lazy Column Grid.
         **/
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .layoutId("data_content")
                .padding(16.dp)
        ) {
            repeat(7) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    border = BorderStroke(1.dp, Color.Black),
                ) {
                    Text(
                        text = "Dashboard Card",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}