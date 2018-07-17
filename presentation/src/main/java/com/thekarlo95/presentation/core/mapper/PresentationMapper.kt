package com.thekarlo95.presentation.core.mapper

interface PresentationMapper<in I, out O> {

    fun toPresentation(domainModel: I): O

    fun toPresentationList(domainModels: List<I>): List<O> = domainModels.map { toPresentation(it) }
}