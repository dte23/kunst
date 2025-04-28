package com.example.kunsthandler.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kunsthandler.R
import com.example.kunsthandler.models.SelectedPhoto
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kunsthandler.models.Artist
import com.example.kunsthandler.models.Category
import com.example.kunsthandler.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSelectArtist: () -> Unit,
    onSelectCategory: () -> Unit,
    onPayment: () -> Unit,
    viewModel: KunsthandlerViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.app_name),
                showBackButton = false
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.select_image_by),
                style = MaterialTheme.typography.titleLarge
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onSelectArtist,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("nav_artist"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(stringResource(R.string.by_artist))
                }

                Button(
                    onClick = onSelectCategory,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("nav_category"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(stringResource(R.string.by_category))
                }
            }

            if (uiState.selectedPhotos.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.cart),
                    style = MaterialTheme.typography.titleLarge
                )

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.selectedPhotos) { selectedPhoto ->
                        CartItem(
                            selectedPhoto = selectedPhoto,
                            onRemove = { viewModel.removeFromCart(selectedPhoto) },
                            viewModel
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier.testTag("cart_count"),
                        text = stringResource(R.string.item_count, uiState.selectedPhotos.size),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(
                            R.string.total_price,
                            uiState.selectedPhotos.sumOf { it.totalPrice.toDouble() }.toFloat()
                        ),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Button(
                        onClick = onPayment,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .testTag("nav_payment"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(stringResource(R.string.to_payment))
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.cart_empty),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun CartItem(
    selectedPhoto: SelectedPhoto,
    onRemove: () -> Unit,
    viewModel: KunsthandlerViewModel,
    modifier: Modifier = Modifier
) {
//    val categories by viewModel.categories.observeAsState(initial = emptyList())
//    val artists by viewModel.artists.observeAsState(initial = emptyList())

    val categories = emptyList<Category>()
    val artists = emptyList<Artist>()

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // First column: Category/Name
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = categories.find { it.id == selectedPhoto.photo.categoryId }?.name ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = artists.find { it.id == selectedPhoto.photo.artistId }?.name ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            // Second column: Frame type/Size
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = selectedPhoto.frameType.name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = selectedPhoto.photoSize.name,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            // Third column: Width/Price
            Column(
                modifier = Modifier.weight(0.8f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.frame_width_mm, selectedPhoto.frameWidth),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = stringResource(R.string.price_format, selectedPhoto.totalPrice.toFloat()),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            // Delete button as the fourth column
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                FilledIconButton(
                    onClick = onRemove,
                    modifier = Modifier.testTag("remove_item"),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = stringResource(R.string.remove_from_cart)
                    )
                }
            }
        }
    }
} 