package com.imoviedb.app.presentation.ui.popularshows.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope

import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.presentation.R
import com.imoviedb.app.presentation.databinding.FragmentPopularShowDetailsBinding
import com.imoviedb.app.presentation.ui.base.BaseFragment
import com.imoviedb.app.presentation.ui.base.State

import com.imoviedb.app.presentation.ui.popularshows.details.viewmodel.PopularShowDetailsViewModel
import com.imoviedb.app.presentation.ui.utils.KeyUtils
import com.imoviedb.app.presentation.ui.utils.UrlUtils
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularShowDetailsFragment : BaseFragment() {

    override val hasBottomNavigation: Boolean = false
    override val isDetailScreen: Boolean = true
    override val showTitleBar: Boolean = true
    override val titleId: Int = R.string.movie_details_header

    private var _binder: FragmentPopularShowDetailsBinding? = null
    private val binding get() = _binder!!
    private val popularShowDetailsViewModel: PopularShowDetailsViewModel by viewModels()
    private var showId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showId = arguments?.getInt(KeyUtils.SHOW_ID_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binder = FragmentPopularShowDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerForData()
    }

    private fun registerForData() {
        showId?.let {
            lifecycleScope.launchWhenCreated {
                popularShowDetailsViewModel.getShowDetailsFromDB(it)
                popularShowDetailsViewModel.data.collect {
                    when (it) {
                        is State.Loading -> {

                        }
                        is State.OnComplete -> {
                            updateUiFromState(it.completionResult as ShowDomainModel)
                        }
                        is State.OnCompletePagedData -> {}
                        is State.OnError -> {
                            showErrorScreenWithInfo(code = it.errorCode, it.errorMessage)
                        }
                    }
                }
            }
        }
    }

    private fun updateUiFromState(show: ShowDomainModel) {
        Picasso.with(context).load("${UrlUtils.IMAGE_URL_BACKDROP_PREFIX}${show.posterPath}")
            .into(binding.moviePoster)
        Picasso.with(context).load("${UrlUtils.IMAGE_URL_BACKDROP_PREFIX}${show.backdropPath}")
            .into(binding.movieBackdrop)
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
}