package com.example.omera.oppdeneme

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.country_fragment.view.*
import retrofit2.Call
import retrofit2.Response


class CountryFragment : Fragment() {
    val ctx=activity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.country_fragment, container, false)

        v.toolbar3.setNavigationIcon(R.drawable.ic_stat_name)
        v.toolbar3.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        getCountryList(v)

        return v
    }

    private fun getCountryList(v: View) {

        RetroClient.getClient().create(CountryServices::class.java)
            .getCountry(arguments?.getString("countryName").toString())
            .enqueue(object : retrofit2.Callback<List<Results>> {
                override fun onFailure(call: Call<List<Results>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<List<Results>>, response: Response<List<Results>>) {

                    val repoList: List<Results> = response.body()!!
                    repoList.forEach {
                        //v.txt_country_name.text = it.name
                        v.txt_country_alpha2.text = it.alpha2Code
                        v.txt_country_alpha3.text = it.alpha2Code
                        v.txt_country_calling.text = it.callingCodes[0]
                        v.txt_country_capital.text = it.capital
                        v.txt_country_region.text = it.region
                        v.txt_country_population.text = it.population
                        // v.txt_country_latlng.text=it.latlng.latitude+"-"+it.latlng.longitude
                        v.txt_country_area.text = it.area
                        Utils.fetchSvg(activity?.applicationContext!!,it.flag, v.img_country_flag as ImageView)

                    }
                }

            })
    }

}
