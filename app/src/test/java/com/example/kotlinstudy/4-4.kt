package com.example.kotlinstudy

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import org.junit.Test

/**
 *   범위지정 함수
 *   - 특정 객체에 있는 함수를 연속해서 사용하거나, 다른 함수의 인자로 잔달하기 위해 변수를 선언하고 이를 다른곳에 사용하지 않는경우, 유용한 함수들
 */

class `4-4` {

    @Test
    fun let함수() {
        /**
         *   - 이 함수를 호출한 객체를 이어지는 함수 블록의 인자로 전달하고 블록 함수 결과를 반환
         *   - 불필요한 변수 선언 방지 가능 ex) 커스텀뷰 패딩값 설정
         *   - 널값이 아닌 경우 체크한 후 특정 작업 수행
         */

        val nullableMessage : String? = "123"
        nullableMessage?.let {
            println("1. It's not null")
        }

        val nullMessage : String? = null
        nullMessage?.let {
            println("2. It's not null")
        }
    }


    @Test
    fun apply함수() {
        /**
         *   - 함수를 호출한 객체를 이어지는 함수블록의 리시버(receiver)로 전달하고 함수를 호출한 객체를 반환
         *   - 객체 이름을 일일이 명시하지 않아도 해당 객체 내의 프로퍼티나 함수를 직접 호출가능 -> 코드간략화
         *   - ex) 뷰의 레이아웃 속성을 코드로 생성
         */
        val param = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            weight = 1f
            topMargin = 100
            bottomMargin = 100
        }
    }

    @Test
    fun with함수() {
        /**
         *   - 인자로 받은 객체를 이어지는 함수 블록의 리시버로 전달하며 블록 함수의 결과를 반환
         *   - 함수에서 사용할 객체를 매개변수를 통해 받으므로 널값이 아닌 것으로 확인된 객체에 쓰는것을 권장 (안전한 호출 사용 불가)
         */

        fun manipulateView(messageView: TextView) {
            // 인자로받은 messageView의 여러 속성을 변경
            with(messageView) {
                text = "Hello, World"
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
    }


    @Test
    fun run함수() {
        /**
         *   인자가 없는 익명함수처럼 사용하는 형태와 객체에서 호출하는 형태를 제공
         */

        // 익명 함수형태: 복잡한 계산을 위해 여러 임시 변수가 필요한 경우 유용
        val padding = run {
            // 이 블록에서 선언하는 값들은 외부 노출되지 않음
            val defaultPadding = TypedValue.applyDimension(1, 1f, null)
            val extraPadding = TypedValue.applyDimension(1,1f,null)

            // 반환
            defaultPadding + extraPadding
        }

        // 객체에서 호출하는 형태 : with()함수와 유사한 목적 (안전한 호출가능하므로 널 값일 수 있는 객체의 속성이나 함수에 연속접근할때 유용)
        // MainActivity에 예제코드
    }

}