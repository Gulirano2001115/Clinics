package uz.gulirano.clinics.ui.main.clinics

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.adapter.ClinicAdapter
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.util.gone
import uz.gulirano.clinics.core.util.visible
import uz.gulirano.clinics.databinding.PageHomeBinding


class HomePage : BaseFragment(R.layout.page_home) {

    private val binding by viewBinding(PageHomeBinding::bind)
    private val adapter by lazy { ClinicAdapter() }
    private val homeViewModel: HomeViewModel by viewModels()
    private val args: HomePageArgs by navArgs()

    override fun onViewCreated() {

        setAdapter()
        observeData()
        loadActions()

    }

    private fun loadActions() {

        binding.dropdownMenu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                binding.progressBar.visible()
                binding.noData.gone()
                adapter.clearData()
                homeViewModel.getClinicsByRegionId((view as TextView).text.toString() , args.category)

            }

        adapter.onClickItem = { id ->
            findNavController().navigate(HomePageDirections.actionHomePageToClinicScreen(id))
        }

    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun observeData() {

        homeViewModel.getRegions()
        homeViewModel.getClinicsByRegionId("Yashnobod" , args.category)
        homeViewModel.successRegionData.observe(viewLifecycleOwner) { regions ->
            val data = arrayListOf<String>()
            regions?.let {
                data.clear()
                for (i in it) {
                    data.add(i.regionName)
                }
            }
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_item, data)
            binding.dropdownMenu.setText("Yashnobod")
            binding.dropdownMenu.setAdapter(adapter)

        }

        homeViewModel.successClinicsData.observe(viewLifecycleOwner) { clinics ->

            clinics?.let {

                if (it.isEmpty()) {
                    binding.noData.visible()
                } else {
                    adapter.setData(it)
                    binding.noData.gone()
                }
                binding.progressBar.gone()
            }

        }

    }


}