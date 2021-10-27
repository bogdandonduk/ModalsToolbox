package bottomsheet.core.anatomy.text

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

class Text(
    var text: CharSequence,
    @ColorInt var textColor: Int? = null,

    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null
)