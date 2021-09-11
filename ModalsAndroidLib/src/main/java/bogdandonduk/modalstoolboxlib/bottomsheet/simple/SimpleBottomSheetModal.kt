package bogdandonduk.modalstoolboxlib.bottomsheet.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.listtoolboxlib.ListToolbox
import bogdandonduk.modalstoolboxlib.bottomsheet.core.base.BaseBottomSheetModal
import bogdandonduk.modalstoolboxlib.databinding.LayoutSimpleBottomSheetModalBinding
import top.defaults.drawabletoolbox.DrawableBuilder

internal class SimpleBottomSheetModal : BaseBottomSheetModal<LayoutSimpleBottomSheetModalBinding, SimpleBottomSheetModalViewModel>(
    { layoutInflater: LayoutInflater, viewGroup: ViewGroup? ->
        LayoutSimpleBottomSheetModalBinding.inflate(layoutInflater, viewGroup, false)
    }
) {
    override var viewModelInitialization = {
        SimpleBottomSheetModalViewModel(tag!!)
    }

    override fun draw(view: View, savedInstanceState: Bundle?) {
        (getInitializedViewModel(viewModelStore).model as SimpleBottomSheetModalModel).run {
            val rippleColor = CommonToolbox.getRippleColorByLuminance(requireActivity(), appearance.backgroundColor)

            /** APPEARANCE */
            viewBinding.layoutSimpleBottomSheetModalContentContainerConstraintLayout.background = DrawableBuilder()
                .solidColor(appearance.backgroundColor)
                .cornerRadii(appearance.cornerRadiusTopLeftPx, appearance.cornerRadiusTopRightPx, appearance.cornerRadiusBottomRightPx, appearance.cornerRadiusBottomLeftPx)
                .strokeWidth(appearance.strokeWidth)
                .strokeColor(appearance.strokeColor)
                .ripple()
                .rippleColor(rippleColor)
                .build()

            if(contextMenu.buttons.isNotEmpty())
                viewBinding.layoutSimpleBottomSheetModalTouchConstraintLayout.let {
                    it.background = DrawableBuilder()
                        .cornerRadii(appearance.cornerRadiusTopLeftPx, appearance.cornerRadiusTopRightPx, appearance.cornerRadiusBottomRightPx, appearance.cornerRadiusBottomLeftPx)
                        .ripple()
                        .rippleColor(rippleColor)
                        .build()

                    it.setOnClickListener {

                    }
                }

            /** TEXT CONTENT */
            ListToolbox.initializeList(
                requireActivity(),
                viewBinding.layoutSimpleBottomSheetModalTextContainerRecyclerView,
                SimpleBottomSheetModalAdapter(
                    title = title,
                    textContent = textContent,
                    hostActivity = requireActivity(),
                    model = this,
                    touchInterceptor = viewBinding.layoutSimpleBottomSheetModalTouchConstraintLayout
                )
            )

            /** POSITIVE BUTTON */
            viewBinding.layoutSimpleBottomSheetModalPositiveButtonTextView.let {
                it.background = DrawableBuilder()
                    .cornerRadius(1000000)
                    .ripple()
                    .rippleColor(rippleColor)
                    .build()

                it.setTextColor(positiveButton.textColor ?: appearance.genericButtonTextColor ?: appearance.genericTextColor)

                it.text = positiveButton.text

                it.setOnClickListener { view ->
                    positiveButton.onClickAction?.invoke(view, this@SimpleBottomSheetModal)
                }

                it.post {
                    if(it.layout.getEllipsisCount(1) > 0)
                        it.setOnLongClickListener {

                            true
                        }
                }
            }

            /** NEGATIVE BUTTON */
            viewBinding.layoutSimpleBottomSheetModalNegativeButtonTextView.let {
                it.background = DrawableBuilder()
                    .cornerRadius(1000000)
                    .ripple()
                    .rippleColor(rippleColor)
                    .build()

                it.setTextColor(positiveButton.textColor ?: appearance.genericButtonTextColor ?: appearance.genericTextColor)

                it.text = negativeButton.text

                it.setOnClickListener { view ->
                    negativeButton.onClickAction?.invoke(view, this@SimpleBottomSheetModal)
                }

                it.post {
                    if(it.layout.getEllipsisCount(1) > 0)
                        it.setOnLongClickListener {

                            true
                        }
                }
            }

            /** OVERFLOW MENU BUTTON */
            if(overflowMenu.buttons.isNotEmpty()) {
                viewBinding.layoutSimpleBottomSheetModalButtonDivider2ContainerConstraintLayout.visibility = View.VISIBLE
                viewBinding.layoutSimpleBottomSheetModalButtonDivider2LinearLayout.setBackgroundColor(appearance.dividerLinesColor)

                if(overflowMenu.overflowIconTintColor != null)
                    CommonToolbox.applyColorFilter(viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonIconImageView.drawable, overflowMenu.overflowIconTintColor!!)
                else if(appearance.genericIconTintColor != null)
                    CommonToolbox.applyColorFilter(viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonIconImageView.drawable, appearance.genericIconTintColor!!)

                viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonContainerConstraintLayout.let {
                    it.visibility = View.VISIBLE

                    it.background = DrawableBuilder()
                        .cornerRadius(1000000)
                        .ripple()
                        .rippleColor(rippleColor)
                        .build()

                    it.setOnClickListener {

                    }

                    it.setOnLongClickListener {
                        true
                    }
                }
            }

            /** BUTTONS' DIVIDER LINE */
            viewBinding.layoutSimpleBottomSheetModalButtonDividerLinearLayout.setBackgroundColor(appearance.dividerLinesColor)
        }
    }
}