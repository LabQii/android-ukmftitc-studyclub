package pemberkel9.smartfinance.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_profil.*
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.fragment.AboutFragment
import pemberkel9.smartfinance.fragment.ProfilFragment

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        bukaFragmentProfil()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btn_profil -> {
                    bukaFragmentProfil()
                    true
                }
                R.id.btn_about -> {
                    bukaFragmentAbout()
                    true
                }
                else -> false
            }
        }

    }

    private fun bukaFragmentProfil(){
        val fragmentManager : FragmentManager = supportFragmentManager

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val profile = ProfilFragment()

        fragmentTransaction.replace(R.id.container,profile,ProfilFragment::class.java.simpleName)
        fragmentTransaction.commit()
    }

    private fun bukaFragmentAbout(){
        val fragmentManager : FragmentManager = supportFragmentManager

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val about = AboutFragment()

        fragmentTransaction.replace(R.id.container,about,AboutFragment::class.java.simpleName)
        fragmentTransaction.commit()
    }
}