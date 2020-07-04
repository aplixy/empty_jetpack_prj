package com.zhiyuan.jetpackdemo

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ToastUtils
import com.tencent.bugly.beta.Beta
import com.zhiyuan.jetpackdemo.app.util.StatusBarUtil
import com.zhiyuan.jetpackdemo.databinding.MainActivityBinding
import com.zhiyuan.jetpackdemo.ui.main.MainFragment
import com.zhiyuan.jetpackdemo.viewmodel.state.MainViewModel
import me.hgj.jetpackmvvm.demo.app.base.BaseActivity
import me.hgj.jetpackmvvm.network.manager.NetState

class MainActivity : BaseActivity<MainViewModel, MainActivityBinding>() {

    override fun layoutId() = R.layout.main_activity

    override fun initView(savedInstanceState: Bundle?) {
        //进入首页检查更新
        Beta.checkUpgrade(false, true)
    }

    override fun createObserver() {
        shareViewModel.appColor.observe(this, Observer {
            supportActionBar?.setBackgroundDrawable(ColorDrawable(it))
            StatusBarUtil.setColor(this, it, 0)
        })
    }
    /**
     * 示例，在Activity/Fragment中如果想监听网络变化，可重写onNetworkStateChanged该方法
     */
    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        if (netState.isSuccess) {
            Toast.makeText(this,"我特么终于有网了啊!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,"我特么怎么断网了!", Toast.LENGTH_SHORT).show()
        }
    }

    var exitTime = 0L
    override fun onBackPressed() {
        val nav = Navigation.findNavController(this, R.id.host_fragment)
        if (nav.currentDestination != null && nav.currentDestination!!.id != R.id.mainfragment) {
            //如果当前界面不是主页，那么直接调用返回即可
            nav.navigateUp()
        } else {
            //是主页
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtils.showShort("再按一次退出程序")
                exitTime = System.currentTimeMillis()
            } else {
                super.onBackPressed()
            }
        }
    }

}