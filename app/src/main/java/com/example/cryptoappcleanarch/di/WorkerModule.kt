package com.example.cryptoappcleanarch.di

import androidx.work.ListenableWorker
import com.example.cryptoappcleanarch.data.workers.ChildWorkerFactory
import com.example.cryptoappcleanarch.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}
