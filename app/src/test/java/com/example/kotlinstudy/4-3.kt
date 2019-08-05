package com.example.kotlinstudy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 *   스트림 함수
 *   - 자바8에서는 리스트나 맵과 같은 컬렉션에 포함된 자료들을 쉽게 다루도록 스트림 기능을 제공
 *   - 코틀린에서는 스트림 대신 이와 유사한 역할을 하는 함수들을 표준 라이브러리에서 제공하며 확장 함수 형태로 제공됨
 *   - 항상 stream() 메서드를 호출해야했던 자바와 달리 컬렉션 객체에서 직접 이러한 함수를 요청할수있어 편리
 */

class `4-3` {

    @Test
    fun 변환_map() {
        val cities = listOf("Seoul", "Tokyo", "Mountain View")

        /**
         *   map : 컬렉션 내 인자를 다른 형태로 변환해줌
         */
        // 도시이름을 대문자로 변환
        cities.map { city ->
            city.toUpperCase()
        }
            .forEach { println(it) }

        println("--------------------")

        // 도시이름을 받아, 이를 각 이름의 문자열 길이로 변환
        cities.map { city ->
            city.length
        }
            .forEach { println("length=$it") }
    }


    @Test
    fun 변환_mapIndexed() {
        val numbers = 0..10

        // 컬렉션 내의 인덱스 값을 사용가능
        numbers.mapIndexed { idx, number ->
            idx * number
        }
            .forEach { println(it) }
    }


    @Test
    fun 변환_mapNotNull() {
        val cities = listOf("Seoul", "Tokyo", "Mountain View")

        // 컬렉션 내 각 인자를 변환함과 동시에, 변환한 결과가 널 값인 경우 이를 무시
        // 도시 이름의 길이가 5 이하이면 그대로 반환, 그렇지 않으면 널 값 반환
        cities.mapNotNull { city ->
            if (city.length <= 5) city else null
        }
            .forEach { println(it) }

        println("--------------------")

        // 그냥 map으로 하면 null이 표시
        cities.map { city ->
            if (city.length <= 5) city else null
        }
            .forEach { print(it) }
    }


    @Test
    fun 변환_flatMap() {
        /**
         *   flatMap : map처럼 컬렉션 내 인자를 다른 형태로 변환해줌. 다른점은 변환함수의 반환형이 Interable
         *   즉 하나의 인자에서 여러 개의 인자로 매핑이 필요한 경우에 사용
         */
        val numbers = 1..6

        // 1부터 시작하여 각 인자를 끝으로 하는 범위를 반환
        numbers.flatMap { number -> 1..number }
            .forEach { print("$it ") }

        println()
        println("--------------------")

        // 그냥 map으로 할경우는 똑같이 IntRange인 형태로만 매핑
        numbers.map { number -> 1..number }
            .forEach { println("$it ") }
    }


    @Test
    fun 변환_groupBy() {
        /**
         *   groupBy : 컬렉션 내 인자들을 지정한 기준에 따라 분류하며 각 인자들의 리스트를 포함하는 맵 형태로 결과 반환
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View")

        // 도시 이름 길이가 5 이하면 "A" 그룹에, 그렇지 않으면 "B" 그룹에 대입
        // 여기서 지정하는 이름은 반환되는 맵의 키 이름으로 사용됨
        cities.groupBy { city ->
            if (city.length <= 5) "A" else "B"
        }
            .forEach { key, cities -> println("key=$key cities=$cities") }
    }


    @Test
    fun 필터_filter() {
        /**
         *   filter() : 컬렉션 내 인자들 중 주어진 조건과 일치하는 인자만 걸러주는 역할
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View")

        // 도시 이름 길이가 5 이하인 항목만 통과시킴
        cities.filter { city ->
            city.length <= 5
        }
            .forEach { println(it) }
    }


    @Test
    fun 필터_take() {
        /**
         *   take() : 컬렉션 내 인자들 중 앞에서 take() 함수의 인자로 받은 개수만큼만을 갖는 리스트 반환
         *   - 응용함수로 takeLast(), takeWhile(), takeLastWhile() 함수 존재
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

        cities.take(2)
            .forEach { println(it) }

        println("--------------------")

        // takeLast : take()와 반대로 뒤에서부터 이 함수의 인자로 받은 개수만큼 인자로 갖는 리스트 반환
        cities.takeLast(2)
            .forEach { println(it) }

        println("--------------------")

        // takeWhile : 첫번째 인자부터 시작하여 주어진 조건을 만족하는 인자까지를 포함하는 리스트 반환
        cities.takeWhile { city ->
            city.length <= 5
        }
            .forEach { println(it) }

        println("--------------------")

        // takeLastWhile : takeWhile의 반대순서
        cities.takeLastWhile { city ->
            city.length < 13
        }
            .forEach { println(it) }
    }


    @Test
    fun 필터_drop() {
        /**
         *   drop() : take()와 반대역할. 조건을 만족하는 항목을 컬렉션에서 제외한 결과를 반환
         *   - 마찬가지로 dropLast(), dropWhile(), dropLastWhile() 함수 존재
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

        cities.drop(2)
            .forEach { println(it) }

        println("--------------------")

        cities.dropLast(2)
            .forEach { println(it) }

        println("--------------------")

        cities.dropWhile { city ->
            city.length <= 5
        }
            .forEach { println(it) }

        println("--------------------")

        cities.dropLastWhile { city ->
            city.length < 13
        }
            .forEach { println(it) }
    }


    @Test
    fun 필터_first() {
        /**
         *   first() : 컬렉션 내 첫번째 인자 반환 -> 특정조건을 만족하는 첫번째 인자를 반환하도록 구성하는 것도 가능
         *   - 조건만족하는 인자가 없는경우 NoSuchElementException 예외 발생
         *   - 대신 firstOrNull() 함수를 사용하면 Null 값도 반환 가능  == find() 함수와 유사
         *
         *   last() : first()와 반대
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

        println(cities.first())

        println("--------------------")

        println(cities.first { city -> city.length > 5 })

        println("--------------------")

        println(cities.last())

        println("--------------------")

        println(cities.last { city -> city.length > 5 })

        println("--------------------")

        try {
            cities.first { city -> city.isEmpty() }
        } catch (e: NoSuchElementException) {
            println("Not Found")
        }

        println("--------------------")

        println(cities.firstOrNull { city -> city.isEmpty() })

        println("--------------------")

        println(cities.lastOrNull { city -> city.isEmpty() })
    }


    @Test
    fun 필터_distinct() {
        /**
         *   distinct() : 컬렉션 내에 포함된 항목 중 중복된 항목을 걸러낸 결과를 반환
         *   distinctBy() : 비교에 사용할 키 값을 직접 설정가능
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "Seoul", "Tokyo")

        cities.distinct()
            .forEach { println(it) }

        println("--------------------")

        // 중복판단을 도시 이름 길이로 기준 (앞에있는 Seoul이 먼저 나오고 뒤의 Tokyo는 중복판단으로 제거됨)
        cities.distinctBy { city -> city.length }
            .forEach { println(it) }
    }


    @Test
    fun 조합및합계_zip() {
        /**
         *   zip() : 두 컬렉션 내의 자료들을 조합하여 새로운 자료를 생성
         *   - 두 컬렉션간 자료 개수가 달랃 사용가능하며 이 경우, 반환되는 컬렉션의 자료수는 더 적은쪽을 따라감
         *   - 기본값으로는 조합된 결과를 Pair로 만들어주며, 원하는 경우 조합 규칙을 사용자가 정의 가능
         */
        val cityCodes = listOf("SEO", "TOK", "MTV", "NYC")
        val cityNames = listOf("Seoul", "Tokyo", "Mountain View")

        // 단순 zip 함수를 호출하는 경우, Pair 형태로 자료 조합
        cityCodes.zip(cityNames)
            .forEach { pair -> println("${pair.first}:${pair.second}") }

        println("--------------------")

        // 조합할 자료의 타입을 조합 함수를 통해 지정하면 해당 형태로 바꿔줌
        cityCodes.zip(cityNames) { code, name -> "$code ($name)" }
            .forEach { println(it) }
    }


    @Test
    fun 조합및합계_joinToString() {
        /**
         *   joinToString() : 컬렉션 내 자료를 문자열 형태로 변환함과 동시에, 이를 조합하여 하나의 문자열로 생성
         *   - 자료를 간단히 직렬화할 때 매우 유용
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

        // 기본설정값을 사용하여 문자열 형태로 조합
        println(cities.joinToString())

        println("--------------------")

        // 구분자로 다른 문자를 사용
        println(cities.joinToString(separator = "|"))

        println("--------------------")

        // 직렬화한 스트링을 다시 파싱하는 법
        println(cities.joinToString().split(",").map { it.trim() })

        println("--------------------")

        // 객체도 되나 테스트  =>  주소값이 문자열로 반환되서 불가능
        val people = listOf(Person("sangjoo", 27), Person("leesangjoo", 26))
        println(people.joinToString())
    }

    class Person(val name: String, val age: Int) {

    }


    @Test
    fun 조합및합계_count() {
        /**
         *   count() : 컬렉션내 자료 개수 반환, 별도의 조건식도 추가가능
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

        // 일반 count()
        assertEquals(cities.count(), 5)

        // 조건식 추가
        assertEquals(cities.count { city -> city.length > 5 }, 2)
    }


    @Test
    fun 조합및합계_reduce() {
        /**
         *   reduce() : 컬렉션 내 자료들을 모두 합쳐 하나의 값으로 만들어주는 역할
         *   - 앞에서의 joinToString() 함수는 reduce() 함수의 일종이라고 볼수 있다
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

        // 아래 reduce 사용은 joinToString와 동일한 형태의 문자열 생성
        // acc에는 지금까지 조합된 결과가, s에는 새로 조합할 자료가 들어감
        println(cities.reduce { acc, s -> "$acc, $s" })

        println("--------------------")

        // reduceRight 함수는 마지막 인자부터 조합
        println(cities.reduceRight { s, acc -> "$acc, $s" })
    }


    @Test
    fun 조합및합계_fold() {
        /**
         *   fold() : reduce() 함수와 거의 동일한 역할을 하나 초기값을 지정가능하다
         *   - reduce()와 마찬가지로 foldRight() 함수 존재
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

        println(cities.fold("Initial") { acc, s -> "$acc, $s" })
    }


    @Test
    fun 기타_any() {
        /**
         *   any() : 컬렉션 내 단 하나의 자료라도 존재하면 true를, 아니면 false 반환
         *   - 인자로 조건식을 전달할 경우, 조건식을 만족하는 자료의 유무 반환
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

        // 일반 any()
        assertEquals(cities.any(), true)

        // 문자열 길이가 5이하 조건식
        assertEquals(cities.any { city -> city.length <= 5 }, true)
    }


    @Test
    fun 기타_none() {
        /**
         *   none() : any() 함수와 반대 작업 수행. 컬렉션이 비어있는지 여부 반환
         */
        val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

        // 일반 none() 동작은 isEmpty()과 동일
        assertEquals(cities.none(), false)

        // 빈 문자열을 가진 자료가 존재하지 않는지 확인  -> 빈문자를 가진 자료가 없으니 true
        assertEquals(cities.none { city -> city.isEmpty() }, true)
    }


    @Test
    fun 기타_maxminaverage() {
        val numbers = listOf(4,2,7,3,2,0)

        // max
        assertEquals(numbers.max(), 7)

        // min
        assertEquals(numbers.min(), 0)

        // average
        assertEquals(numbers.average().toFloat(), 3.0f)
    }
}