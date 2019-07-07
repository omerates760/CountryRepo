package com.example.omera.oppdeneme

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.region_fragment.view.*
import retrofit2.Call
import retrofit2.Response

class RegionFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View= inflater.inflate(R.layout.region_fragment,container,false)





        getRegionList(view)


        return view
    }

    private fun getRegionList(view:View) {

        RetroClient.getClient().create(CountryServices::class.java)
            .getRegion().enqueue(object :retrofit2.Callback<List<Results>>{
                override fun onResponse(call: Call<List<Results>>, response: Response<List<Results>>) {

                    val repoList:List<Results> = response.body()!!
                    view.rc_view_region.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.VERTICAL, false)


                    val adapter=RegionAdapter(repoList.distinctBy {
                        it.region
                    },object :CustomRegionItemListener{
                        override fun onItemClickRegion(results: Results, position: Int) {

                            Log.e("RESULT",results.region)

                            val fragmentCountries=CountriesFragment()

                            val bundle= Bundle()
                            bundle.putString("regionName",results.region)
                            fragmentCountries.arguments=bundle

                            val manager=activity?.supportFragmentManager
                            val trans=manager?.beginTransaction()
                            trans?.replace(R.id.container,fragmentCountries)
                            trans?.addToBackStack("FragCountries")
                            trans?.commit()



                        }
                    })
                    view.rc_view_region.adapter=adapter

                }
                override fun onFailure(call: Call<List<Results>>, t: Throwable) {
                    Log.e("result",""+t.message.toString())
                }



            })

    }
}