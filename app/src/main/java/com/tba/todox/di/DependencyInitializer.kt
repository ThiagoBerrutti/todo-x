package com.tba.todox.di

import com.tba.todox.feature.home.di.homeModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin{
        config?.invoke(this)
        modules(
            sharedModules,
            homeModules
        )
    }
}