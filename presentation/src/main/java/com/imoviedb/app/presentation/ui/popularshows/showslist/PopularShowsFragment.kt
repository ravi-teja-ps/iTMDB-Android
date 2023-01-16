package com.imoviedb.app.presentation.ui.popularshows.showslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.imoviedb.app.presentation.R
import com.imoviedb.app.presentation.databinding.PopularShowsFragmentBinding
import com.imoviedb.app.presentation.ui.base.BaseFragment
import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.presentation.ui.popularshows.showslist.viewmodel.PopularShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularShowsFragment : BaseFragment() {

    override val hasBottomNavigation: Boolean = true
    override val isDetailScreen: Boolean = false
    override val showTitleBar: Boolean = true
    override val titleId: Int = R.string.popular_shows_screen

    //view model init
    private val viewModel: PopularShowsViewModel by viewModels()
    //Binding
    private var _binder: PopularShowsFragmentBinding? = null
    private val binding get() = _binder!!

    private val popularShowsGridAdapter: PopularShowsGridAdapter =
        PopularShowsGridAdapter(::onListItemSelected)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPopularShows()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binder = PopularShowsFragmentBinding.inflate(layoutInflater)
        initGridViewWithData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewLifecycleOwner.lifecycleScope.launch {
                observePopularShowData()
        }
    }

    //Invoked under scope of a lifecycle of fragment and not recreated
    private suspend fun observePopularShowData() {

        viewModel.data.collectLatest {
            when (it) {
                is State.Loading -> { }//Ignoring loading par as lazy loading shows items upfront
                is State.OnCompletePagedData -> {
                    popularShowsGridAdapter.submitData(it.pagedData)
                }

                is State.OnError -> { showErrorScreenWithInfo(code = it.errorCode)}

                is State.OnComplete -> {} //non functional callback for paging3 screen
            }
        }
    }

    //Recycler initialize
    private fun initGridViewWithData() {
        binding.popularRecyclerview.apply {
            layoutManager =
                GridLayoutManager(activity, GRID_ITEMS_COUNT, GridLayoutManager.VERTICAL, false)
            adapter = popularShowsGridAdapter
        }
    }

    //A higher order expression passed as constructor param invoked on list ite clicked
    private fun onListItemSelected(id: Int) {
        val bundle = Bundle().apply {
            putInt("showId",id)
        }
       findNavController().navigate(R.id.action_popularShowsFragment_to_popularShowDetailsFragment,bundle)
    }

    override fun onDestroyBinding() {
        _binder = null
    }

    companion object {
        const val GRID_ITEMS_COUNT = 2
    }

    override fun onDestroy() {
        super.onDestroy()
     }
}