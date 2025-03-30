package com.example.kunsthandler.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kunsthandler.R
import com.example.kunsthandler.data.ArtDataSource
import com.example.kunsthandler.data.Artist
import com.example.kunsthandler.data.Category
import com.example.kunsthandler.data.Photo
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagesScreen(
    onNavigateToDetails: (Photo) -> Unit,
    onNavigateBack: () -> Unit,
    selectedArtist: Artist? = null,
    selectedCategory: Category? = null,
    viewModel: KunsthandlerViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val filteredPhotos = when {
        selectedArtist != null -> ArtDataSource.photosByArtist(selectedArtist)
        selectedCategory != null -> ArtDataSource.photosByCategory(selectedCategory)
        else -> ArtDataSource.photos
    }

    val title = when {
        selectedArtist != null -> stringResource(
            R.string.images_by_artist,
            selectedArtist.name,
            selectedArtist.familyName
        )
        selectedCategory != null -> stringResource(
            R.string.images_by_category,
            selectedCategory.name.lowercase()
        )
        else -> stringResource(R.string.all_images)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("nav_back")
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(filteredPhotos) { photo ->
                PhotoItem(
                    photo = photo,
                    onClick = { 
                        viewModel.setCurrentPhoto(photo)
                        onNavigateToDetails(photo)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoItem(
    photo: Photo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = photo.category.name,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .testTag("photo_${photo.id}")
                .clickable(onClick = onClick),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = photo.imageResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                Text(
                    text = photo.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
                Text(
                    text = stringResource(R.string.price_format, photo.price),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
} 