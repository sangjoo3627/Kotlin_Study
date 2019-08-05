package com.example.kotlinstudy

import org.junit.Assert.*
import org.junit.Test

/**
 *   컬렉션 생성 함수
 *   - 여러 종류의 컬렉션을 간편하게 만들수 있는 함수들을 제공
 */

class `4-2` {

    @Test
    fun 배열생성함수() {

        // 일반적 배열 생성 함수
        val cities = arrayOf("Seoul", "Tokyo", "San Francisco")
        println("--------------------")

        // 빈 String 형 배열생성 함수
        val emptyStringArray = emptyArray<String>()
        assertEquals(emptyStringArray.size, 0)
        for (item in emptyStringArray)
            println(item)
        println("--------------------")

        // 배열내 각 값들이 모두 null 값이고 인자로 받은 size만큼의 크기를 갖는 배열
        val nullStoreableArray = arrayOfNulls<String>(3)
        assertEquals(nullStoreableArray.size, 3)
        for (item in nullStoreableArray)
            println(item)
        println("--------------------")

        /**
         *   이외에도 booleanArrayOf, IntArrayOf, CharArrayOf, ByteArrayOf 등등 타입에 따라 존재
         *   - 모두 자바의 같은 타입 배열과 호환됨
         */
    }

    @Test
    fun 리스트생성함수() {

        // 일반적으로 읽기만 가능한 리스트 생성 함수
        val readableList = listOf<String>()         // Empty list
        println("--------------------")

        // 인자로 받는 값 중 널 값은 무시하고 널이 아닌 값으로만 리스트를 구성하고 싶은 경우
        val listOfCountries = listOfNotNull(null)       // null이 포함될수 없으므로 빈 리스트 생성됨
        assertEquals(listOfCountries.size, 0)
        println("--------------------")

        val listOfCities = listOfNotNull("Seoul", null, "Tokyo", null)
        assertEquals(listOfCities.size, 2)
        for (item in listOfCities)
            println(item)
        println("--------------------")

        /**
         *   수정할 수 있는 리스트는 mutableListOf() 함수
         *   ArrayList 또한 arrayListOf()를 이용
         *   사용법은 위와 동일. 단, NotNull 함수는 없음
         */
    }

    @Test
    fun 맵생성함수() {

        // 일반적 맵 생성함수 : Pair()를 이용
        val cities1 = mapOf(
            Pair("SEO", "Seoul"), Pair("TOK", "Tokyo")
        )

        // 직관적인 방법
        val cities2 = mapOf(
            "SEO" to "Seoul", "TOK" to "Tokyo"
        )

        /**
         *   수정가능한 맵은 mutableMapOf() 함수
         *   보다 명시적인 맵 생성은
         *   - hashMapOf()      :  put하는 데이터들이 내부 해쉬값에 따라 특정 규칙없이 출력
         *   - linkedMapOf()    :  put하는 데이터들의 순서가 지켜지는 해쉬맵
         *   - sortedMapOf()    :  put하는 데이터들이 정렬된 해쉬맵
         */

        val cities3 = hashMapOf(
            "B" to "bbb", "C" to "ccc", "A" to "aaa"
        )
            .forEach { println(it) }

        println("--------------------")

        val cities4 = linkedMapOf(
            "B" to "bbb", "C" to "ccc", "A" to "aaa"
        )
            .forEach { println(it) }

        println("--------------------")

        val cities5 = sortedMapOf(
            "B" to "bbb", "C" to "ccc", "A" to "aaa"
        )
            .forEach { println(it) }
    }


    @Test
    fun 집합생성함수() {

        val cities1 = setOf(
            "seoul", "inchoen", "tokyo", "osaka", "seoul"
        )
            .forEach { println(it) }

        /**
         *   수정가능한 set은 mutableSetOf()
         *   map과 마찬가지로 hashSetOf(), linkedSetOf(), sortedSetOf() 존재
         */

        println("--------------------")

        val cities2 = sortedSetOf(
            "seoul", "inchoen", "tokyo", "osaka", "seoul"
        )
            .forEach { println(it) }
    }
}