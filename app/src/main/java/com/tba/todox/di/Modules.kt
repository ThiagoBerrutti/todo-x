package com.tba.todox.di


import com.tba.todox.MainActivityViewModel
import com.tba.todox.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModules = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::MainActivityViewModel)
}