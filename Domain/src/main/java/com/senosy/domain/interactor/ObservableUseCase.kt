package com.senosy.domain.interactor

import com.senosy.domain.executor.PostExecutionThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {
    private val disposables = CompositeDisposable()

     abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params).subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addObservable(observable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addObservable(disposable: Disposable) {
        disposables.add(disposable)
    }
}