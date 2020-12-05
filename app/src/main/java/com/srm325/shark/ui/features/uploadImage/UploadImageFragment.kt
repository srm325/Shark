package com.srm325.shark.ui.features.uploadImage

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.srm325.google.zxing.integration.android.IntentIntegrator
import com.srm325.shark.R


class UploadImageFragment : Fragment() {
    private var scanBtn: Button? = null
    private val formatTxt: TextView? = null
    private  var contentTxt:TextView? = null
    private  var recyclableTxt:TextView? = null
    private  var type:TextView? = null
    companion object{
        const val GET_FROM_GALLERY = 3
    }
    var bitmap: Bitmap? = null
    lateinit var img: ImageView
    lateinit var selectedImage:Uri
    lateinit var storage: FirebaseStorage
    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.uploadimage_layout, container, false)
        val scanBtn: MaterialButton = view.findViewById(R.id.scan_button)
        val contentTxt:TextView = view.findViewById(R.id.scan_content)
        val recyclableTxt:TextView = view.findViewById(R.id.recyclable)
        val type:TextView = view.findViewById(R.id.type)
        scanBtn.setOnClickListener {
            var scanIntegrator : IntentIntegrator ? = IntentIntegrator(activity)
            if (scanIntegrator != null) {
                scanIntegrator.initiateScan()
            }
        }
        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        val scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent)
        if (scanningResult != null) {
            val scanContent = scanningResult.contents
            val scanFormat = scanningResult.formatName
            formatTxt!!.text = "Barcode Format: $scanFormat"
            contentTxt!!.text = "Barcode: $scanContent"
            recyclableTxt!!.text = "Recyclable"
            type!!.text = "Type: Plastic0"
        } else {
            val toast = Toast.makeText(
                context,
                "No scan data received!", Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }


}