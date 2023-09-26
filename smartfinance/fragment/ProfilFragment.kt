package pemberkel9.smartfinance.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kategori.*
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.fragment_profil.ivBack
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.activity.BarubukaActivity
import pemberkel9.smartfinance.activity.MainActivity
import pemberkel9.smartfinance.activity.SignInActivity
import pemberkel9.smartfinance.pojo.User
import pemberkel9.smartfinance.util.SessionManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        if (SessionManager.getProfile(requireContext()) == null) return
        val own: User? = SessionManager.getProfile(requireContext())
        if (own != null) {
            p.text = own.fullname
        }
        initView()
        loadSavedData()

    }

    private fun initView() {
        ivBack.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        btnSimpan.setOnClickListener {
            simpanData()
            Toast.makeText(requireContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
        }

        btnLogout.setOnClickListener {
            val intent = Intent(requireContext(), BarubukaActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

    private fun simpanData() {
        val editor = sharedPreferences.edit()
        editor.putString("fullname", etTitle.text.toString())
        editor.putString("birthday", etDesc.text.toString())
        editor.putString("gender", etSaldo.text.toString())
        editor.putString("address", etAlamat.text.toString())
        editor.apply()
    }
    private fun loadSavedData() {
        etTitle.setText(sharedPreferences.getString("fullname", ""))
        etDesc.setText(sharedPreferences.getString("birthday", ""))
        etSaldo.setText(sharedPreferences.getString("gender", ""))
        etAlamat.setText(sharedPreferences.getString("address", ""))
    }


}