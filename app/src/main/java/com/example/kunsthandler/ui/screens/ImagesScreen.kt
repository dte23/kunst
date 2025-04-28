package com.example.kunsthandler.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kunsthandler.R
import com.example.kunsthandler.models.Artist
import com.example.kunsthandler.models.Category
import com.example.kunsthandler.models.Photo
import com.example.kunsthandler.ui.components.AppTopBar
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagesScreen(
    modifier: Modifier = Modifier,
    selectedArtist: Artist? = null,
    selectedCategory: Category? = null,
    onNavigateBack: () -> Unit,
    onNavigateToDetails: (Photo) -> Unit,
    viewModel: KunsthandlerViewModel = viewModel()
) {

    val photos by viewModel.photos.observeAsState(initial = emptyList())
    val categories by viewModel.categories.observeAsState(initial = emptyList())


    val filteredPhotos = when {
        selectedArtist != null -> photos.filter { it.artistId == selectedArtist.id }
        selectedCategory != null -> photos.filter { it.categoryId == selectedCategory.id }
        else -> photos
    }


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(
            text = "DEBUG - Filtered Count: ${'$'}{filteredPhotos.size}",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "DEBUG - Titles: ${'$'}{filteredPhotos.joinToString { it.title }}",
            style = MaterialTheme.typography.labelSmall
        )
    }

    // Title for the AppBar
    val title = when {
        selectedArtist != null -> stringResource(
            R.string.images_by_artist,
            selectedArtist.name,
            selectedArtist.familyName
        )
        selectedCategory != null -> stringResource(
            R.string.images_by_category,
            selectedCategory.name
        )
        else -> stringResource(R.string.all_images)
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = title,
                onBackClick = onNavigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        if (filteredPhotos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.no_images))
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(
                    items = filteredPhotos,
                    key = { it.id }
                ) { photo ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToDetails(photo) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Photo title as primary label
                        Text(
                            text = photo.title,
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        // Category label as subtitle (optional)
                        val categoryName = categories
                            .firstOrNull { it.id == photo.categoryId }
                            ?.name.orEmpty()
                        if (categoryName.isNotEmpty()) {
                            Text(
                                text = categoryName,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }

                        // Photo card
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            AsyncImage(
                                model = photo.imageUrl,
                                contentDescription = photo.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        // Price
                        Text(
                            text = stringResource(R.string.price_format, photo.price),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}





//package com.example.kunsthandler.ui.screens
//
//import android.graphics.BitmapFactory
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.produceState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.example.kunsthandler.R
//import com.example.kunsthandler.models.Artist
//import com.example.kunsthandler.models.Category
//import com.example.kunsthandler.models.Photo
//import com.example.kunsthandler.ui.components.AppTopBar
//import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel
//import androidx.compose.ui.unit.Dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.net.URL
//
//@Composable
//fun ImagesScreen(
//    modifier: Modifier = Modifier,
//    selectedArtist: Artist? = null,
//    selectedCategory: Category? = null,
//    onNavigateBack: () -> Unit,
//    onNavigateToDetails: (Photo) -> Unit,
//    viewModel: KunsthandlerViewModel = viewModel()
//) {
//    // Observe data
//    val photos     by viewModel.photos.observeAsState(initial = emptyList())
//    val categories by viewModel.categories.observeAsState(initial = emptyList())
//
//    // Filter
//    val filteredPhotos = when {
//        selectedArtist != null   -> photos.filter { it.artistId   == selectedArtist.id }
//        selectedCategory != null -> photos.filter { it.categoryId == selectedCategory.id }
//        else                     -> photos
//    }
//
//    // Title
//    val title = when {
//        selectedArtist != null   -> stringResource(
//            R.string.images_by_artist,
//            selectedArtist.name,
//            selectedArtist.familyName
//        )
//        selectedCategory != null -> stringResource(
//            R.string.images_by_category,
//            selectedCategory.name
//        )
//        else                     -> stringResource(R.string.all_images)
//    }
//
//    Scaffold(
//        topBar = { AppTopBar(title = title, onBackClick = onNavigateBack) },
//        modifier = modifier
//    ) { innerPadding ->
//        if (filteredPhotos.isEmpty()) {
//            Box(
//                Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = stringResource(R.string.no_images))
//            }
//        } else {
//            LazyVerticalGrid(
//                columns               = GridCells.Fixed(2),
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                verticalArrangement   = Arrangement.spacedBy(16.dp),
//                contentPadding        = PaddingValues(16.dp),
//                modifier              = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//            ) {
//                items(filteredPhotos, key = { it.id }) { photo ->
//                    Column(
//                        Modifier
//                            .fillMaxWidth()
//                            .clickable { onNavigateToDetails(photo) },
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        // Title
//                        Text(
//                            text      = photo.title,
//                            style     = MaterialTheme.typography.labelLarge,
//                            textAlign = TextAlign.Center,
//                            modifier  = Modifier.padding(bottom = 4.dp)
//                        )
//
//                        // Category subtitle
//                        val categoryName = categories.firstOrNull { it.id == photo.categoryId }?.name.orEmpty()
//                        if (categoryName.isNotEmpty()) {
//                            Text(
//                                text  = categoryName,
//                                style = MaterialTheme.typography.bodySmall,
//                                color = MaterialTheme.colorScheme.primary,
//                                modifier = Modifier.padding(bottom = 8.dp)
//                            )
//                        }
//
//                        // Custom network image loader, bypassing Coil
//                        NetworkImage(
//                            url = photo.imageUrl,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .aspectRatio(1f),
//                            placeholderSize = 100.dp
//                        )
//
//                        // Price
//                        Text(
//                            text     = stringResource(R.string.price_format, photo.price),
//                            style    = MaterialTheme.typography.labelLarge,
//                            color    = MaterialTheme.colorScheme.primary,
//                            modifier = Modifier.padding(top = 8.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun NetworkImage(
//    url: String,
//    modifier: Modifier = Modifier,
//    placeholderSize: Dp = 64.dp
//) {
//    // Produce an ImageBitmap from the URL
//    val imageBitmap by produceState<androidx.compose.ui.graphics.ImageBitmap?>(null, url) {
//        value = try {
//            withContext(Dispatchers.IO) {
//                val stream = URL(url).openStream()
//                BitmapFactory.decodeStream(stream).asImageBitmap()
//            }
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//    if (imageBitmap != null) {
//        Image(
//            bitmap = imageBitmap!!,
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = modifier
//        )
//    } else {
//        // simple placeholder box while loading or on error
//        Box(
//            modifier = modifier
//                .size(placeholderSize)
//                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator()
//        }
//    }
//}








//package com.example.kunsthandler.ui.screens
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import coil.compose.AsyncImage
//import com.example.kunsthandler.R
//import com.example.kunsthandler.models.Artist
//import com.example.kunsthandler.models.Category
//import com.example.kunsthandler.models.Photo
//import com.example.kunsthandler.ui.components.AppTopBar
//import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ImagesScreen(
//    modifier: Modifier = Modifier,
//    selectedArtist: Artist? = null,
//    selectedCategory: Category? = null,
//    onNavigateBack: () -> Unit,
//    onNavigateToDetails: (Photo) -> Unit,
//    viewModel: KunsthandlerViewModel = viewModel()
//) {
//    // 1) Observe live data
//    val photos by viewModel.photos.observeAsState(initial = emptyList())
//    val categories by viewModel.categories.observeAsState(initial = emptyList())
//
//    // 2) Filter based on artist or category
//    val filteredPhotos = when {
//        selectedArtist != null   -> photos.filter { it.artistId   == selectedArtist.id }
//        selectedCategory != null -> photos.filter { it.categoryId == selectedCategory.id }
//        else                     -> photos
//    }
//
//    // 3) Determine screen title
//    val title = when {
//        selectedArtist != null   -> stringResource(
//            R.string.images_by_artist,
//            selectedArtist.name,
//            selectedArtist.familyName
//        )
//        selectedCategory != null -> stringResource(
//            R.string.images_by_category,
//            selectedCategory.name
//        )
//        else                     -> stringResource(R.string.all_images)
//    }
//
//    Scaffold(
//        topBar = { AppTopBar(title = title, onBackClick = onNavigateBack) },
//        modifier = modifier
//    ) { innerPadding ->
//        if (filteredPhotos.isEmpty()) {
//            Box(
//                Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = stringResource(R.string.no_images))
//            }
//        } else {
//            LazyVerticalGrid(
//                columns               = GridCells.Fixed(2),
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                verticalArrangement   = Arrangement.spacedBy(16.dp),
//                contentPadding        = PaddingValues(16.dp),
//                modifier              = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//            ) {
//                items(
//                    items = filteredPhotos,
//                    key   = { it.id }
//                ) { photo ->
//                    Column(
//                        Modifier
//                            .fillMaxWidth()
//                            .clickable { onNavigateToDetails(photo) },
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        // Photo title
//                        Text(
//                            text      = photo.title,
//                            style     = MaterialTheme.typography.labelLarge,
//                            textAlign = TextAlign.Center,
//                            modifier  = Modifier.padding(bottom = 4.dp)
//                        )
//
//                        // Category subtitle
//                        categories
//                            .firstOrNull { it.id == photo.categoryId }
//                            ?.name
//                            ?.let { category ->
//                                Text(
//                                    text      = category,
//                                    style     = MaterialTheme.typography.bodySmall,
//                                    color     = MaterialTheme.colorScheme.primary,
//                                    modifier  = Modifier.padding(bottom = 8.dp)
//                                )
//                            }
//
//                        // Photo card with unique request key
//                        Card(
//                            Modifier
//                                .fillMaxWidth()
//                                .aspectRatio(1f),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//                        ) {
//                            // Append ID to URL so Coil sees unique data
//                            val uniqueUrl = "${photo.imageUrl}?uid=${photo.id}"
//                            AsyncImage(
//                                model          = uniqueUrl,
//                                contentDescription = photo.title,
//                                contentScale   = ContentScale.Crop,
//                                modifier       = Modifier.fillMaxSize()
//                            )
//                        }
//
//                        // Photo price
//                        Text(
//                            text     = stringResource(R.string.price_format, photo.price),
//                            style    = MaterialTheme.typography.labelLarge,
//                            color    = MaterialTheme.colorScheme.primary,
//                            modifier = Modifier.padding(top = 8.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}










