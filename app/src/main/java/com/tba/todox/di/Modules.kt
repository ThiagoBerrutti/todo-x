package com.tba.todox.di

import com.tba.todox.home.HomeViewModel
import com.tba.todox.MainActivityViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModules = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::MainActivityViewModel)
}