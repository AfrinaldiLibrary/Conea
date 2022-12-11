package com.afrinaldi.conea.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afrinaldi.conea.R
import com.afrinaldi.conea.ui.theme.ConeaTheme

@Composable
fun DetailItem(
    image: Int,
    name: String,
    detail: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = image),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth()
        )
        Text(text = name, style = MaterialTheme.typography.h6, modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp))
        Text(text = detail, style = MaterialTheme.typography.caption, modifier = modifier.padding(horizontal = 8.dp))
    }
}

@Preview
@Composable
fun DetailItemPreview() {
    ConeaTheme {
        DetailItem(image = R.drawable.nal, name = "afrinaldi", detail = "Mari ngoding")
    }
}