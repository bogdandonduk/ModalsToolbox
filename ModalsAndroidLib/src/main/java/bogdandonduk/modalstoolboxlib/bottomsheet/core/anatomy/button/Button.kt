package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.button

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Button(
    var text: String,
    @ColorInt var textColor: Int? = null,

    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null,

    var onClickAction: ((view: View, modal: BottomSheetDialogFragment) -> Unit)? = null
)