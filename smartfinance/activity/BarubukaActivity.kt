package pemberkel9.smartfinance.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.adapter.Slide

class BarubukaActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private val slides = listOf(
        Slide(R.drawable.gb1, "Atur Uang dengan bijak", " Aplikasi Cerdas untuk Mengontrol Uang Anda"),
        Slide(R.drawable.gb2, "Tahu Kemana Uang Anda Pergi ", "Lacak Transaksi Anda dengan Mudah Dengan Kategori dan Laporan Keuangan"),
        Slide(R.drawable.gb3, "Manage Pengeluaran Pemasukan", "Atur uang anda agar teralokasi dengan baik untuk pengeluaran dan pemasukan")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barubuka)

        viewPager = findViewById(R.id.viewPager)
        titleTextView = findViewById(R.id.titleTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)

        val sliderAdapter = SliderAdapter(slides)
        viewPager.adapter = sliderAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                updateSlide(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        addIndicatorDots()


    }

    private fun addIndicatorDots() {
        val indicatorLayout: ViewGroup = findViewById(R.id.indicator)

        val indicators = arrayOfNulls<ImageView>(slides.size)

        val layoutParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        for (i in slides.indices) {
            indicators[i] = ImageView(this)
            indicators[i]?.setImageResource(R.drawable.indicator_inactive)
            indicators[i]?.layoutParams = layoutParams
            indicatorLayout.addView(indicators[i])
        }

        indicators[0]?.setImageResource(R.drawable.indicator_active)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                updateIndicator(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }


    private fun updateSlide(position: Int) {
        val slide = slides[position]
        titleTextView.text = slide.title
        descriptionTextView.text = slide.description
    }

    private fun updateIndicator(position: Int) {
        val indicatorLayout: ViewGroup = findViewById(R.id.indicator)
        val indicatorsCount = indicatorLayout.childCount

        for (i in 0 until indicatorsCount) {
            val indicator = indicatorLayout.getChildAt(i) as ImageView
            if (i == position) {
                indicator.setImageResource(R.drawable.indicator_active)
            } else {
                indicator.setImageResource(R.drawable.indicator_inactive)
            }
        }
    }

    inner class SliderAdapter(private val slides: List<Slide>) : PagerAdapter() {
        override fun getCount(): Int {
            return slides.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(container.context)
            val slideLayout = inflater.inflate(R.layout.slide_item, container, false)

            val imageView: ImageView = slideLayout.findViewById(R.id.imageView)

            val slide = slides[position]
            imageView.setImageResource(slide.image)

            container.addView(slideLayout)
            return slideLayout
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)


            }
        }

    fun onStartButtonClicked(view: View) {
        val intent = Intent(this,DaftarActivity::class.java)
        startActivity(intent)

}

    fun onStartButtonMasuk(view: View) {

        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
    }



}
