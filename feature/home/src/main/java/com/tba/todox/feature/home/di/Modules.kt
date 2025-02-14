package com.tba.todox.feature.home.di

import com.tba.todox.feature.home.add_task.AddTaskViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModules = module {
    viewModelOf(::AddTaskViewModel)
}