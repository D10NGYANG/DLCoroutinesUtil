package com.d10ng.coroutines

import kotlinx.coroutines.delay
import kotlin.test.Test
import kotlin.test.assertTrue

class CoroutineScopeUtilsTest {

    @Test
    fun test() {
        assertTrue {
            launchIO {
                println(this.toString())
            }
            launchIO {
                println(this.toString())
            }
            launchIO {
                println(this.toString())
            }
            true
        }
    }
}