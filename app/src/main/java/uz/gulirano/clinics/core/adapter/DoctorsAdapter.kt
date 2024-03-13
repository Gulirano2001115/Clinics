package uz.gulirano.clinics.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.gulirano.clinics.core.model.doctors.DoctorResponseItem
import uz.gulirano.clinics.databinding.ItemDoctorBinding

class DoctorsAdapter : RecyclerView.Adapter<DoctorsAdapter.ViewHolder>() {

    private val data = ArrayList<DoctorResponseItem>()
    var onClickItem: ((id: Int) -> Unit)? = null

    fun setData(data: ArrayList<DoctorResponseItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun filterRating() {
        this.data.sortByDescending {
            it.rating.toFloat()
        }
        notifyDataSetChanged()
    }

    fun filterName() {
        this.data.sortBy { it.fullName }
        notifyDataSetChanged()
    }

    fun filterPrice() {
        this.data.sortByDescending { it.price.toInt() }
        notifyDataSetChanged()
    }

    fun filterExperience() {
        this.data.sortByDescending { it.experience }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemDoctorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: DoctorResponseItem) {

            binding.image.load(data.image)
            binding.name.text = data.fullName
            binding.doctor.text = data.specialization

            itemView.setOnClickListener {
                onClickItem?.invoke(data.id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}