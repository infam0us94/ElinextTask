package com.project.elinexttask.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.elinexttask.R
import com.project.elinexttask.adapter.RecyclerViewAdapter
import com.project.elinexttask.databinding.MainFragmentBinding
import com.project.elinexttask.viewmodel.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var recView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        initRecView()

        initViewModel()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    private fun initRecView() {
        recView = binding.recView
        recView.layoutManager =
            StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.HORIZONTAL)
        val decoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recView.addItemDecoration(decoration)
        adapter = RecyclerViewAdapter()
        recView.adapter = adapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_btn -> {
//                   adapter.setImage(Image("http://loremflickr.com/200/200/"))
//                recView.smoothScrollToPosition(adapter.itemCount - 1)
            }
            R.id.reload_btn -> {
                initViewModel()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

