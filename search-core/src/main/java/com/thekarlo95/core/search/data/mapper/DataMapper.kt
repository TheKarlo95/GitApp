package com.thekarlo95.core.search.data.mapper

interface DataMapper<I, O> {
    fun toDomain(dataModel: I): O

    fun toData(domainModel: O): I

    fun toDomainList(dataModels: List<I>): List<O> = dataModels.map { toDomain(it) }

    fun toDataList(domainModels: List<O>): List<I> = domainModels.map { toData(it) }
}