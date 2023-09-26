package pemberkel9.smartfinance.activity

import android.content.Intent
import android.os.Bundle
import pemberkel9.smartfinance.R
import kotlinx.android.synthetic.main.activity_daftar_sukses.*

class DaftarSuksesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_sukses)

        transparentStatusBar()

        ivThumbsUp.setImageDrawable(getBackground(R.drawable.centang1))
        btnToMain.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

    }

}