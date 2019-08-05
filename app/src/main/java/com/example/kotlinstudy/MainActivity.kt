package com.example.kotlinstudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // run 사용예제 (객체에서 run 함수 호출하는 경우)
        savedInstanceState?.run {
            // Bundle 내에 저장된 값 추출
            val selection = getInt("last_selection")
            val text = getString("last_text")

            // UI 복원 수행
        }

        setContentView(R.layout.activity_main)
    }
}
