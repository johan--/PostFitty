package com.example.matthias.postfitty.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthias.postfitty.Activity.MainActivity
import com.example.matthias.postfitty.Activity.SplashActivity
import com.example.matthias.postfitty.Model.Post
import com.example.matthias.postfitty.R
import com.example.matthias.postfitty.View.CustomMapsWindowView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment(), OnMapReadyCallback {
    private var posts = SplashActivity.posts

    private var mapView: MapView? = null
    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.map) as MapView?
        mapView?.onCreate(savedInstanceState)
        mapView?.onResume()
        mapView?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (post: Post in posts) {
            mMap.addMarker(MarkerOptions().position(LatLng(post.latitude, post.longitude)).title(post.title))
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(posts[0].latitude, posts[0].longitude), 8f))
    }

    companion object {
        fun newInstance(): MapsFragment {
            return MapsFragment()
        }
    }
}
