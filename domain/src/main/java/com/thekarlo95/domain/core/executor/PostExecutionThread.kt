package com.thekarlo95.domain.core.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}