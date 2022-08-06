package com.enesselcuk.jatpeackdatastore.ui

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesselcuk.jatpeackdatastore.common.BaseActivity
import com.enesselcuk.jatpeackdatastore.databinding.ActivityMainBinding
import com.enesselcuk.jatpeackdatastore.repos.SortOrder
import com.enesselcuk.jatpeackdatastore.ui.adapter.PersonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val viewModel: PersonViewModel by viewModels()
    private lateinit var perAdapter: PersonAdapter

    override fun defination() {

        perAdapter = PersonAdapter()

        with(binding) {
            listRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            listRecyclerview.adapter = perAdapter
        }

        viewModel.personUiModelFlow.observe(this) {
            perAdapter.submitList(it.person)
            updateSort(it.sortOrder)
            binding.setSwitch = it.showCompleted
        }
        setUpSort()
    }

   private fun setUpSort() {
        binding.sortDeadline.setOnCheckedChangeListener { _, checked ->
            viewModel.enableSortByDeadline(checked)
        }
        binding.sortPriority.setOnCheckedChangeListener { _, checked ->
            viewModel.enableSortByPriority(checked)
        }
    }

    private fun updateSort(sortOrder: SortOrder) {
        binding.sortDeadline.isChecked =
            sortOrder == SortOrder.BY_DEADLINE || sortOrder == SortOrder.BY_DEADLINE_AND_PRIORITY

        binding.sortPriority.isChecked =
            sortOrder == SortOrder.BY_PRIORITY || sortOrder == SortOrder.BY_DEADLINE_AND_PRIORITY
    }

    fun setupFilterListeners(checked: Boolean) {
        viewModel.showCompletedPerson(checked)
    }


}