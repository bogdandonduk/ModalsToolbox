package bottomsheet.core.anatomy.button.configuration

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import bottomsheet.core.anatomy.button.configuration.option.ConfigurationOption

class ConfigurationOptionsButton(
    var text: CharSequence,
    @ColorInt var textColor: Int? = null,

    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null,

    @ColorInt var strokeColor: Int? = null,
    var strokeWidth: Int? = null,

    var cornerRadiusTopLeftPx: Int = 8,
    var cornerRadiusTopRightPx: Int = 8,
    var cornerRadiusBottomRightPx: Int = 8,
    var cornerRadiusBottomLeftPx: Int = 8,

    var configurationOptions: MutableList<ConfigurationOption> = mutableListOf(),

    var currentOptionIdProviderAction: () -> Int,
) {
    var currentNonPersistentOptionId: Int? = null
}