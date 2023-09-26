package pemberkel9.smartfinance.viewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pemberkel9.smartfinance.pojo.User
import pemberkel9.smartfinance.util.PreferenceUtil
import pemberkel9.smartfinance.util.SessionManager

class AutentikasiViewModel(application: Application) : AndroidViewModel(application) {

    var fullname: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var password : MutableLiveData<String> = MutableLiveData()
    lateinit var listener : onSuccessListener

    fun registerClick(isCheck: Boolean, onSuccessListener: onSuccessListener){

        this.listener = onSuccessListener

        if (!isValidName()){
            toast("Nama Invalid...")
        }else if (!isValidEmail()){
            toast("Email Invalid...")
        }else if (!isValidPassword()){
            toast("Password Invalid...")
        }else if (!isCheck){
            toast("Silahkan ceklis kotak agreement dulu...")
        }else{
            saveUser(onSuccessListener)
        }

    }

    fun loginClick(onSuccessListener: onSuccessListener){

        this.listener = onSuccessListener

        if (!isValidEmail()){
            toast("Email Invalid...")
        }else if (!isValidPassword()){
            toast("Password Invalid...")
        }else{
            checkLogin(listener)
        }

    }

    fun saveUser(onSuccessListener: onSuccessListener){
        val _fullname = fullname.value
        val _email = email.value
        val _password = password.value
        val user = User(_fullname = _fullname, _email = _email, _password = _password)
        user.let { SessionManager.saveProfile(getApplication(), it) }
        listener.onSuccess()
        PreferenceUtil.getEditor(getApplication())?.putBoolean(PreferenceUtil.SETUP_REGISTER, true)?.commit()
    }

    fun checkLogin(onSuccessListener: onSuccessListener){

        val _email = email.value
        val _password = password.value

        if (SessionManager.getProfile(getApplication()) != null){
            val own: User = SessionManager.getProfile(getApplication())!!
            if (_email.equals(own.email) && _password.equals(own.password)){
                onSuccessListener.onSuccess()
                PreferenceUtil.getEditor(getApplication())?.putBoolean(PreferenceUtil.SETUP_LOGIN, true)?.commit()
            }else{
                toast("Password atau email anda salah...")
            }
        }else{
            toast("Anda belum memiliki akun, silahkan register dahulu")
        }

    }

    fun toast(text: String){
        Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show()
    }

    fun isValidName(): Boolean{
        return !TextUtils.isEmpty(fullname.value) && fullname.value.toString().length >= 5
    }

    fun isValidEmail(): Boolean {
        return !TextUtils.isEmpty(email.value) && Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches()
    }

    fun isValidPassword():Boolean{
        return !TextUtils.isEmpty(password.value) && password.value.toString().length >= 6
                && !password.value.toString().contains(fullname.value.toString())
                && !password.value.toString().contains(email.value.toString())
    }

    interface onSuccessListener {
        fun onSuccess()
    }

}