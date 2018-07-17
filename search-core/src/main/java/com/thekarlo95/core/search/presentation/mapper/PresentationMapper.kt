package com.thekarlo95.core.search.presentation.mapper

interface PresentationMapper<in I, out O> {

    fun toPresentation(domainModel: I): O

    fun toPresentaitonList(domainModels: List<I>): List<O> = domainModels.map { toPresentation(it) }
}