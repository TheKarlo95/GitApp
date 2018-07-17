package hr.thekarlo95.api.github.mapper

interface ApiMapper<in I, out O> {
    fun toData(apiModel: I): O

    fun toDataList(apiModels: List<I>): List<O> = apiModels.map { toData(it) }
}