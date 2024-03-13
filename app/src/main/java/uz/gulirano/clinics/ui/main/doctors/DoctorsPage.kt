package uz.gulirano.clinics.ui.main.doctors

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.adapter.ClinicCategoryAdapter
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.db.CardDataBase
import uz.gulirano.clinics.core.db.CardEntity
import uz.gulirano.clinics.core.util.getDoctorsData
import uz.gulirano.clinics.databinding.PageDoctorsBinding
import uz.gulirano.clinics.ui.bottomsheet.CardBottomSheet
import uz.gulirano.clinics.ui.bottomsheet.CardListBottomSheet

class DoctorsPage : BaseFragment(R.layout.page_doctors) {

    private val binding by viewBinding(PageDoctorsBinding::bind)
    private val adapter by lazy { ClinicCategoryAdapter() }
    private lateinit var db: CardDataBase

    override fun onViewCreated() {

        db = Room.databaseBuilder(
            requireContext(),
            CardDataBase::class.java,
            "card-db"
        ).allowMainThreadQueries().build()

        loadAdapter()
        setData()
        loadAction()


    }

    private fun setData() {
        adapter.setData(getDoctorsData())
    }


    private fun loadAction() {

        binding.consultation.setOnClickListener {
            if (db.getDao().getCards().isEmpty()) {
                cardAddView()
            } else {
                val card = CardListBottomSheet(requireContext())
                card.setData(db.getDao().getCards())
                card.onClickAdd = {
                    cardAddView()
                }
            }

        }



        adapter.onClickItem = { category ->
            findNavController().navigate(
                DoctorsPageDirections.actionPageDoctorsToCategoryDoctorScreen(
                    category
                )
            )
        }

    }

    private fun cardAddView() {
        val card = CardBottomSheet(requireContext())
        card.onClickSave = { n: String, d: String, v: String ->
            Toast.makeText(context, "AA", Toast.LENGTH_SHORT).show()
            db.getDao().insertCards(CardEntity(n, d, v))
        }
    }

    private fun loadAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }


}