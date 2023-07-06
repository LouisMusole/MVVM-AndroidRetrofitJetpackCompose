package com.drcmind.androidretrofitcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MysCreen(
    onGetQuotes : ()-> Unit,
    uiState: UiState
){
    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "QuotesApp",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp)
                    )
                },
                actions = {
                    IconButton(onClick = onGetQuotes) {
                        Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()){
            if(uiState.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }else if(!uiState.error.isNullOrEmpty()){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Filled.Warning,
                        contentDescription = null,
                        tint = Color.Red
                    )
                    Text(text = uiState.error, textAlign = TextAlign.Center)
                }
            }else{
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ){
                    items(uiState.quotes!!){ quote->
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = quote.author,
                                style = TextStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                    textAlign = TextAlign.End
                                )
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = quote.quote
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider()
                        }
                    }

                }
            }
        }
    }
}