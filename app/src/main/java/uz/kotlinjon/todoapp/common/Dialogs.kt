package uz.kotlinjon.todoapp.common

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.AlertDialog
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import uz.kotlinjon.todoapp.R
import uz.kotlinjon.todoapp.model.TaskModel

@Composable
fun MyAlertDialog(showDialog: Boolean,taskModel: TaskModel, onDismiss: (Int?) -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss(null)
            },
            title = {
                Text(
                    "Delete",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = colorResource(id = R.color.black),
                    fontSize = 14.sp,
                )
            },
            text = { Text("This is task delete ?") },
            confirmButton = {
                Button(onClick = {onDismiss(taskModel.id)}) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = {
                    onDismiss(null)
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun MyDialog(showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            // Dialog oynasining ichidagikontent
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("This is a dialog")
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            }
        }
    }
}