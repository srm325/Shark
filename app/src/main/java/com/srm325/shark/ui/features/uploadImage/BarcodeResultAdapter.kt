package com.srm325.shark.ui.features.uploadImage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srm325.shark.R
import com.srm325.shark.data.model.BarcodeResult


class BarcodeResultAdapter(var postList: List<BarcodeResult>) : RecyclerView.Adapter<BarcodeViewHolder>() {
    var currentAdminArea : String = "Los Angeles County"
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarcodeViewHolder {

        context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_line, parent, false)
        return BarcodeViewHolder(view)


    }


    override fun onBindViewHolder(holder: BarcodeViewHolder, position: Int) {



    }

    override fun getItemCount() = postList.size



}