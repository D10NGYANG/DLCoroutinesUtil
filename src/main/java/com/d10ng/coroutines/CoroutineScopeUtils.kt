package com.d10ng.coroutines

import kotlinx.coroutines.*

/**
 * 主线程执行
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, Unit>
 * @return Job
 */
fun launchMain(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineScope(Dispatchers.Main).launch(block = block)
}

/**
 * 子线程执行
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, Unit>
 * @return Job
 */
fun launchIO(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineScope(Dispatchers.IO).launch(block = block)
}

/**
 * 执行协程读取数据，并回到主线程
 * @receiver CoroutineScope
 * @param getIO SuspendFunction0<T>
 * @param toMain Function1<[@kotlin.ParameterName] T, Unit>
 */
fun <T> CoroutineScope.launchIO2Main(getIO: suspend () -> T, toMain:(value: T) -> Unit) {
    launch {
        val value = withIO {
            getIO.invoke()
        }
        withMain {
            toMain.invoke(value)
        }
    }
}

/**
 * 执行协程读取数据，并回到主线程
 * @param getIO Function0<T>
 * @param toMain Function1<[@kotlin.ParameterName] T, Unit>
 */
fun <T> launchIO2Main(getIO: suspend () -> T, toMain:(value: T) -> Unit) {
    CoroutineScope(Dispatchers.IO).launchIO2Main(getIO, toMain)
}

/**
 * 回到主线程
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, T>
 * @return T
 */
suspend fun <T> withMain(
    block: suspend CoroutineScope.() -> T
): T = withContext(Dispatchers.Main, block)

/**
 * 回到IO线程
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, T>
 * @return T
 */
suspend fun <T> withIO(
    block: suspend CoroutineScope.() -> T
): T = withContext(Dispatchers.IO, block)