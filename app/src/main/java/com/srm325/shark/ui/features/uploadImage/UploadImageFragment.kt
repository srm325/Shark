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
import com.google.zxing.integration.android.IntentIntegrator
import com.srm325.shark.R
import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


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
    ): View {
        val view: View = inflater.inflate(R.layout.uploadimage_layout, container, false)
        val scanBtn: MaterialButton = view.findViewById(R.id.scan_button)
        contentTxt = view.findViewById(R.id.scan_content)
        recyclableTxt= view.findViewById(R.id.recyclable)
        type = view.findViewById(R.id.type)
        scanBtn.setOnClickListener {
            IntentIntegrator.forSupportFragment(this).initiateScan();

        }
        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        Timber.d("this is getting called")

        super.onActivityResult(requestCode, resultCode, intent)

        val scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent)
        if (scanningResult != null) {
            val scanContent = scanningResult.contents
            val scanFormat = scanningResult.formatName

            Timber.d(scanContent)
            Timber.d(scanFormat)
            readData(scanContent)
            
        } else {
            val toast = Toast.makeText(
                context,
                "No scan data received!", Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    private fun readData(scannedValue :String) {
        Timber.d("CSV in read data method")
        var line: String?
        val reading = InputStreamReader(resources.openRawResource(R.raw.barcodedata))
        val reader = BufferedReader(reading)
        try {
            while (reader.readLine().also { line = it } != null) {
                if(line!=null){
                    Timber.d(line)
                    val code = line!!.split(",")[0]
                    if(code==scannedValue)
                        Toast.makeText(context, "Successful Match", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}