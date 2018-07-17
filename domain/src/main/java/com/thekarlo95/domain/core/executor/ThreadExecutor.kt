package com.thekarlo95.domain.core.executor

import io.reactivex.Scheduler
import java.util.concurrent.Executor

interface ThreadExecutor  {
    val scheduler: Scheduler
}