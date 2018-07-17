package com.thekarlo95.core.search.domain.executor

import io.reactivex.Scheduler
import java.util.concurrent.Executor

interface ThreadExecutor  {
    val scheduler: Scheduler
}