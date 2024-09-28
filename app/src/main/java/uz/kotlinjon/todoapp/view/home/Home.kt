package uz.kotlinjon.todoapp.view.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import uz.kotlinjon.todoapp.common.MyAlertDialog
import uz.kotlinjon.todoapp.model.TaskModel

typealias color = R.color
typealias draw = R.drawable

@Composable
fun Home(modifier: Modifier) {
    val viewMode: HomeVMC = viewModel<HomeViewModel>()
    val events = viewMode.getEvent().collectAsState()
    viewMode.getAllTaskFromData()



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
                        TaskItemView(modifier = modifier, model = list[i], itemType = itemType) {
                            viewMode.deleteTask(it)
                        }
                    }
                }
            }
        }
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItemView(
    modifier: Modifier,
    model: TaskModel,
    itemType: ItemType = ItemType.Center(8.dp, 8.dp),
    deleteItem: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    MyAlertDialog(showDialog = showDialog, model) {
        it?.let {
            deleteItem.invoke(it)
        }
        showDialog = false
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    showDialog = true
                }
            )
            .padding(
                itemType._left, itemType._top, itemType._right, itemType._bottom
            )
            .border(
                1.dp,
                color = colorResource(id = color.simple_color),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = colorResource(id = color.bg_item),
                shape = RoundedCornerShape(17.dp)
            )
    ) {

        Box(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .background(color = colorResource(id = color.simple_color), shape = CircleShape)
        ) {
            if (model.isImportant())
                Image(
                    modifier = modifier
                        .size(24.dp)
                        .padding(5.dp),
                    painter = painterResource(id = draw.ic_important),
                    contentDescription = null
                )
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = model.title,
                modifier = modifier,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = colorResource(id = color.black),
                fontSize = 14.sp,
                maxLines = 2
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = model.disc,
                fontFamily = FontFamily.Monospace,
                color = colorResource(id = color.black),
                fontSize = 13.sp
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = model.data,
                fontFamily = FontFamily.Monospace,
                color = colorResource(id = color.black),
                fontSize = 12.sp,
                modifier = modifier.align(Alignment.End)
            )

        }
    }
}


sealed class ItemType(val _top: Dp, val _bottom: Dp, val _left: Dp, val _right: Dp) {

    data class First(
        val top: Dp = 0.dp,
        val bottom: Dp = 0.dp,
        val left: Dp = 0.dp,
        val right: Dp = 0.dp
    ) :
        ItemType(top, bottom, left, right)

    data class Last(
        val top: Dp = 0.dp,
        val bottom: Dp = 0.dp,
        val left: Dp = 0.dp,
        val right: Dp = 0.dp
    ) : ItemType(top, bottom, left, right)

    data class Center(
        val top: Dp = 0.dp,
        val bottom: Dp = 0.dp,
        val left: Dp = 0.dp,
        val right: Dp = 0.dp
    ) : ItemType(top, bottom, left, right)
}