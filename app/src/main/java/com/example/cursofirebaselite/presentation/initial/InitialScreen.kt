package com.example.cursofirebaselite.presentation.initial

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.ui.theme.Purple40

@Preview
@Composable
fun InitialScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.Gray, Color.Black), startY = 0f, endY = 600f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.fotonix),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Millones de fotos en un solo instante.",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )
        Text(
            text = "Registrate o inicia sesión para empezar.",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp), colors = ButtonDefaults.buttonColors(containerColor = Purple40)
        ) {
            Text(text = "Registrate", color = Color.White)
        }
        //CustomizedButton()
    }
}

/*
@Composable
fun CustomizedButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 32.dp), contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.googlepng),
            contentDescription = "",
            modifier = Modifier.size(16.dp)
        )
         Text(
            text = "Registrate con Google",
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
             textAlign = TextAlign.Center
               
         )

    }


}

 */