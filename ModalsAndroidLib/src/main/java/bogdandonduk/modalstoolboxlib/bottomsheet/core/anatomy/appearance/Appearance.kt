package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.appearance

import android.graphics.Color
import androidx.annotation.ColorInt

class Appearance(
    @ColorInt var backgroundColor: Int = Color.WHITE,

    var strokeWidth: Int = 0,
    @ColorInt var strokeColor: Int = Color.DKGRAY,

    @ColorInt var dividerLinesColor: Int = Color.BLACK,

    @ColorInt var genericTextColor: Int = Color.BLACK,
    @ColorInt var genericButtonTextColor: Int? = null,
    @ColorInt var genericIconTintColor: Int? = null,

    var cornerRadiusTopLeftPx: Int = 50,
    var cornerRadiusTopRightPx: Int = 50,
    var cornerRadiusBottomRightPx: Int = 50,
    var cornerRadiusBottomLeftPx: Int = 50
)