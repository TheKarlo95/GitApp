package com.thekarlo95.search.ui.andorid.core.logging

import android.util.Log
import com.thekarlo95.presentation.core.PresentationLogger
import hr.thekarlo95.api.github.ApiLogger

class LoggerImpl(private val tag: String) : ApiLogger, PresentationLogger {

    override fun v(msg: String): Int = Log.v(tag, msg)

    override fun v(msg: String, tr: Throwable): Int = Log.v(tag, msg, tr)

    override fun d(msg: String): Int = Log.d(tag, msg)

    override fun d(msg: String, tr: Throwable): Int = Log.d(tag, msg, tr)

    override fun i(msg: String): Int = Log.i(tag, msg)

    override fun i(msg: String, tr: Throwable): Int = Log.i(tag, msg, tr)

    override fun w(msg: String): Int = Log.w(tag, msg)

    override fun w(msg: String, tr: Throwable): Int = Log.w(tag, msg, tr)

    override fun w(tr: Throwable): Int = Log.w(tag, tr)

    override fun e(msg: String): Int = Log.e(tag, msg)

    override fun e(msg: String, tr: Throwable): Int = Log.e(tag, msg, tr)
}