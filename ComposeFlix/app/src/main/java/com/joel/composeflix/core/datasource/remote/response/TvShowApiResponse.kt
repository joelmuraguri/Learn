package com.joel.composeflix.core.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class TvShowApiResponse(
    val page: Int,
    val results: List<TvShow>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

data class TvShow(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("first_air_date")
    val firstAirDate: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerialName("media_type")
    val mediaType: String ? = null,
    val name: String,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class TvShowDetailsApiResponse(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("created_by")
    val createdBy: List<CreatedBy>,
    @SerialName("episode_run_time")
    val episodeRunTime: List<String> = emptyList(),
    @SerialName("first_air_date")
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @SerialName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerialName("last_air_date")
    val lastAirDate: String,
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: NextEpisodeToAir,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val poster_path: String,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class Season(
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_count")
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("vote_average")
    val voteAverage: Double
)


@Serializable
data class CreatedBy(
    @SerialName("credit_id")
    val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val profilePath: String
)

@Serializable
data class LastEpisodeToAir(
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_number")
    val episodeNumber: Int,
    @SerialName("episode_type")
    val episodeType: String,
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("production_code")
    val productionCode: String,
    val runtime: Int,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("show_id")
    val showId: Int,
    @SerialName("still_path")
    val stillPath: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class NextEpisodeToAir(
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_number")
    val episodeNumber: Int,
    @SerialName("episode_type")
    val episodeType: String,
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("production_code")
    val productionCode: String,
    val runtime: Int,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("show_id")
    val showId: Int,
    @SerialName("still_path")
    val stillPath: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class Network(
    val id: Int,
    @SerialName("logo_path")
    val logoPath: String,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)

