package uz.gita.mobilebanking.presentation.ui.screen

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.ResponseBody
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.requests.profile_user.DataUser
import uz.gita.mobilebanking.data.requests.profile_user.RequestProfileEdit
import uz.gita.mobilebanking.databinding.ProfileScreenBinding
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.ProfileScreenViewModelImpl
import uz.gita.mobilebanking.presentation.utils.FileUtils.getPath
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.profile_screen) {
    private val bind by viewBinding(ProfileScreenBinding::bind)
    private var file: File? = null
    private val viewmodel by viewModels<ProfileScreenViewModelImpl>()
    private val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val resultCode = result.resultCode
        val data = result.data
        when (resultCode) {
            Activity.RESULT_OK -> {
                val fileUri = data?.data!!
                bind.imageAccount.setImageURI(fileUri)
                file = File(getPath(requireContext(), fileUri))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.save.setOnClickListener {
            file?.let { it1 -> viewmodel.setAvatar(it1) }
            viewmodel.editProfile(RequestProfileEdit(bind.name.text.toString(), bind.lastName.text.toString(), bind.password.text.toString()))
        }

        bind.name.addTextChangedListener {
            bind.save.isEnabled = it.toString().isEmpty()
        }

        bind.lastName.addTextChangedListener {
            bind.save.isEnabled = it.toString().isEmpty()
        }
        bind.phoneNumber.addTextChangedListener {
            bind.save.isEnabled = it?.length == 13 && it.toString().startsWith("+998")
        }

        bind.password.addTextChangedListener {
            bind.save.isEnabled = it?.length == 8
        }

        bind.imageAccount.setOnClickListener {
            ImagePicker.with(requireActivity())
                .compress(1024)
                .crop()
                .maxResultSize(512, 512)
                .saveDir(File(requireContext().getExternalFilesDir(null)?.let {
                    it.absolutePath
                }, "MyImage"))
                .createIntent {
                    startForProfileImageResult.launch(it)
                }
        }

        viewmodel.getAvatar()
        viewmodel.getInfo()
        viewmodel.getAvatarLiveData.observe(viewLifecycleOwner, observerImage)
        viewmodel.getInfoLiveData.observe(viewLifecycleOwner, observerInfo)
    }

    private val observerImage = Observer<ResponseBody> {
        downloadImage(it)
    }
    private val observerInfo = Observer<DataUser> {
        bind.name.setText(it.firstName)
        bind.lastName.setText(it.lastName)
        bind.phoneNumber.setText(it.phone)
        bind.password.setText(it.password)
    }

    private fun downloadImage(body: ResponseBody): Boolean {
        return try {
            var `in`: InputStream? = null
            var out: FileOutputStream? = null
            try {
                `in` = body.byteStream()
                out = FileOutputStream(requireActivity().getExternalFilesDir(null).toString() + File.separator + "Android.jpg")
                var c: Int
                while (`in`.read().also { c = it } != -1) {
                    out.write(c)
                }
            } catch (e: IOException) {
                return false
            } finally {
                `in`?.close()
                out?.close()
            }
            val width: Int
            val height: Int
            val bMap = BitmapFactory.decodeFile(requireActivity().getExternalFilesDir(null).toString() + File.separator + "Android.jpg")
            width = 2 * bMap.width
            height = 3 * bMap.height
            val bMap2 = Bitmap.createScaledBitmap(bMap, width, height, false)
            bind.imageAccount.setImageBitmap(bMap2)
            true
        } catch (e: IOException) {
            false
        }
    }
}