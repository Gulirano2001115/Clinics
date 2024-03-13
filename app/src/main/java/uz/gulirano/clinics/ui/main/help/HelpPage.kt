package uz.gulirano.clinics.ui.main.help

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.adapter.HelpAdapter
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.util.gone
import uz.gulirano.clinics.core.util.visible
import uz.gulirano.clinics.databinding.PageHelpBinding

class HelpPage : BaseFragment(R.layout.page_help) {

    private val binding by viewBinding(PageHelpBinding::bind)
    private val adapter by lazy { HelpAdapter(this) }
    private val viewModel: HelpViewModel by viewModels()

    override fun onViewCreated() {
        binding.recyclerView.adapter = adapter
        binding.progressBar.visible()
        observer()
    }

    private fun observer() {

        viewModel.getHelp()

        viewModel.successRegionData.observe(viewLifecycleOwner) {

            it?.let { data ->
                adapter.setData(data)
                binding.progressBar.gone()
            }

        }

    }


}