package hr.thekarlo95.api.github.mapper

import com.thekarlo95.data.model.OrderParamDataModel
import hr.thekarlo95.api.github.model.OrderParamApiModel
import javax.inject.Inject

class OrderParamApiMapper @Inject constructor() : ApiMapper<OrderParamApiModel, OrderParamDataModel> {

    override fun toData(apiModel: OrderParamApiModel): OrderParamDataModel {
        return when (apiModel) {
            OrderParamApiModel.FORKS -> OrderParamDataModel.FORKS
            OrderParamApiModel.STARS -> OrderParamDataModel.STARS
            OrderParamApiModel.UPDATED -> OrderParamDataModel.UPDATED
        }
    }
}