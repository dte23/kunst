package com.example.kunsthandler.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.kunsthandler.R
import com.example.kunsthandler.models.Category
import com.example.kunsthandler.models.FrameType
import com.example.kunsthandler.models.Photo
import com.example.kunsthandler.models.PhotoSize
import com.example.kunsthandler.models.SelectedPhoto
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel
import com.example.kunsthandler.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    photo: Photo?,
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: KunsthandlerViewModel = viewModel()
) {
    // Set the current photo in the ViewModel
    LaunchedEffect(photo) {
        photo?.let { viewModel.setCurrentPhoto(it) }
    }

    var selectedFrameType by remember { mutableStateOf(FrameType.WOOD) }
    var selectedPhotoSize by remember { mutableStateOf(PhotoSize.SMALL) }
    var frameWidth by remember { mutableIntStateOf(10) }

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.details),
                onBackClick = onNavigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            val categories by viewModel.categories.observeAsState(initial = emptyList())
            val categories = emptyList<Category>()

            photo?.let { currentPhoto ->
                // Photo with Card background
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        // Category name text
                        Text(
                            text = categories.find { it.id == currentPhoto.categoryId }?.name ?: "",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 4.dp),
                            textAlign = TextAlign.Center
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(280.dp) // Fixed width approach
                                    .wrapContentHeight()
                                    .background(Color.White)
                                    .border(
                                        width = frameWidth.dp,
                                        color = selectedFrameType.color,
                                        shape = MaterialTheme.shapes.small
                                    ),
                                contentAlignment = Alignment.Center
                            )
                            {
//                                Text(
//                                    text = photo.imageUrl,
//                                    fontSize = 4.sp,                       // debug shiz
//                                    modifier = Modifier.padding(14.dp)
//                                )

                                AsyncImage(
                                    model = photo.imageUrl,
                                    contentDescription = photo.title,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                )
                            }
                        }
                    }
                }

                Text(
                    text = stringResource(R.string.velg_ramme_og_størrelse),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, top = 12.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left column: Frame Type
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        FrameType.entries.forEach { frameType ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("frame_type_${frameType.name.lowercase()}")
                                    .clickable { selectedFrameType = frameType }
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedFrameType == frameType,
                                    onClick = { selectedFrameType = frameType },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colorScheme.primary
                                    )
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = frameType.name,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    // Right column: Photo Size
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        PhotoSize.entries.forEach { size ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("photo_size_${size.name.lowercase()}")
                                    .clickable { selectedPhotoSize = size }
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedPhotoSize == size,
                                    onClick = { selectedPhotoSize = size },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colorScheme.primary
                                    )
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = size.name,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                // Frame width section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.select_frame_width),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(10, 15, 20).forEach { width ->
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("frame_width_$width")
                                    .clickable { frameWidth = width }
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                RadioButton(
                                    selected = frameWidth == width,
                                    onClick = { frameWidth = width },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colorScheme.primary
                                    )
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "${width}mm",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                // Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            val selectedPhoto = SelectedPhoto(
                                photo = currentPhoto,
                                frameType = selectedFrameType,
                                frameWidth = frameWidth,
                                photoSize = selectedPhotoSize,
                                photoPrice = currentPhoto.price
                            )
                            viewModel.addToCart(selectedPhoto)
                            onNavigateToHome()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("add_to_cart")
                    ) {
                        Text(stringResource(R.string.legg_i_handlekurv))
                    }

                    OutlinedButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.hjem))
                    }
                }
            }
        }
    }
}