package com.tba.todox.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tba.todox.feature.home.HomeScreen
import kotlinx.serialization.Serializable


@Serializable
data object HomeScreenRoute

@Serializable
data object AddTaskRoute

@Serializable
data object CalendarRoute

@Serializable
data object FocusRoute

@Serializable
data object ProfileRoute

@Serializable
data object AddTaskDescriptionRoute

@Serializable
data object AddTaskDateRoute

@Serializable
data object AddTaskTimeRoute

@Serializable
data object AddTaskPriorityRoute


//const val HOME_SCREEN_ROUTE = "home_screen_route"
//const val ADD_TASK_ROUTE = "${HOME_SCREEN_ROUTE}/add_task"

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(HomeScreenRoute, navOptions)

fun NavGraphBuilder.homeScreen() {
    composable<HomeScreenRoute> {
        HomeScreen()
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//fun NavGraphBuilder.addTask(
//    closeDialog: () -> Unit,
//    onDismiss: () -> Unit,
//    onComplete: () -> Unit = {},
//) {
//    dialog<AddTaskRoute>(
//        dialogProperties = DialogProperties(dismissOnBackPress = true,
//            decorFitsSystemWindows = false)
//    ) {
//        val navController = rememberNavController()
//
//        NavHost(
//            navController = navController,
//            modifier = Modifier.fillMaxSize(),
//            startDestination = AddTaskDescriptionRoute
//        ) {
//            composable<AddTaskDescriptionRoute>() {
//                Log.d("NAVIGATING", "description route")
//                HomeAddTaskBottomSheet(onDismissRequest = {
//                    Log.d("TEST", "onDismissRequest")
//                },
//                    state = AddTaskState(),
//                    onTitleChanged = {},
//                    onDescriptionChanged = {},
//                    onBack = {
//                    Log.d("TEST", "onBack")
//                    closeDialog()
//                    navController.popBackStack()
//                }) {
//                    navController.navigate(
//                        AddTaskDateRoute
//                    )
//                }
//            }
//
////            composable<AddTaskDateRoute> {
////                Log.d("NAVIGATING", "date route")
////                AddTaskDatePicker(state = rememberDatePickerState()) {
////                    navController.navigate(AddTaskTimeRoute)
////                }
////            }
//
////            composable<AddTaskTimeRoute> {
////                Log.d("NAVIGATING", "time route")
////                AddTaskTimePicker(state = rememberTimePickerState()) {
////                    navController.navigate(
////                        AddTaskPriorityRoute
////                    )
////                }
////            }
////            composable<AddTaskPriorityRoute> {
////                Log.d("NAVIGATING", "priority route")
////                AddTaskSetPriority(onSave = {})
////            }
//        }
//    }

//}