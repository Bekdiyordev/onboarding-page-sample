package uz.beko404.onboardingsample.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.textview.MaterialTextView
import uz.beko404.onboardingsample.R
import uz.beko404.onboardingsample.model.OnBoardingModel

class OnBoardingAdapter(
    private val context: Context,
    private val dataList: MutableList<OnBoardingModel>
) : PagerAdapter() {

    override fun getCount(): Int = dataList.size

    override fun isViewFromObject(view: View, image: Any): Boolean = (view == image)

    @SuppressLint("MissingInflatedId")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_on_boarding, container, false)
        val data = dataList[position]
        view.findViewById<ImageView>(R.id.image).setImageResource(data.image)
        view.findViewById<MaterialTextView>(R.id.title).text = data.title
        view.findViewById<MaterialTextView>(R.id.description).text = data.description
        view.findViewById<LinearLayout>(R.id.item).setBackgroundColor(data.color)

        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) =
        container.removeView(view as View?)
}