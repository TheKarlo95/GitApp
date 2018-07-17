package hr.thekarlo95.api.github

interface ApiLogger {

    fun v(msg: String): Int

    fun v(msg: String, tr: Throwable): Int

    fun d(msg: String): Int

    fun d(msg: String, tr: Throwable): Int

    fun i(msg: String): Int

    fun i(msg: String, tr: Throwable): Int

    fun w(msg: String): Int

    fun w(msg: String, tr: Throwable): Int

    fun w(tr: Throwable): Int

    fun e(msg: String): Int

    fun e(msg: String, tr: Throwable): Int
}