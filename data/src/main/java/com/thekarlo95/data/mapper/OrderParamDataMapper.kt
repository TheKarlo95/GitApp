package com.thekarlo95.data.mapper

import com.thekarlo95.data.model.OrderParamDataModel
import com.thekarlo95.domain.core.model.OrderParamDomainModel
import javax.inject.Inject

class OrderParamDataMapper @Inject constructor() : DataMapper<OrderParamDataModel, OrderParamDomainModel> {
    override fun toDomain(dataModel: OrderParamDataModel): OrderParamDomainModel {
        return when (dataModel) {
            OrderParamDataModel.FORKS -> OrderParamDomainModel.FORKS
            OrderParamDataModel.STARS -> OrderParamDomainModel.STARS
            OrderParamDataModel.UPDATED -> OrderParamDomainModel.UPDATED
        }
    }
}