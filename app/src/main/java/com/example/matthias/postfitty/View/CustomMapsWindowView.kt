package com.example.matthias.postfitty.View

import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import android.app.Activity
import com.example.matthias.postfitty.R


class CustomMapsWindowView : GoogleMap.InfoWindowAdapter {

    private var context: Activity

    constructor(context: Activity) {
        this.context = context
    }

    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    override fun getInfoContents(marker: Marker?): View {

        return context.layoutInflater.inflate(R.layout.view_custom_maps_window, null)
    }
}