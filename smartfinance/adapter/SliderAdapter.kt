package pemberkel9.smartfinance.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import pemberkel9.smartfinance.R

class SliderAdapter(private val context: Context, private val slides: List<Slide>) :
    PagerAdapter() {

    override fun getCount(): Int {
        return slides.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
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

