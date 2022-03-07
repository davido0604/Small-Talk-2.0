package com.example.smalltalk20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.FrameLayout
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {

    lateinit var fragmentContainer: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragmentContainerView)

        var sharedPreferences = getPreferences(MODE_PRIVATE)
        var login = sharedPreferences.getBoolean(loginKey, false)

        if (login) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<MainChatFragment>(R.id.fragmentContainerView)
            }
        } else {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<LoginFragment>(R.id.fragmentContainerView)
            }

        }
    }
}