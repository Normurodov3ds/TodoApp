package uz.kotlinjon.todoapp.view.addTask

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.kotlinjon.todoapp.view.home.color
import uz.kotlinjon.todoapp.model.TaskModel
import java.text.SimpleDateFormat

@Composable
fun AddTask(modifier: Modifier,action: () -> Unit) {
    val viewModel: AddTaskVMC = viewModel<AddTaskViewModel>()
    Box(
        modifier = modifier
            .background(colorResource(id = color.bg_screen))
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(color = colorResource(id = color.bg_screen))
                .border(
                    1.dp,
                    shape = RoundedCornerShape(16.dp),
                    color = colorResource(id = color.simple_color)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            MyTextField(modifier) {
                viewModel.insertData(it)
                action()
            }
        }
    }
}


@SuppressLint("SimpleDateFormat")
@Composable
fun MyTextField(modifier: Modifier, action: (TaskModel) -> Unit) {
    var disc by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    TextField(
        value = title,
        onValueChange = { title = it },
        placeholder = {
            Text(
                text = "Enter your task name",
                fontFamily = FontFamily.Monospace,
                color = colorResource(id = color.black),
                fontSize = 12.sp,
            )
        },
        leadingIcon = { Icon(Icons.Filled.Add, contentDescription = "Task") },
        isError = title.isBlank(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp, 16.dp, 0.dp)
    )

    TextField(
        value = disc,
        onValueChange = { disc = it },
        placeholder = {
            Text(
                text = "Enter your disc",
                fontFamily = FontFamily.Monospace,
                color = colorResource(id = color.black),
                fontSize = 12.sp,
            )
        },
        leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = "Task") },
        isError = disc.isBlank(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
    )

    Row {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(id = color.simple_color),
                uncheckedColor = colorResource(id = color.simple_color),
                checkmarkColor = colorResource(id = color.bg_screen),
            )
        )
        Text(
            text = "important",
            fontFamily = FontFamily.Monospace,
            color = colorResource(id = color.black),
            fontSize = 12.sp,
            modifier = modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }

    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = color.simple_color),
            contentColor = Color.White
        ),
        onClick = {
            if (disc.isNotEmpty() && title.isNotEmpty()) {
                val data = SimpleDateFormat("dd.MM.yyy").format(System.currentTimeMillis())
                action.invoke(TaskModel(disc = disc, title = title, data = data).apply{
                    this.changeImportant(isChecked)
                })
            }
        }, modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Text(text = "Save", color = colorResource(id = color.bg_screen))
    }

}
