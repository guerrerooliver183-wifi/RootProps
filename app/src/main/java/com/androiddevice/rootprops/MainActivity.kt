package com.androiddevice.rootprops

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddevice.rootprops.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding) { "Activity has been destroyed" }
    private var isEnglish = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.app_name)
        renderLanguage()
        binding.languageButton.setOnClickListener {
            isEnglish = !isEnglish
            renderLanguage()
        }
    }

    private fun renderLanguage() {
        binding.languageButton.text = if (isEnglish) "ES" else "EN"
        binding.welcomeTitle.text = getString(if (isEnglish) R.string.welcome_title_en else R.string.welcome_title)
        binding.welcomeSubtitle.text = getString(if (isEnglish) R.string.welcome_subtitle_en else R.string.welcome_subtitle)
        binding.rootStatusTitle.text = getString(if (isEnglish) R.string.root_status_title_en else R.string.root_status_title)
        binding.rootStatus.text = getString(
            when {
                hasSuBinary() && isEnglish -> R.string.root_binary_found_en
                hasSuBinary() -> R.string.root_binary_found
                isEnglish -> R.string.root_not_detected_en
                else -> R.string.root_not_detected
            }
        )
        binding.rootStatusDescription.text = getString(if (isEnglish) R.string.root_status_description_en else R.string.root_status_description)
        binding.safetyTitle.text = getString(if (isEnglish) R.string.safety_title_en else R.string.safety_title)
        binding.systemWarning.text = getString(if (isEnglish) R.string.system_warning_en else R.string.system_warning)
        binding.toolsTitle.text = getString(if (isEnglish) R.string.tools_title_en else R.string.tools_title)
        binding.featureStatus.text = getString(if (isEnglish) R.string.features_disabled_en else R.string.features_disabled)
        binding.footerNote.text = getString(if (isEnglish) R.string.footer_note_en else R.string.footer_note)
    }

    // This is only a passive file-presence check; it does not request root or run commands.
    private fun hasSuBinary(): Boolean {
        return listOf("/system/bin/su", "/system/xbin/su", "/sbin/su", "/vendor/bin/su")
            .any { File(it).exists() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
