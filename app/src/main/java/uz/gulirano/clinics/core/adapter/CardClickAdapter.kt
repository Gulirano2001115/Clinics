package uz.gulirano.clinics.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import uz.gulirano.clinics.core.db.CardEntity
import uz.gulirano.clinics.databinding.CardItemBinding

class CardClickAdapter : RecyclerView.Adapter<CardClickAdapter.ViewHolder>() {


    var onClick: ((position: Int) -> Unit)? = null
    var selectedItemPosition: Int? = null
        private set

    private val data = ArrayList<CardEntity>()

    fun setData(cardEntity: List<CardEntity>) {
        this.data.clear()
        this.data.addAll(cardEntity)
        notifyDataSetChanged()
    }

    fun setClick() {

        onClick = {
            if (selectedItemPosition == it) {
                selectedItemPosition = null
            } else {
                selectedItemPosition = it
            }
            notifyDataSetChanged()
        }

    }

    inner class ViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int, data: CardEntity) {

            val bosh = data.cardNumber.substring(0, 5)
            val orqa = data.cardNumber.substring(15)

            binding.numberView.text = "$bosh ******** $orqa"
            binding.dateView.text = data.cardDate

            itemView.tag = position
            binding.cheked.isVisible = (position == selectedItemPosition)
            itemView.setOnClickListener {
                onClick?.invoke(position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position, data[position])
    }
}