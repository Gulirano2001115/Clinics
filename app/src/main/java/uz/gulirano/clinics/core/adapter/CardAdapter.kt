package uz.gulirano.clinics.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import uz.gulirano.clinics.databinding.CardItemBinding

class CardAdapter : BaseAdapter() {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Any {
        return ""
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        if (position == 0) {
            binding.cheked.visibility = View.VISIBLE
        }
        return binding.root
    }
}