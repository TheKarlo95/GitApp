package com.thekarlo95.core.repodetail.domain.interactor

import io.reactivex.subscribers.DisposableSubscriber

open class DefaultObserver<T> : DisposableSubscriber<T>() {

    private var isFirstOnNext = true

    override fun onComplete() {
    }

    override fun onNext(state: T) {
        if (isFirstOnNext) {
            onFirstOnNext(state)
            isFirstOnNext = false
        }
    }

    override fun onError(e: Throwable) {
    }

    open fun onFirstOnNext(state: T) {

    }
}