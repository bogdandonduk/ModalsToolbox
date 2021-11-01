package bottomsheet.configuration

import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnAttach
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.modalstoolboxlib.databinding.LayoutConfigurationBottomSheetModalItemBinding
import bogdandonduk.modalstoolboxlib.databinding.LayoutSimpleBottomSheetModalTextItemBinding
import bogdandonduk.viewdatabindingwrapperslib.ViewBindingHandler
import bottomsheet.core.anatomy.button.configuration.ConfigurationOptionsButton
import bottomsheet.core.anatomy.text.Text
import top.defaults.drawabletoolbox.DrawableBuilder

internal class ConfigurationBottomSheetModalAdapter(
    var title: Text,
    var configurationOptionsButtons: MutableList<ConfigurationOptionsButton>,
    var hostActivity: FragmentActivity,
    var hostFragment: ConfigurationBottomSheetModal,
    var model: ConfigurationBottomSheetModalModel,
    var touchInterceptor: View
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolder(override var viewBinding: LayoutConfigurationBottomSheetModalItemBinding) : RecyclerView.ViewHolder(viewBinding.root), ViewBindingHandler<LayoutConfigurationBottomSheetModalItemBinding> {
        lateinit var item: ConfigurationOptionsButton

        init {
            viewBinding.root.run {
                setOnTouchListener { _, event ->
                    touchInterceptor.dispatchTouchEvent(event)

                    false
                }

                setOnLongClickListener {
                    if(configurationOptionsButtons.isNotEmpty())
                        CommonToolbox.vibrateOneShot(hostActivity, 50)

                    true
                }
            }
        }
    }

    override fun getItemViewType(position: Int) = if(position != 0) 0 else 1

     inner class TitleViewHolder(override var viewBinding: LayoutSimpleBottomSheetModalTextItemBinding) : RecyclerView.ViewHolder(viewBinding.root), ViewBindingHandler<LayoutSimpleBottomSheetModalTextItemBinding> {
        lateinit var item: Text

        init {
            viewBinding.root.run {
                setOnTouchListener { _, _ ->
                    hostFragment.dismissPopupMenus()

                    false
                }

                setOnTouchListener { _, event ->
                    touchInterceptor.dispatchTouchEvent(event)

                    false
                }

                setOnLongClickListener {
                    if(configurationOptionsButtons.isNotEmpty())
                        CommonToolbox.vibrateOneShot(hostActivity, 50)

                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder =
        if(viewType != 1)
            ViewHolder(LayoutConfigurationBottomSheetModalItemBinding.inflate(hostActivity.layoutInflater, parent, false))
        else
            TitleViewHolder(LayoutSimpleBottomSheetModalTextItemBinding.inflate(hostActivity.layoutInflater, parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder)
            holder.run {
                item = configurationOptionsButtons[position - 1]

                viewBinding.run {
                    if(item.icon != null) {
                        layoutConfigurationBottomSheetModalItemIconContainerConstraintLayout.visibility = View.VISIBLE

                        layoutConfigurationBottomSheetModalItemIconImageView.run {
                            layoutParams = if(position == 0)
                                ConstraintLayout.LayoutParams(80, 80)
                            else
                                ConstraintLayout.LayoutParams(60, 60)

                            setImageDrawable(item.icon)
                        }
                    } else
                        layoutConfigurationBottomSheetModalItemIconContainerConstraintLayout.visibility = View.GONE

                    layoutConfigurationBottomSheetModalItemHintTextView.run {
                        setTextColor(item.textColor ?: model.appearance.genericTextColor)

                        text = item.text
                    }

                    layoutConfigurationBottomSheetModalItemSelectedConfigurationOptionContainerConstraintLayout.run {
                        background =
                            DrawableBuilder()
                                .ripple()
                                .rippleColor(CommonToolbox.getRippleColorByLuminance(hostActivity, model.appearance.backgroundColor))
                                .strokeColor(item.strokeColor ?: model.appearance.genericContentStrokeColor)
                                .strokeWidth(item.strokeWidth ?: model.appearance.genericContentStrokeWidth)
                                .cornerRadii(item.cornerRadiusTopLeftPx, item.cornerRadiusTopRightPx, item.cornerRadiusBottomRightPx, item.cornerRadiusBottomLeftPx)
                                .build()

                        setOnClickListener {
                            (model.modal as ConfigurationBottomSheetModal).run {
                                getInitializedViewModel(viewModelStore).currentOpenPopupMenuAnchorViewListPosition = position

                                buildConfigurationPopupMenu(item, it, position, model).show(hostActivity, it)
                            }
                        }
                    }

                    val currentOption = item.configurationOptions[item.currentNonPersistentOptionId ?: item.currentOptionIdProviderAction()]

                    layoutConfigurationBottomSheetModalItemOptionTextTextView.run {
                        setTextColor(currentOption.textColor ?: item.textColor ?: model.appearance.genericTextColor)

                        text = currentOption.text
                    }

                    if(currentOption.icon != null) {
                        layoutConfigurationBottomSheetModalItemIconContainerConstraintLayout.visibility = View.VISIBLE

                        layoutConfigurationBottomSheetModalItemIconImageView.run {
                            setImageDrawable(currentOption.icon!!)

                            if(currentOption.iconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, currentOption.iconTintColor!!)
                        }
                    }

                    layoutConfigurationBottomSheetModalItemSelectedConfigurationOptionContainerConstraintLayout.doOnAttach {
                        try {
                            (model.modal as ConfigurationBottomSheetModal).run {
                                getInitializedViewModel(viewModelStore).run {
                                    if(position == currentOpenPopupMenuAnchorViewListPosition)
                                        buildConfigurationPopupMenu(item, it, position, model as ConfigurationBottomSheetModalModel).`continue`(hostActivity, layoutConfigurationBottomSheetModalItemSelectedConfigurationOptionContainerConstraintLayout)
                                }
                            }
                        } catch(thr: Throwable) {  }
                    }

                    if(item.note != null) {
                        layoutConfigurationBottomSheetModalItemNoteTextView.run {
                            visibility = View.VISIBLE

                            setTextColor(item.note!!.textColor ?: model.appearance.genericTextColor)

                            text = item.note!!.text
                        }
                    } else
                        layoutConfigurationBottomSheetModalItemNoteTextView.visibility = View.GONE
                }
            }
        else if(holder is TitleViewHolder)
            holder.run {
                item = title

                viewBinding.run {
                    if(item.icon != null) {
                        layoutSimpleBottomSheetModalItemIconContainerConstraintLayout.visibility = View.VISIBLE

                        layoutSimpleBottomSheetModalItemIconImageView.run {
                            layoutParams = if(position == 0)
                                ConstraintLayout.LayoutParams(80, 80)
                            else
                                ConstraintLayout.LayoutParams(60, 60)

                            setImageDrawable(item.icon)
                        }
                    } else
                        layoutSimpleBottomSheetModalItemIconContainerConstraintLayout.visibility = View.GONE

                    layoutSimpleBottomSheetModalItemTextTextView.run {
                        textSize = 20f
                        setTextColor(item.textColor ?: model.appearance.genericTextColor)
                        setTypeface(null, Typeface.BOLD)

                        text = item.text
                    }
                }
            }
    }

    override fun getItemCount() = configurationOptionsButtons.size + 1
}