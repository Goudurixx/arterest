package com.goudurixx.arterest.shared.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    searchTerm: String,
    onSearchClicked: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val tempSearchTerm: MutableState<String> = remember { mutableStateOf(searchTerm) }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = tempSearchTerm.value,
        onValueChange = { tempSearchTerm.value = it },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Companion.Default.copy(imeAction = ImeAction.Companion.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onSearchClicked(tempSearchTerm.value)
            }
        ),
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(4.dp),
                onClick = {
                    keyboardController?.hide()
                    onSearchClicked(tempSearchTerm.value)
                },
            ) {
                Icon(
                    Icons.Default.Search,
                    null,
                    modifier = Modifier.padding(4.dp),
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun SearchBarPreview() {
    SearchBar(
        "cats",
        {}
    )
}