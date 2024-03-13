package uz.gulirano.clinics.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.gulirano.clinics.core.model.clinic.ClinicResponseItem
import uz.gulirano.clinics.databinding.ItemClinicsBinding

class ClinicAdapter : RecyclerView.Adapter<ClinicAdapter.ViewHolder>() {

    private val data = ArrayList<ClinicResponseItem>()
    var onClickItem: ((id: Int) -> Unit)? = null

    fun setData(data: List<ClinicResponseItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.data.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemClinicsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ClinicResponseItem) {

            binding.title.text = data.name
            binding.shapeableImageView.load(data.image)
            binding.rating.rating = data.rating.toFloat()

            itemView.setOnClickListener {

                onClickItem?.invoke(data.id)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemClinicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data = data[position])
    }
}