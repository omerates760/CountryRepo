package com.example.omera.oppdeneme

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.countries_fragment.view.*
import retrofit2.Call
import retrofit2.Response

class CountriesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.countries_fragment, container, false)

        view.toolbar.setNavigationIcon(R.drawable.ic_stat_name)
        view.toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        getCountriesList(view)
        return view
    }

    private fun getCountriesList(view: View?) {

        RetroClient.getClient().create(CountryServices::class.java).getCountries(arguments?.getString("regionName").toString())
            .enqueue(object : retrofit2.Callback<List<Results>> {
                override fun onFailure(call: Call<List<Results>>, t: Throwable) {

                    Log.e("HATA",t.message)
                }

                override fun onResponse(call: Call<List<Results>>, response: Response<List<Results>>) {

                   // Log.e("ARGS", "" + arguments?.getString("regionName") + "\n")
                    Log.e("CountriesList", "" + response.body())

                    val repoList: List<Results> = response.body()!!



                    view?.rc_view_countries?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false
                    )

                    val adapter = CountriesAdapter(repoList, object : CustomCountriesItemListener {
                        override fun onItemClickCountries(countries: Results, position: Int) {

                            val fragmentCountry:Fragment=CountryFragment()
                            val bundle:Bundle= Bundle()
                            bundle.putString("countryName",countries.name)
                            fragmentCountry.arguments=bundle


                            val manager=activity?.supportFragmentManager
                            val trans=manager?.beginTransaction()
                            trans?.replace(R.id.container,fragmentCountry)
                            trans?.addToBackStack("FragCountry")
                            trans?.commit()
                        }

                    })
                    view?.rc_view_countries?.adapter = adapter


                }

            })

    }


}