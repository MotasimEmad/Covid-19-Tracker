package com.example.covid19.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.covid19.JsonCovidApi
import com.example.covid19.R
import com.example.covid19.Sudan
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NumberFragment: Fragment() {

    lateinit var mRequestQueue: RequestQueue

    lateinit var mContext: Context

    lateinit var NewConfirmedTotal: TextView
    lateinit var TotalConfirmedTotal: TextView
    lateinit var RecoveredConfirmedSituation: TextView

    lateinit var SudanConfirmedSituationTotal: TextView
    lateinit var SudanActiveSituationTotal: TextView
    lateinit var SudanRecoveredSituationTotal: TextView
    lateinit var SudanDeathSituationTotal: TextView

    lateinit var mJsonCovidApi: JsonCovidApi

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.number_fragment, container, false)

        mContext = context!!

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/total/country/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mJsonCovidApi = retrofit.create(JsonCovidApi::class.java)

        NewConfirmedTotal = view.findViewById(R.id.NewConfirmedSituationTotal) as TextView
        TotalConfirmedTotal = view.findViewById(R.id.ConfirmedSituationTotal) as TextView
        RecoveredConfirmedSituation = view.findViewById(R.id.RecoveredConfirmedSituationTotal) as TextView

        SudanConfirmedSituationTotal  = view.findViewById(R.id.SudanConfirmedSituationTotal) as TextView
        SudanActiveSituationTotal  = view.findViewById(R.id.SudanActiveSituationTotal) as TextView
        SudanRecoveredSituationTotal  = view.findViewById(R.id.SudanRecoveredSituationTotal) as TextView
        SudanDeathSituationTotal = view.findViewById(R.id.SudanDeathSituationTotal) as TextView

        loadGlobal()
        GetSudan()

        return view
    }

    companion object {
        fun newIntance(): NumberFragment {
            return NumberFragment()
        }
    }

        private fun loadGlobal() {
        val Global_URL = "https://api.covid19api.com/summary"
        mRequestQueue = Volley.newRequestQueue(mContext)
        val jsonObjectRequest =
            JsonObjectRequest(Request.Method.GET, Global_URL, null, Response.Listener { jsonObject ->
                try {
                    val jsonArray = jsonObject.getJSONObject("Global")

                    val NewConfirmed = jsonArray.getInt("NewConfirmed")
                    val TotalConfirmed = jsonArray.getInt("TotalConfirmed")
                    val TotalRecovered = jsonArray.getInt("TotalRecovered")

                    NewConfirmedTotal.text = NewConfirmed.toString()
                    TotalConfirmedTotal.text = TotalConfirmed.toString()
                    RecoveredConfirmedSituation.text = TotalRecovered.toString()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }, Response.ErrorListener { })
        mRequestQueue.add(jsonObjectRequest)
    }

    fun GetSudan() {
        val call = mJsonCovidApi.sudanData
        call.enqueue(object : Callback<List<Sudan>> {
            override fun onResponse(
                call: Call<List<Sudan>>,
                response: retrofit2.Response<List<Sudan>>
            ) {
                if (!response.isSuccessful) {

                    return
                }
                val posts = response.body()
                for (post in posts!!) {
                    SudanConfirmedSituationTotal.text = post.confirmed.toString()
                    SudanActiveSituationTotal.text = post.active.toString()
                    SudanRecoveredSituationTotal.text = post.recovered.toString()
                    SudanDeathSituationTotal.text = post.deaths.toString()
                }
            }

            override fun onFailure(call: Call<List<Sudan>>, t: Throwable) {
                Toast.makeText(mContext, "" + t.message ,Toast.LENGTH_LONG).show()
            }
        })
    }

}
