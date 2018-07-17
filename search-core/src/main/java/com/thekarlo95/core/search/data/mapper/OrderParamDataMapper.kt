package com.thekarlo95.core.search.data.mapper

import com.thekarlo95.core.search.data.model.OrderParamDataModel
import com.thekarlo95.core.search.domain.model.OrderParamDomainModel
import javax.inject.Inject

class OrderParamDataMapper @Inject constructor() : DataMapper<OrderParamDataModel, OrderParamDomainModel> {
    override fun toDomain(dataModel: OrderParamDataModel): OrderParamDomainModel {
        return when (dataModel) {
            OrderParamDataModel.FORKS -> OrderParamDomainModel.FORKS
            OrderParamDataModel.STARS -> OrderParamDomainModel.STARS
            OrderParamDataModel.UPDATED -> OrderParamDomainModel.UPDATED
        }
    }

    override fun toData(domainModel: OrderParamDomainModel): OrderParamDataModel {
        return when (domainModel) {
            OrderParamDomainModel.FORKS -> OrderParamDataModel.FORKS
            OrderParamDomainModel.STARS -> OrderParamDataModel.STARS
            OrderParamDomainModel.UPDATED -> OrderParamDataModel.UPDATED
        }
    }
}