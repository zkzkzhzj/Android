/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

/**
 * Activity for cupcake order flow.
 */
/*
class MainActivity : AppCompatActivity(R.layout.activity_main)
해당 코드는 아래와 같은 코드 이다.(위 아래 동일)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    // 전역으로 NavController 객체를 둠
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // NavHostFragment 를 가져옴(프래그먼트 매니저 통해서)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // navController 를 가져옴
        navController = navHostFragment.navController

        // 액션바와 함께 navController 를 사용하기 위해서 넘겨줌
        setupActionBarWithNavController(navController)
    }

    // 뒤로가기 버튼 클릭
    override fun onSupportNavigateUp(): Boolean {
        // 뒤로갈 수 있다면 뒤로가도록 구현
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
