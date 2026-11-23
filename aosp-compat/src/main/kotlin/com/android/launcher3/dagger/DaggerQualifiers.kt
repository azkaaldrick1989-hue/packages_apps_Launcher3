package com.android.launcher3.dagger

import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class LauncherAppSingleton

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContextSingleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UiContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BackgroundContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Ui

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Background

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LightweightBackground

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ThreadPool

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ElapsedRealtimeLong

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DisplayLibMainThread

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DisplayLibBackground
