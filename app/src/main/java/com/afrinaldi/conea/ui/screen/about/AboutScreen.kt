package com.afrinaldi.conea.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.afrinaldi.conea.R
import com.afrinaldi.conea.ui.theme.Shapes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.nal_hero),
            contentDescription = "afrinaldi",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(Shapes.small)
        )
        Text(
            text = stringResource(id = R.string.my_name),
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.my_email),
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 120.dp)
        ) {
            val (firstQm, quotes, lastQm) = createRefs()

            Text(
                text = "\"",
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
                modifier = modifier.constrainAs(firstQm) {
                    top.linkTo(parent.top)
                    end.linkTo(quotes.start, margin = 4.dp)
                })
            Text(
                text = stringResource(id = R.string.quotes),
                fontStyle = FontStyle.Italic,
                color = Color.Red,
                modifier = modifier.constrainAs(quotes) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(
                text = "\"",
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
                modifier = modifier.constrainAs(lastQm) {
                    top.linkTo(parent.top)
                    start.linkTo(quotes.end, margin = 4.dp)
                })
        }
    }
}