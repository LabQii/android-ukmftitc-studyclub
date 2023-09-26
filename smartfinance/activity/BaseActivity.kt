package pemberkel9.smartfinance.activity

import android.graphics.drawable.Drawable
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

open class BaseActivity : AppCompatActivity() {

    @Suppress("DEPRECATION")
    open fun transparentStatusBar(){
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    open fun getBackground(drawable: Int): Drawable? {
        return ContextCompat.getDrawable(this, drawable)
    }

    open fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}