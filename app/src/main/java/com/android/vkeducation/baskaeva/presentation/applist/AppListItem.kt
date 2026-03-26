package com.android.vkeducation.baskaeva.presentation.applist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun AppListItem(
    app: AppListEntry,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val textColor = Color.Black
    val subtextColor = Color.DarkGray
    val categoryColor = Color.Gray

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(app.id) }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = app.iconUrl,
            contentDescription = app.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(12.dp)),
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = app.name,
                style = MaterialTheme.typography.bodyLarge,
                color = textColor,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = app.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = subtextColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = app.category,
                style = MaterialTheme.typography.bodySmall,
                color = categoryColor,
            )
        }
    }
}