package uz.gulirano.clinics.core.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gulirano.clinics.core.model.category.CategoryModel
import uz.gulirano.clinics.databinding.ItemCategoryClinicBinding

class ClinicCategoryAdapter : RecyclerView.Adapter<ClinicCategoryAdapter.ViewHolder>() {


    private val data = ArrayList<CategoryModel>()
    var onClickItem: ((title: String) -> Unit)? = null

    fun setData(data: ArrayList<CategoryModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCategoryClinicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: CategoryModel) {

            binding.imageIcon.setImageDrawable(binding.imageView.resources.getDrawable(data.icon))
            binding.background.setBackgroundColor(data.backColor)
            binding.imageView.setColorFilter(Color.WHITE)
            binding.title.text = data.title

            itemView.setOnClickListener {
                onClickItem?.invoke(data.title)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryClinicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data = data[position])
    }
}