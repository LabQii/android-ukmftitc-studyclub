package pemberkel9.smartfinance.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_daftar.*
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.databinding.ActivitySignInBinding
import pemberkel9.smartfinance.viewmodel.AutentikasiViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.ivBack

class SignInActivity : BaseActivity(), View.OnClickListener {

    /*
    * Init view model
    * */
    private lateinit var userViewModel: AutentikasiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        userViewModel = ViewModelProviders.of(this).get(AutentikasiViewModel::class.java)

        // binding for screen xml
        binding.authViewModel = userViewModel

        // set color status bar white
        transparentStatusBar()

        // init view
        initView()
    }

    private fun initView(){

        ivBack.setOnClickListener {
            finish()
        }
        btnSignIn.setOnClickListener(this)
        tvCreateAccount.setOnClickListener(this)
    }

    /*
    * listener for success login
    * */
    private  val listener = object : AutentikasiViewModel.onSuccessListener{
        override fun onSuccess() {
            Intent()
        }
    }

    private fun Intent(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /*
    * onclick event
    * */
    override fun onClick(p0: View?) {
        when (p0) {
            btnSignIn -> {
                userViewModel.loginClick(listener)
            }
            tvCreateAccount -> {
                startActivity(Intent(this, DaftarActivity::class.java))
                finish()
            }
        }
    }
}
