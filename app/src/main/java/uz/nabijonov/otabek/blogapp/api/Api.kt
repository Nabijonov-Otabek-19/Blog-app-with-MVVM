package uz.nabijonov.otabek.blogapp.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import uz.nabijonov.otabek.blogapp.model.PostModel
import uz.nabijonov.otabek.blogapp.model.UserModel

interface Api {
    @Headers("app-id:625d46a6582bb8251aada573")
    @GET("user")
    fun getUsers(): Observable<BaseResponse<List<UserModel>>>

    @Headers("app-id:625d46a6582bb8251aada573")
    @GET("post")
    fun getPosts(): Observable<BaseResponse<List<PostModel>>>
}