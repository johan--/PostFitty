package com.example.matthias.postfitty.View

import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import android.app.Activity
import android.widget.TextView
import com.example.matthias.postfitty.R


class CustomMapsInfoWindowAdapter : GoogleMap.InfoWindowAdapter {

    private var context: Activity

    constructor(context: Activity) {
        this.context = context
    }


    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    override fun getInfoContents(marker: Marker?): View {
        val view = context.layoutInflater.inflate(R.layout.view_custom_maps_window, null)

        val tvTitle = view.findViewById(R.id.tv_title) as TextView
        val tvSubTitle = view.findViewById(R.id.tv_subtitle) as TextView

        tvTitle.text = marker.getTitle()
        tvSubTitle.text = marker.getSnippet()

        return view
    }
}