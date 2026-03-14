package com.example.tomato.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,

        label = {
            Text(text = label)
        },

        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = label
            )
        },

        visualTransformation = visualTransformation,

        singleLine = singleLine,

        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)

    )
}