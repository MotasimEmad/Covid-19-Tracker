package com.example.covid19


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.covid19.fragments.InfomationFragment
import com.example.covid19.fragments.MoreFragment
import com.example.covid19.fragments.NumberFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mActionBar: ActionBar = supportActionBar!!
        mActionBar.hide()

        MoewMenu()
    }

    private fun MoewMenu() {
        setFragment(NumberFragment.newIntance())
        BottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_med))
        BottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_timeline))
        BottomNavigation.add(MeowBottomNavigation.Model(3,R.drawable.ic_info))

        BottomNavigation.setOnClickMenuListener {
            when(it.id) {
                2 -> setFragment(NumberFragment.newIntance())
                3 -> setFragment(InfomationFragment.newIntance())
                1 -> setFragment(MoreFragment.newIntance())

                else -> " "
            }
        }

        BottomNavigation.show(2)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameLayout, fragment, "main")
            .commit()
    }


}
