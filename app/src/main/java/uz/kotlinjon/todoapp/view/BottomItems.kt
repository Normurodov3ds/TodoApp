package uz.kotlinjon.todoapp.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Task : BottomNavItem("task", Icons.AutoMirrored.Filled.List, "Task")
    data object Important : BottomNavItem("important", Icons.Default.DateRange, "Important")
    data object Add : BottomNavItem("add", Icons.Default.Add, "Add")
}