package android.com.baseapp.ui.drawText

import android.Manifest
import android.app.Activity
import android.com.baseapp.BuildConfig
import android.com.baseapp.R
import android.com.baseapp.databinding.FragmentDrawTextBinding
import android.com.baseapp.ui.base.BaseFragment
import android.com.baseapp.utils.BitmapUtil
import android.com.baseapp.utils.FileUtil
import android.com.baseapp.utils.hasPermission
import android.com.baseapp.utils.setSafeOnClickListener
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class DrawTextFragment : BaseFragment<FragmentDrawTextBinding, DrawTextViewModel>() {

    override val resourceLayoutId = R.layout.fragment_draw_text
    var mOutPutFile: File? = null

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCamera()
            }
        }

    private val startActivityForResultOwner =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

            if (result.resultCode == Activity.RESULT_OK) {

                result.data?.data?.let { uri ->
                    val outputFile =
                        File(
                            FileUtil.getCaptureImageFolder(requireActivity()),
                            "temp.jpg"
                        )
                    val resultSave =
                        FileUtil.saveBitmapFromContentUri(requireContext(), uri, 1920, outputFile)
                    if (resultSave) {
                        BitmapUtil.getBitmapFromFile(outputFile.absolutePath)?.let {
                            Glide.with(requireContext())
                                .load(
                                    BitmapUtil.writeTextToBitmap(
                                        it,
                                        "demo text\ndemo text\ndemo text",
                                        14f
                                    )
                                )
                                .into(binding.imgResult)
                        }
                    }
                } ?: run {
                    if (mOutPutFile != null) {
                        BitmapUtil.getBitmapFromFile(mOutPutFile?.absolutePath ?: "")?.let {
                            Glide.with(requireContext())
                                .load(
                                    BitmapUtil.writeTextToBitmap(
                                        it,
                                        "demo text\ndemo text\ndemo text",
                                        14f
                                    )
                                )
                                .into(binding.imgResult)
                        }
                    }
                }
            }

        }

    override fun onInitView(root: View?) {
        binding.btnCamera.setSafeOnClickListener {
            if (requireActivity().hasPermission(arrayOf(Manifest.permission.CAMERA))) {
                startCamera()
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
        binding.btnGallery.setSafeOnClickListener {
            if (requireActivity().hasPermission(arrayOf(Manifest.permission.CAMERA))) {
                startGallery()
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

    }

    private fun startGallery() {
        val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResultOwner.launch(intentGallery)
    }

    private fun startCamera() {

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        mOutPutFile = File(FileUtil.getCaptureImageFolder(requireContext()), "temp.jpg")

        val outputFileUri: Uri = FileProvider.getUriForFile(
            requireContext(),
            BuildConfig.APPLICATION_ID,
            mOutPutFile!!
        )

        takePictureIntent.putExtra(
            MediaStore.EXTRA_OUTPUT,
            outputFileUri
        )
        // Start the activity with camera_intent, and request pic id
        startActivityForResultOwner.launch(takePictureIntent)
    }

    override fun subscribeUi(viewModel: DrawTextViewModel) {
    }
}