package me.fidelep.gamevault.domain.model

data class SearchModel(
    val searchParams: String = "",
    val searchFilter: SearchFilter = SearchFilter.ALL,
)
