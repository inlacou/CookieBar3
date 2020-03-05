package com.inlacou.cookiebarsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.inlacou.cookiebar3.AnimationRoutes
import com.inlacou.cookiebar3.CookieBar.Companion.BOTTOM
import com.inlacou.cookiebar3.CookieBar.Companion.build
import com.inlacou.cookiebar3.CookieBar.Companion.dismiss

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val btnTop = findViewById<Button>(R.id.btn_top)
		btnTop.setOnClickListener {
			build(this@MainActivity)
					.setTitle(R.string.top_cookie_title)
					.setTitleColor(R.color.yellow)
					.setMessage(R.string.top_cookie_message)
					.setIcon(R.drawable.ic_android_white_48dp)
					.setSteps(AnimationRoutes.showFromTop)
					.show()
		}
		val btnBottom = findViewById<Button>(R.id.btn_bottom)
		btnBottom.setOnClickListener {
			build(this@MainActivity)
					.setTitle(R.string.bottom_cookie_title)
					.setIcon(R.mipmap.ic_launcher)
					.setSteps(AnimationRoutes.showFromBottom)
					.setMessage(R.string.bottom_cookie_message)
					.setTitleColor(R.color.yellow)
					.setCookiePosition(BOTTOM)
					.show()
		}
		val btnBottomAnimated = findViewById<Button>(R.id.btn_bottom_animated)
		btnBottomAnimated.setOnClickListener {
			build(this@MainActivity)
					.setTitle(R.string.fancy_cookie_title)
					.setMessage(R.string.fancy_cookie_message)
					.setIcon(R.drawable.ic_settings_white_48dp)
					.setIconAnimation(R.animator.iconspin)
					.setTitleColor(R.color.fancyTitle)
					.setMessageColor(R.color.fancyMessage)
					.setSteps(AnimationRoutes.showFromBottom)
					.setCookiePosition(BOTTOM)
					.show()
		}
		val btnCustomView = findViewById<Button>(R.id.btn_custom_view)
		btnCustomView.setOnClickListener {
			build(this@MainActivity)
					.setCustomView(R.layout.custom_cookie)
					.setTitle("This is a custom cookie")
					.setMessage("Fancy, isn't it?")
					.setIcon(R.drawable.cookiemonster)
					.setCookiePosition(BOTTOM)
					.setSteps(AnimationRoutes.specialExample)
					.setShownListener { Log.d("example_activity", "cookie animation step $it finished") }
					.setDismissListener { Log.d("example_activity", "cookie dismissed") }
					.show()
		}
		findViewById<View>(R.id.activity_main).setOnClickListener { dismiss(this@MainActivity) }
	}
	
}