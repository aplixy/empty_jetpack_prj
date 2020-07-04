package me.hgj.jetpackmvvm.demo.app.weight.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.zhiyuan.jetpackdemo.R


class LoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

}