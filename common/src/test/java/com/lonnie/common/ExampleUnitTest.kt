package com.lonnie.common

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    open class Parent {
        private val pFlagA = true
        private val pFlagB = true

        init {
            println("Parent's constructor begin")
            println("pFlagA=$pFlagA")
            println("pFlagB=$pFlagB")
            println("Parent's constructor end")
        }
    }

    class Son() : Parent() {
        private val sFlagA = true


        constructor(a:String) : this(){
            println("a")
        }

        init {
            println("Son's constructor begin")
            println("sFlagA=$sFlagA")
//            println("sFlagB=$sFlagB")
            println("Son's constructor end")
        }
        private val sFlagB = true
    }
}