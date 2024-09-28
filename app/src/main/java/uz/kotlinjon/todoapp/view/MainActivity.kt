package uz.kotlinjon.todoapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uz.kotlinjon.todoapp.R
import uz.kotlinjon.todoapp.view.addTask.AddTask
import uz.kotlinjon.todoapp.view.home.Home
import uz.kotlinjon.todoapp.home.Important
import uz.kotlinjon.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme(darkTheme = true) {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier
                        .statusBarsPadding(),
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    },
                    topBar = {
                        var name by remember {
                            mutableStateOf("Home")
                        }
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route
                        when (currentRoute) {
                            "task" -> name = "Task"
                            "important" -> name = "Important"
                            "add" -> name = "AddTask"
                        }
                        TopAppBar(
                            title = {
                                Text(
                                    text = name,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            backgroundColor = colorResource(id = R.color.simple_color),
                        )
                    },
                    content = { paddingValues ->
                        NavigationHost(
                            navController = navController,
                            Modifier.padding(paddingValues)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier
            .background(color = colorResource(id = R.color.bg_screen))
            .fillMaxSize(),
        navController = navController,
        startDestination = BottomNavItem.Task.route
    ) {
        composable(BottomNavItem.Task.route) {
            Home(modifier = Modifier)
        }
        composable(BottomNavItem.Important.route) {
            Important(modifier = Modifier)
        }
        composable(BottomNavItem.Add.route) {
            AddTask(modifier = Modifier){
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.simple_color)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val list = listOf(BottomNavItem.Task, BottomNavItem.Important, BottomNavItem.Add)
        list.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(item.icon, contentDescription = null)
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                label = {
                    Text(
                        text = item.label,
                        fontSize = 13.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }
}