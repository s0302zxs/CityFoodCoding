package com.giles.wealthparkcoding.ui.home.common

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.giles.wealthparkcoding.ui.common.GlideApp
import timber.log.Timber

object CommonBindings {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean?) {
        Timber.v("showHide show = %s", show)
        view.visibility = if (show == true) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageFromUrl","leftRoundedImage", requireAll = false)
    fun bindImageFromUrl(view: ImageView, imageUrl: String?, isLeftRounded : Boolean = false) {
        if (imageUrl.isNullOrEmpty()) return
        GlideApp.with(view.context)
            .load(imageUrl)
            .apply {
                if(isLeftRounded){
                    apply(RequestOptions().transform(CenterCrop(), GranularRoundedCorners(12.0F,0F,0F,12.0F)))
                }else{
                    centerCrop()
                }
                into(view)
//                into(object : CustomViewTarget<ImageView, Drawable>(view) {
//                    override fun onLoadFailed(errorDrawable: Drawable?) {
//                        Timber.e("load original size image failed")
//                    }
//
//                    override fun onResourceCleared(placeholder: Drawable?) {
//                        // implement if needed
//                    }
//
//                    override fun onResourceReady(
//                        resource: Drawable,
//                        transition: Transition<in Drawable>?
//                    ) {
//                        view.setImageDrawable(resource)
//                    }
//                })
//                else into(view)
            }
    }

}