package com.example.startkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class TabLayoutPagerSimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_pager_simple)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("1"))
        tabLayout.addTab(tabLayout.newTab().setText("2"))
        tabLayout.addTab(tabLayout.newTab().setText("3"))

        viewPager.adapter = MyViewPagerAdapter(layoutInflater, 3)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}

class MyViewPagerAdapter(
    val layoutInflater: LayoutInflater,
    private val tabCount: Int
) : PagerAdapter() {
    override fun getCount(): Int {
        return tabCount
    }

    // 전달된 View가 instantiateItem에서 생성한 View인지 확인
    // === 연산자를 통해 주소값을 비교하여 확인(같은 객체인가)
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    // PagerAdapter는 Implement 이외에도 추가적으로 아래 2가지를 상속받아야 동작
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    // 기존의 createFragment가 Fragment를 만들어 리턴하는 것처럼 보여줄 대상을 리턴
    // 차이점은 Any로써 어떤것이든 반환할 수 있음 -> Fragment를 반환하지 않아도 됨
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when (position) {
            0 ->  {
                val myView = layoutInflater.inflate(R.layout.one_fragment, container, false)
                container.addView(myView)
                return myView
            }
            1 ->  {
                val myView = layoutInflater.inflate(R.layout.activity_android_hw2, container, false)
                container.addView(myView)
                return myView
            }
            else ->  {
                val myView = layoutInflater.inflate(R.layout.one_fragment, container, false)
                container.addView(myView)
                return myView
            }
        }
    }
}