package bogdandonduk.modalstoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import bottomsheet.BottomSheetModalsToolbox
import bottomsheet.configuration.ConfigurationBottomSheetModalBuilder
import bottomsheet.core.anatomy.button.configuration.ConfigurationOptionsButton
import bottomsheet.core.anatomy.button.configuration.option.ConfigurationOption
import bottomsheet.core.anatomy.text.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).run {
            setOnClickListener {
                BottomSheetModalsToolbox.buildConfigurationModal("my_modal")
                    .setTitle {
                        Text(
                            "My Bottom Sheet Modal"
                        )
                    }
                    .setAllConfigurationOptionsButton {
                        mutableListOf<ConfigurationOptionsButton>().apply {
                            add(
                                ConfigurationOptionsButton("Select something: ", currentOptionIdProviderAction = { 1 }, configurationOptions = mutableListOf<ConfigurationOption>().apply {
                                    add(ConfigurationOption(0, "Thing 1") { true })
                                    add(ConfigurationOption(1, "Thing 2", icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_overflow_menu, null)) { true })
                                    add(ConfigurationOption(2, "Thing 3") { true })
                                })
                            )
                        }
                    }
                    .show(supportFragmentManager)
            }
        }
    }
}