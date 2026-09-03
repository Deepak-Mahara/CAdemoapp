package com.example.androidca1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.androidca1.ui.theme.AndroidCa1Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCa1Theme {
                foodDeleiveryApp()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun foodDeleiveryApp() {

    val sheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    var showCartDialog by remember {
        mutableStateOf(false)
    }

    var cartCount by remember {
        mutableStateOf(0)
    }

    BottomSheetScaffold(
        scaffoldState = sheetState,

        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(24.dp)
            ) {

                Text(
                    text = "Item A Details",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        scope.launch {
                            sheetState.bottomSheetState.hide()
                        }
                    }
                ) {
                    Text("Close")
                }
            }
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Item A",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    scope.launch {
                        sheetState.bottomSheetState.expand()
                    }
                }
            ) {
                Text("View")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    showCartDialog = true
                }
            ) {
                Text("Cart")
            }
        }
    }

    if (showCartDialog) {

        Dialog(
            onDismissRequest = {
                showCartDialog = false
            }
        ) {

            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {

                    Text(
                        text = "Cart",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Items in cart: $cartCount")

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            cartCount++
                        }
                    ) {
                        Text("Add to Cart")
                    }

                    Button(
                        onClick = {
                            showCartDialog = false
                        }
                    ) {
                        Text("Close")
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidCa1Theme {

    }
}
