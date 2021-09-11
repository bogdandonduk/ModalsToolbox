package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.text

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

class Text(
    var text: String,
    @ColorInt var textColor: Int? = null,

    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null
)