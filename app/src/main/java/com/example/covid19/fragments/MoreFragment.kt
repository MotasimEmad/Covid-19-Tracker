package com.example.covid19.fragments

import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.covid19.JsonCovidApi
import com.example.covid19.R
import com.example.covid19.Sudan
import com.example.covid19.adapters.AboutAdapter
import com.example.covid19.models.Model
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoreFragment: Fragment() {

    lateinit var mViewPager: ViewPager
    lateinit var mAboutAdapter: AboutAdapter
    lateinit var models: MutableList<Model>
    lateinit var colors: Array<Int>
    var mArgbEvaluator = ArgbEvaluator()

    lateinit var mContext: Context

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.more_fragment, container, false)

        mContext = context!!

        models = ArrayList()
        models.add(Model(R.drawable.ic_undraw_wash_hands_nwl2,"Wash your hands"))
        models.add(Model(R.drawable.ic_undraw_medical_care_movn,"Wear mask & save a life"))
        models.add(Model(R.drawable.ic_undraw_social_distancing_2g0u,"Social distancing"))

        mAboutAdapter = AboutAdapter(models, mContext)
        mViewPager = view.findViewById(R.id.ViewPager)
        mViewPager.adapter = mAboutAdapter
        mViewPager.setPadding(100, 0, 100, 0)

        val Colors_Temp = arrayOf(
            resources.getColor(R.color.colorAdapter1),
            resources.getColor(R.color.colorAdapter2),
            resources.getColor(R.color.colorPrimary)
        )

        colors = Colors_Temp

        mViewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position < mAboutAdapter.count - 1 && position < colors.size - 1) {
                    mViewPager.setBackgroundColor(
                        mArgbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position + 1]
                        ) as Int
                    )
                } else {
                    mViewPager.setBackgroundColor(colors[colors.size - 1])
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        return view
    }

    companion object {
        fun newIntance(): MoreFragment {
            return MoreFragment()
        }
    }

}
