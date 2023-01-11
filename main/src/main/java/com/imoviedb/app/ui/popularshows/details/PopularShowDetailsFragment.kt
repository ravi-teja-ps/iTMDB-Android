package com.imoviedb.app.ui.popularshows.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.imoviedb.app.R
import com.imoviedb.app.data.models.popular.Show
import com.imoviedb.app.data.networking.utils.ApiServiceUtils
import com.imoviedb.app.databinding.FragmentPopularShowDetailsBinding
import com.imoviedb.app.ui.core.BaseFragment
import com.imoviedb.app.ui.core.BaseViewModel
import com.imoviedb.app.ui.popularshows.details.viewmodel.PopularShowDetailsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularShowDetailsFragment : BaseFragment() {

    override val hasBottomNavigation: Boolean = false
    override val isDetailScreen: Boolean = true
    override val showTitleBar: Boolean = true
    override val titleId: Int =R.string.movie_details_header

    private var _binder :FragmentPopularShowDetailsBinding? = null
    private val binding get() = _binder!!
    private val popularShowDetailsViewModel : PopularShowDetailsViewModel by viewModels()
    private var showId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showId = arguments?.getInt("showId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binder= FragmentPopularShowDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerForData()
    }

    private fun registerForData(){
        showId?.let {
            lifecycleScope.launchWhenCreated {
                popularShowDetailsViewModel.getShowDetailsFromDB(it)
                popularShowDetailsViewModel.data.collect{
                    when(it){
                        is BaseViewModel.State.Loading -> {

                        }
                        is BaseViewModel.State.OnComplete -> {
                            updateUiFromState(it.completionResult as Show)
                        }
                        is BaseViewModel.State.OnCompletePagedData -> {}
                        is BaseViewModel.State.OnError -> {showErrorScreenWithInfo(code = it.errorCode)}
                    }
                }
            }
        }
    }
    private fun updateUiFromState(show: Show){
        Picasso.with(context).load("${ApiServiceUtils.IMAGE_URL_BACKDROP_PREFIX}${show.posterPath}").into(binding.moviePoster)
        Picasso.with(context).load("${ApiServiceUtils.IMAGE_URL_BACKDROP_PREFIX}${show.backdrop_path}").into(binding.movieBackdrop)
        binding.let {
            it.movieTitle.text = show.originalTitle
            it.movieReleaseDate.text = show.releaseDate
            it.movieOverview.text = show.overview
            updateToolbarTitle(show.originalTitle)
        }
    }

    /**
     * Clear binder traces on fragment destroyed
     */
    override fun onDestroyBinding() {
        _binder = null
    }

    companion object {
        @JvmStatic
        fun newInstance(showId: Int) : PopularShowDetailsFragment {
            return  PopularShowDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt("showId",showId )
                }
            }
        }
    }
}