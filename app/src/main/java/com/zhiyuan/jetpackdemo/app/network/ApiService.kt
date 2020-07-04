package me.hgj.jetpackmvvm.demo.app.network

import com.zhiyuan.jetpackdemo.data.model.bean.ApiResponse
import com.zhiyuan.jetpackdemo.data.model.bean.UserInfo
import retrofit2.http.*

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 网络API
 */
interface ApiService {

    companion object {
         const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") username: String, @Field("password") pwd: String): ApiResponse<UserInfo>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String, @Field("password") pwd: String, @Field(
            "repassword"
        ) rpwd: String
    ): ApiResponse<Any>



    /**
     * 添加一个TODO
     */
    @POST("/lg/todo/add/json")
    @FormUrlEncoded
    suspend fun addTodo(@Field("title") title: String,
                @Field("content") content: String,
                @Field("date") date: String,
                @Field("type") type: Int,
                @Field("priority") priority: Int): ApiResponse<Any?>

    /**
     * 修改一个TODO
     */
    @POST("/lg/todo/update/{id}/json")
    @FormUrlEncoded
    suspend fun updateTodo(@Field("title") title: String,
                   @Field("content") content: String,
                   @Field("date") date: String,
                   @Field("type") type: Int,
                   @Field("priority") priority: Int,
                   @Path("id") id: Int): ApiResponse<Any?>

    /**
     * 删除一个TODO
     */
    @POST("/lg/todo/delete/{id}/json")
    suspend fun deleteTodo(@Path("id") id: Int): ApiResponse<Any?>

    /**
     * 完成一个TODO
     */
    @POST("/lg/todo/done/{id}/json")
    @FormUrlEncoded
    suspend fun doneTodo(@Path("id") id: Int, @Field("status") status: Int): ApiResponse<Any?>


}