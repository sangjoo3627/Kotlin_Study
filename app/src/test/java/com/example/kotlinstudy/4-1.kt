package com.example.kotlinstudy

import org.junit.Test
import java.lang.IllegalArgumentException

/**
 *   조건확인 함수
 *   - 사용하려는 값 혹은 상태를 확인하고 올바른 상태에서만 실행되도록할때 사용
 */

class `4-1` {

    /**
     *   특정 값의 일치 여부 확인 : check, require
     *   - 인자로 받은 표현식이 참이 아닌 경우 예외를 발생시킴!
     *   - check() : IllegalStateException
     *   - require() : IllegalArgumentException
     */

    @Test
    fun test1_1() {
        // check()의 두가지 종류
        try {
            check(false, { println("Exception1") })
            println("Work!")
        } catch (e: IllegalStateException) {
            println("Exception2")
        }
    }

    @Test
    fun test1_2() {
        // require()의 두가지 종류
        try {
            require("str".length > 10)
            println("Work!")
        } catch (e: java.lang.IllegalArgumentException) {
            println("Exception")
        }
    }

    @Test
    fun test1_3() {
        // requireNotNull() 함수
        var str = null

        try {
            val msg = requireNotNull(str, { println("Exception1") })
        } catch (e: IllegalArgumentException) {
            println("Exception2")
        }
    }


    /**
     *   명시적으로 실행 중단하기 : error
     *   - 호출될 가능성이 없는 영역에 알수없는 오류로 진입하게 될 경우를 방지
     *   - 이 경우 임의로 예외를 발생시켜 프로그램의 실행을 막는다
     *   - error() : 인자로 받은 message와 함께 IllegalStateException 발생
     */

    @Test
    fun test2_1() {
        val isPrepared = false

        // 인자로 받은 값 isPrepared가 거짓일 경우 예외발생
        if (!isPrepared) {
            error("Not prepared yet")
        }
    }

    @Test
    fun test2_2() {
        val isPrepared = false

        // 인자로 받은 값 isPrepared가 거짓일 경우 예외발생 catch 예제
        try {
            if (!isPrepared) {
                error("Not prepared yet")
            }
        } catch (e: IllegalStateException) {
            println("Exception")
        }
    }


    /**
     *   보통 주석을 사용하여 추가 작업이 필요함을 표시하고 임의의값을 반환하도록 구현해두는데
     *   간혹 이 주석을 미처 확인못하고 그냥 두어 버그가 발생하는 경우를 방지하기 위해 TODO함수 사용
     *   TODO() : NotImplementedError 예외를 발생시켜 이 부분이 아직 완성되지 않았음을 알림 (+ 메세지와 함께도 가능)
     */

    @Test
    fun test2_3() {
        fun stop() {
            TODO("This part is not implemented")
        }

        stop()
    }
}