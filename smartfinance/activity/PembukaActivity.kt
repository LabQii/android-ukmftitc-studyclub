package pemberkel9.smartfinance.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.util.AppConstant
import pemberkel9.smartfinance.util.PreferenceUtil
import kotlinx.android.synthetic.main.activity_pembuka.*

class PembukaActivity : BaseActivity() {

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembuka)

        // set status bar color blue
        transparentStatusBar()
        imgLogo.setImageDrawable(getBackground(R.drawable.logo_horizontal))

        // delay for to next activity
        Handler().postDelayed({ toActivity() }, AppConstant.LENGTH_SPLASH)

    }

    /*
    * state for choose actvity want to run base on preference
    * */
    private fun toActivity(){

        val isRegisterDone: Boolean? = PreferenceUtil.getPref(this)?.getBoolean(PreferenceUtil.SETUP_REGISTER, false)
        val isLoginDone: Boolean? = PreferenceUtil.getPref(this)?.getBoolean(PreferenceUtil.SETUP_LOGIN, false)

        if (isRegisterDone == true && isLoginDone == false){
            startActivity(Intent(this, BarubukaActivity::class.java))
            finish()
        }else if (isRegisterDone == false && isLoginDone == true){
            startActivity(Intent(this, BarubukaActivity::class.java))
            finish()
        }else if (isRegisterDone == true && isLoginDone == true){
            startActivity(Intent(this, BarubukaActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, BarubukaActivity::class.java))
            finish()
        }


    }

}