package uz.beko404.onboardingsample.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import uz.beko404.onboardingsample.R
import uz.beko404.onboardingsample.adapter.OnBoardingAdapter
import uz.beko404.onboardingsample.databinding.FragmentOnBoardingBinding
import uz.beko404.onboardingsample.model.OnBoardingModel
import uz.beko404.onboardingsample.utill.viewBinding

@Suppress("DEPRECATION")
class OnBoarding : Fragment(R.layout.fragment_on_boarding) {
    private val binding by viewBinding { FragmentOnBoardingBinding.bind(it) }
    private var pagerItems: MutableList<OnBoardingModel> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        next.setOnClickListener { swapPage() }
        skip.setOnClickListener { skip() }
        viewPager.addOnPageChangeListener(makePageListener())

        setPagerListContent()
        viewPager.adapter = OnBoardingAdapter(requireContext(), pagerItems)
        viewPager.offscreenPageLimit = pagerItems.size

        dotsIndicator.attachTo(viewPager)
    }

    private fun skip() {
        findNavController().navigate(R.id.action_onBoarding_to_home2)
    }

    private fun swapPage() {
        if (binding.viewPager.currentItem == pagerItems.lastIndex) {
            skip()
        } else {
            binding.viewPager.currentItem++
        }
    }

    private fun makePageListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == pagerItems.lastIndex) {
                    binding.skip.visibility = View.INVISIBLE
                    binding.next.text = getString(R.string.start)
                } else {
                    binding.skip.visibility = View.VISIBLE
                    binding.next.text = getString(R.string.next)
                }
            }
        }
    }

    private fun setPagerListContent() {
        pagerItems.add(
            OnBoardingModel(
                getString(R.string.title_1),
                getString(R.string.description_1),
                R.drawable.img_1,
                resources.getColor(R.color.green)
            )
        )

        pagerItems.add(
            OnBoardingModel(
                getString(R.string.title_2),
                getString(R.string.description_2),
                R.drawable.img_2,
                resources.getColor(R.color.purple)
            )
        )

        pagerItems.add(
            OnBoardingModel(
                getString(R.string.title_3),
                getString(R.string.description_3),
                R.drawable.img_3,
                resources.getColor(R.color.blue)
            )
        )
    }
}