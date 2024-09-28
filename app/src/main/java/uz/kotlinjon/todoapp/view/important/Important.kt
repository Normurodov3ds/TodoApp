package uz.kotlinjon.todoapp.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.kotlinjon.todoapp.R
import uz.kotlinjon.todoapp.view.important.ImportantViewModel
import uz.kotlinjon.todoapp.model.TaskModel
import uz.kotlinjon.todoapp.view.home.ItemType
import uz.kotlinjon.todoapp.view.home.TaskItemView
import uz.kotlinjon.todoapp.view.home.UiEvents
import uz.kotlinjon.todoapp.view.home.color

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Important(modifier: Modifier) {
    val viewMode = viewModel<ImportantViewModel>()
    val events = viewMode.getUiEvents().collectAsState()

    when (events.value) {
        is UiEvents.Error -> {

        }

        UiEvents.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = colorResource(id = color.simple_color),
                    modifier = modifier.align(Alignment.Center)
                )
            }
        }

        is UiEvents.Success -> {
            Box(modifier = modifier.fillMaxSize()) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = colorResource(id = color.bg_screen))
                        .verticalScroll(rememberScrollState())
                ) {
                    val list = (events.value as UiEvents.Success).list
                    for (i in list.indices) {
                        val itemType = if (i == 0) {
                            ItemType.First(left = 8.dp, top = 8.dp, right = 8.dp)
                        } else if (i == list.size - 1) {
                            ItemType.Last(left = 8.dp, top = 8.dp, bottom = 8.dp, right = 8.dp)
                        } else {
                            ItemType.Center(left = 8.dp, top = 8.dp, right = 8.dp)
                        }
                        TaskItemView(modifier = modifier, model = list[i], itemType = itemType){
                           viewMode.deleteTask(it)
                        }
                    }
                }
            }
        }
    }

}
