package com.example.kunsthandler.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kunsthandler.R
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: KunsthandlerViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedPhotos = uiState.selectedPhotos
    val context = LocalContext.current
    
    val totalPrice = selectedPhotos.sumOf { selectedPhoto ->
        (selectedPhoto.photoPrice + 
        selectedPhoto.frameType.extraPrice + 
        selectedPhoto.photoSize.extraPrice + 
        (selectedPhoto.frameWidth * 0.5f)).toDouble()
    }

    // Get string resource before using it in onClick
    val notImplementedText = stringResource(R.string.payment_not_implemented)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.payment)) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.total_price_label),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.price_format, totalPrice.toFloat()),
                style = MaterialTheme.typography.headlineMedium
            )

            Button(
                onClick = {
                    Toast.makeText(context, notImplementedText, Toast.LENGTH_SHORT).show()
                    viewModel.clearCart()
                    onNavigateToHome()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("pay_button")
            ) {
                Text(stringResource(R.string.pay_button))
            }
        }
    }
}