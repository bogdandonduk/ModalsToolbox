package bottomsheet.core.anatomy.button

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Button(
    var text: CharSequence,
    @ColorInt var textColor: Int? = null,

    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null,

    var onClickAction: ((view: View, modal: BottomSheetDialogFragment) -> Unit)? = null
)