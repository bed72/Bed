package com.bed.core.data.datasources.remote

import com.bed.core.domain.alias.GetOffersType

interface RemoteOffersDatasource {
    suspend fun getOffers(): GetOffersType
}
