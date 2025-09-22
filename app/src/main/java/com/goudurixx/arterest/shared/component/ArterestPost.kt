package com.goudurixx.arterest.shared.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.goudurixx.arterest.R
import com.goudurixx.arterest.shared.viewModel.ArterestPost

@Composable
fun ArterestPost(
    post: ArterestPost,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (post.imageUrl.isNotBlank()) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    model = post.imageUrl,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    error = painterResource(R.drawable.ic_launcher_foreground),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Companion.CenterVertically,
            ) {
//                AsyncImage(
//                    modifier = Modifier
//                        .size(48.dp)
//                        .padding(4.dp)
//                        .clip(CircleShape),
//                    model = post.authorPic,
//                    placeholder = painterResource(R.drawable.ic_person),
//                    error = painterResource(R.drawable.ic_person),
//                    contentDescription = null
//                )
                Column(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        style = MaterialTheme.typography.labelLarge,
                        fontStyle = FontStyle.Companion.Normal,
                        text = post.title
                    )
                }
            }
            Text(
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium,
                text = post.description
            )
        }
    }
}


@PreviewLightDark
@Composable
private fun SkyPostViewPreview() {
    ArterestPost(
       post = ArterestPost(
            id = "1",
            title = "Sample Post",
            description = "This is a sample description for the Arterest post component.",
            imageUrl = "https://via.placeholder.com/150",
        ),
        modifier = Modifier.padding(16.dp)
    )
}