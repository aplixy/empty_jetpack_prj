package com.zhiyuan.jetpackdemo.app.weight.banner

/**
 * 作者　: hegaojian
 * 时间　: 2020/2/20
 * 描述　:
 */

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.zhiyuan.jetpackdemo.R
import com.zhiyuan.jetpackdemo.data.model.bean.BannerResponse
import com.zhpan.bannerview.holder.ViewHolder

class HomeBannerViewHolder : ViewHolder<BannerResponse> {
    override fun getLayoutId(): Int {
        return R.layout.banner_itemhome
    }

    override fun onBind(itemView: View, data: BannerResponse?, position: Int, size: Int) {
        val img = itemView.findViewById<ImageView>(R.id.bannerhome_img)
        data?.let {
            Glide.with(img.context.applicationContext)
                .load(it.imagePath)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(img)
        }

    }

}
