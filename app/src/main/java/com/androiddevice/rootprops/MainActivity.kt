package com.androiddevice.rootprops

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddevice.rootprops.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding) { "Activity has been destroyed" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.app_name)
        binding.rootStatus.text = if (hasSuBinary()) {
            getString(R.string.root_binary_found)
        } else {
            getString(R.string.root_not_detected)
        }
        binding.rootStatusDescription.text = getString(R.string.root_status_description)
        binding.systemWarning.text = getString(R.string.system_warning)
        binding.featureStatus.text = getString(R.string.features_disabled)
        binding.languageButton.setOnClickListener {
            val spanish = binding.languageButton.text.toString() == "EN"
            binding.languageButton.text = if (spanish) "ES" else "EN"
            binding.welcomeTitle.text = getString(if (spanish) R.string.welcome_title else R.string.welcome_title_en)
            binding.welcomeSubtitle.text = getString(if (spanish) R.string.welcome_subtitle else R.string.welcome_subtitle_en)
            binding.rootStatusTitle.text = getString(if (spanish) R.string.root_status_title else R.string.root_status_title_en)
            binding.rootStatusDescription.text = getString(if (spanish) R.string.root_status_description else R.string.root_status_description_en)
            binding.systemWarning.text = getString(if (spanish) R.string.system_warning else R.string.system_warning_en)
            binding.featureStatus.text = getString(if (spanish) R.string.features_disabled else R.string.features_disabled_en)
        }
    }

    private fun hasSuBinary(): Boolean {
        return listOf("/system/bin/su", "/system/xbin/su", "/sbin/su", "/vendor/bin/su")
            .any { File(it).exists() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
