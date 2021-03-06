package com.example.blogapp.ui.camera

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.blogapp.R
import com.example.blogapp.data.remote.auth.AuthDataSource
import com.example.blogapp.databinding.FragmentCameraBinding
import com.example.blogapp.databinding.FragmentSetupProfileBinding
import com.example.blogapp.domain.home.auth.AuthRepoImpl
import com.example.blogapp.presentation.auth.AuthViewModel


class CameraFragment : Fragment(R.layout.fragment_camera) {
    private val REQUEST_IMAGE_CAPTURE = 2
    private lateinit var binding: FragmentCameraBinding

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding = FragmentCameraBinding.bind(view)
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "no se encontro ninguna app para abrir camara",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

            override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    binding.imgAddPhoto.setImageBitmap(imageBitmap)
                }
    }
}

