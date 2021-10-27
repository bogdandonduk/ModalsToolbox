package bottomsheet.core.anatomy.button.configuration.option

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

class ConfigurationOption(
    var id: Int,
    var text: CharSequence = "Option",
    @ColorInt var textColor: Int? = null,
    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null,
    var iconContentDescription: String? = null,
    var executeSelectedActionImmediately: Boolean = false,
    var executeSelectedActionAgainWhenNotChanged: Boolean = false,
    var selectedAction: () -> Unit
)