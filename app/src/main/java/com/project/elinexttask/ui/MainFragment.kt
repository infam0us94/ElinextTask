package com.project.elinexttask.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.elinexttask.R
import com.project.elinexttask.adapter.ImagesAdapter
import com.project.elinexttask.api.model.Image
import com.project.elinexttask.databinding.MainFragmentBinding
import com.project.elinexttask.viewmodel.ImageViewModel

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var recView: RecyclerView
    private lateinit var adapter: ImagesAdapter

    private lateinit var viewModel: ImageViewModel

    private var listImage: List<Image> = ArrayList()

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
        recView.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
        adapter = ImagesAdapter(requireContext(), listImage as ArrayList<Image>)
        recView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        viewModel.getPost().observe(viewLifecycleOwner, listImagesObserver)
        viewModel.makeApiCall()
    }

    private val listImagesObserver = Observer<List<Image>> { result ->
        if (result != null) {
            listImage = result
            adapter.setImages(result as ArrayList<Image>)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_btn -> Toast.makeText(context, "ADDED!", Toast.LENGTH_SHORT).show()
            R.id.reload_btn -> Toast.makeText(context, "RELOADED!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

