package pemberkel9.smartfinance.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.databinding.ActivityDaftarBinding
import pemberkel9.smartfinance.viewmodel.AutentikasiViewModel
import kotlinx.android.synthetic.main.activity_daftar.*

class DaftarActivity : BaseActivity(), View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /*
    * Init view model
    * */
    private lateinit var userViewModel: AutentikasiViewModel
    private var isCheck: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDaftarBinding = DataBindingUtil.setContentView(this, R.layout.activity_daftar)
        userViewModel = ViewModelProviders.of(this).get(AutentikasiViewModel::class.java)

        // binding to xml layout
        binding.authViewModel = userViewModel

        // set status bar color white
        transparentStatusBar()

        initView()
    }

    /*
    * Init event listener component view
    * */
    private fun initView() {
        ivBack.setOnClickListener {
            finish()
        }
        btnCreateAccount.setOnClickListener(this)
        tvAlreadyAccount.setOnClickListener(this)
        btnCreateAccount.setOnClickListener(this)
        cbTNC.setOnCheckedChangeListener(this)
    }

    override fun onClick(p0: View?) {

        if (p0 == btnCreateAccount) {
            // call view model function for register
            userViewModel.registerClick(isCheck, listener)

        } else if (p0 == tvAlreadyAccount) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

    }


    /*
    * listener when success register
    * */
    private val listener = object : AutentikasiViewModel.onSuccessListener {
        override fun onSuccess() {
            Intent()
        }
    }

    /*
    * move screen to next page
    * */
    private fun Intent() {
        startActivity(Intent(this, DaftarSuksesActivity::class.java))
        finish()
    }

    /*
    * listener for checkbox
    * */
    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        if (p1) {
            isCheck = true
            cbTNC.background = getBackground(R.drawable.bg_checkbox_check)
        } else {
            isCheck = false
            cbTNC.background = getBackground(R.drawable.bg_checkbox)
        }
    }

}
