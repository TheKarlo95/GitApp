package com.thekarlo95.data.mapper

interface DataMapper<I, O> {
    fun toDomain(dataModel: I): O

    fun toDomainList(dataModels: List<I>): List<O> = dataModels.map { toDomain(it) }
}