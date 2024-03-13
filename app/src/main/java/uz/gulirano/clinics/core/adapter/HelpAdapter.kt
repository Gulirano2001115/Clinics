package uz.gulirano.clinics.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import uz.gulirano.clinics.core.model.help.HelpResponseItem
import uz.gulirano.clinics.databinding.ItemHelpBinding

class HelpAdapter(private val fragment: Fragment) : RecyclerView.Adapter<HelpAdapter.ViewHolder>() {

    private val data = ArrayList<HelpResponseItem>()

    fun setData(data: List<HelpResponseItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemHelpBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: HelpResponseItem) {

            binding.txtName.text = data.title
            binding.discript.text = data.description
            class MyChrome : WebChromeClient() {
                private var mCustomView: View? = null
                private var mCustomViewCallback: CustomViewCallback? = null
                private var mOriginalSystemUiVisibility = 0

                override fun onHideCustomView() {
                    (fragment.requireActivity().window.decorView as FrameLayout).removeView(
                        mCustomView
                    )
                    mCustomView = null
                    fragment.requireActivity().window.decorView.systemUiVisibility =
                        mOriginalSystemUiVisibility
                    mCustomViewCallback!!.onCustomViewHidden()
                    mCustomViewCallback = null
                }

                override fun onShowCustomView(
                    paramView: View,
                    paramCustomViewCallback: CustomViewCallback
                ) {
                    if (mCustomView != null) {
                        onHideCustomView()
                        return
                    }
                    mCustomView = paramView
                    mOriginalSystemUiVisibility =
                        fragment.requireActivity().window.decorView.systemUiVisibility
                    mCustomViewCallback = paramCustomViewCallback
                    (fragment.requireActivity().window.decorView as FrameLayout).addView(
                        mCustomView,
                        FrameLayout.LayoutParams(-1, -1)
                    )
                    fragment.requireActivity().window.decorView.systemUiVisibility =
                        3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
            }

            binding.webview.apply {
                visibility = View.VISIBLE
                settings.javaScriptEnabled = true
                isSoundEffectsEnabled = true
                webChromeClient = MyChrome()
                data.videoUrl.let {
                    loadUrl("https://www.youtube.com/embed/${it}")
                }

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHelpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}