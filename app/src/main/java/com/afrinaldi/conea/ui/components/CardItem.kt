package com.afrinaldi.conea.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afrinaldi.conea.R
import com.afrinaldi.conea.ui.theme.ConeaTheme
import com.afrinaldi.conea.ui.theme.Shapes

@Composable
fun CardItem(
    image: Int,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(180.dp)
                .clip(Shapes.small)
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.see_detail),
            style = MaterialTheme.typography.caption,
            color = Color.Gray,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    ConeaTheme {
        CardItem(R.drawable.nal, name = "Afrinaldi")
    }
}