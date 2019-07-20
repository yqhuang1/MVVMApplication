package com.example.mvvmapplication.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.example.mvvmapplication.GlideApp;
import com.example.mvvmapplication.utils.GlideCircleTransform;

/**
 * 类说明
 * 图片绑定显示
 *
 * @author renjialiang
 * @version [版本]
 * @see [相关类]
 * @since [模块]
 */
public class ImageBindingAdapter {

    /**
     * 网络下载式
     *  img:error="@{@drawable/avatar1}"
     *  img:imgUrl="@{myInfoViewModel.imageUrl}"
     *  img:placeholder="@{@color/color_place_holder}"
     *
     *     @BindingAdapter({"img:imgUrl", "img:placeholder", "img:error"})
     **/
    @BindingAdapter({"imgUrl", "placeholder", "error"})
    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable,
                                 Drawable errorDrawable) {
        if (url == null) {
            url = "";
        }
        GlideApp.with(imageView.getContext())
                .load(Uri.parse(url))
                .placeholder(holderDrawable)
                .error(errorDrawable)
                .fitCenter()
                .transform(new GlideCircleTransform())
                .into(imageView);
    }

    /**
     * 本地加载式
     *  img:src="@{meViewModel.mHeadImgResId}
     *
     *     @BindingAdapter("img:src")
     **/
    @BindingAdapter("src")
    public static void setSrc(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }
}
