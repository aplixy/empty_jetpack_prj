package com.zhiyuan.jetpackdemo.ui.fragment.login

import android.os.Bundle
import android.widget.CompoundButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zhiyuan.jetpackdemo.R
import com.zhiyuan.jetpackdemo.app.ext.hideSoftKeyboard
import com.zhiyuan.jetpackdemo.app.ext.initClose
import com.zhiyuan.jetpackdemo.app.ext.showMessage
import com.zhiyuan.jetpackdemo.app.util.CacheUtil
import com.zhiyuan.jetpackdemo.app.util.SettingUtil
import com.zhiyuan.jetpackdemo.databinding.FragmentLoginBinding
import com.zhiyuan.jetpackdemo.viewmodel.rquest.RequestLoginRegisterViewModel
import com.zhiyuan.jetpackdemo.viewmodel.state.LoginRegisterViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.parseState

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 登录页面
 */
class LoginFragment : BaseFragment<LoginRegisterViewModel, FragmentLoginBinding>() {

    /** */
    private val requestLoginRegisterViewModel: RequestLoginRegisterViewModel by viewModels()


    override fun layoutId() = R.layout.fragment_login

    override fun initView(savedInstanceState: Bundle?) {

        mDatabind.viewmodel = mViewModel
        mDatabind.click = ProxyClick()

        toolbar.initClose("登录") {
            nav().navigateUp()
        }
//        //设置颜色跟主题颜色一致
//        shareViewModel.appColor.value.let {
//            SettingUtil.setShapColor(loginSub, it)
//            loginGoregister.setTextColor(it)
//            toolbar.setBackgroundColor(it)
//        }
    }

    override fun createObserver() {
        requestLoginRegisterViewModel.loginResult.observe(viewLifecycleOwner,Observer { resultState ->
                parseState(resultState, {
                    //登录成功 通知账户数据发生改变鸟
                    CacheUtil.setUser(it)
                    CacheUtil.setIsLogin(true)
                    shareViewModel.userinfo.postValue(it)
                    nav().navigateUp()
                }, {
                    //登录失败
                    showMessage(it.errorMsg)
                })
            })
    }

    inner class ProxyClick {

        fun clear() {
            mViewModel.username.set("")
        }

        fun login() {
            when {
                mViewModel.username.get().isEmpty() -> showMessage("请填写账号")
                mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
                else -> requestLoginRegisterViewModel.loginReq(
                    mViewModel.username.get(),
                    mViewModel.password.get()
                )
            }
        }

        fun goRegister() {
            hideSoftKeyboard(activity)
            //nav().navigate(R.id.action_loginFragment_to_registerFrgment)
        }

        var onCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                mViewModel.isShowPwd.set(isChecked)
            }
    }
}