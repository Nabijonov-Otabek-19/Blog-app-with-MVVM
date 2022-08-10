package uz.nabijonov.otabek.blogapp.api.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import uz.nabijonov.otabek.blogapp.api.BaseResponse
import uz.nabijonov.otabek.blogapp.api.NetworkManager
import uz.nabijonov.otabek.blogapp.model.PostModel
import uz.nabijonov.otabek.blogapp.model.UserModel

class BlogRepository {

    val compositeDisposable = CompositeDisposable()
    fun getUsers(
        error: MutableLiveData<String>,
        progress: MutableLiveData<Boolean>,
        success: MutableLiveData<List<UserModel>>
    ) {
        progress.value = true
        compositeDisposable.add(
            NetworkManager.getApiService().getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<UserModel>>>() {
                    override fun onNext(value: BaseResponse<List<UserModel>>?) {
                        progress.value = false
                        if (value != null) {
                            success.value = value.data
                        }
                    }

                    override fun onError(e: Throwable?) {
                        progress.value = false
                        error.value = e?.localizedMessage
                    }

                    override fun onComplete() {

                    }
                })
        )
    }

    fun getPosts(error: MutableLiveData<String>, success: MutableLiveData<List<PostModel>>) {
        compositeDisposable.add(
            NetworkManager.getApiService().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<PostModel>>>() {
                    override fun onNext(value: BaseResponse<List<PostModel>>?) {
                        if (value != null) {
                            success.value = value.data
                        }
                    }

                    override fun onError(e: Throwable?) {
                        error.value = e?.localizedMessage
                    }

                    override fun onComplete() {

                    }
                })
        )
    }
}