package com.thekarlo95.search.ui.andorid.core.mapper

interface UiMapper<in I, out O> {

    fun toUi(presentationModel: I): O

    fun toUiList(presentationModels: List<I>): List<O> = presentationModels.map { toUi(it) }
}