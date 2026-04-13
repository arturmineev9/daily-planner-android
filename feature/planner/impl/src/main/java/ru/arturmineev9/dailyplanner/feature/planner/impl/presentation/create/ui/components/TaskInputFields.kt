package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.ui.create.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

private const val TITLE_MAX_LENGTH = 50
private const val DESC_MAX_LENGTH = 200

@Composable
fun TaskInputFields(
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        OutlinedTextField(
            value = title,
            onValueChange = { newValue ->
                if (newValue.length <= TITLE_MAX_LENGTH) {
                    onTitleChange(newValue)
                }
            },
            label = { Text("Название") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            supportingText = {
                Text(
                    text = "${title.length} / $TITLE_MAX_LENGTH",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        )

        OutlinedTextField(
            value = description,
            onValueChange = { newValue ->
                if (newValue.length <= DESC_MAX_LENGTH) {
                    onDescriptionChange(newValue)
                }
            },
            label = { Text("Описание (необязательно)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            supportingText = {
                Text(
                    text = "${description.length} / $DESC_MAX_LENGTH",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        )
    }
}