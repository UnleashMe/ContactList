package com.example.contactslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.contactslist.presentation.navigation.Navigation
import com.example.contactslist.ui.theme.ContactsListTheme
import com.example.contactslist.ui.theme.rememberWindowSizeClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val window = rememberWindowSizeClass()
            ContactsListTheme(windowSizeClass = window) {
                Navigation()
            }
        }
    }
}

