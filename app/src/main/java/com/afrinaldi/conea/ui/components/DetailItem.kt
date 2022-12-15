package com.afrinaldi.conea.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afrinaldi.conea.R
import com.afrinaldi.conea.ui.theme.ConeaTheme

@Composable
fun DetailItem(
    image: Int,
    name: String,
    detail: String,
    favoriteStatus: Boolean,
    updateFavoriteStatus: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(
            modifier = modifier
                .height(320.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            IconButton(modifier = modifier.align(Alignment.TopEnd) ,onClick = {
                updateFavoriteStatus(name)
            }){
                Icon(
                    painter = if (favoriteStatus) {
                        painterResource(id = R.drawable.ic_favorite_filled)
                    } else {
                        painterResource(id = R.drawable.ic_favorite_outlined)
                    },
                    contentDescription = stringResource(id = R.string.is_favorite),
                    tint = Color.Red,
                )
            }
        }
        Text(
            text = name,
            style = MaterialTheme.typography.h6,
            modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )
        Text(
            text = detail,
            style = MaterialTheme.typography.caption,
            modifier = modifier.padding(horizontal = 8.dp)
        )
    }
}

@Preview
@Composable
fun DetailItemPreview() {
    ConeaTheme {
        val status = false
        DetailItem(image = R.drawable.nal, name = "afrinaldi", detail = "Mari ngoding", status, updateFavoriteStatus = {status != status})
    }
}