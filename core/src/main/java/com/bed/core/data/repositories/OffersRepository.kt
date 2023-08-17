package com.bed.core.data.repositories

import javax.inject.Inject

import com.bed.core.domain.alias.GetOffersType

import com.bed.core.data.datasources.remote.RemoteOffersDatasource

interface OffersRepository {
    suspend fun getProduct(): GetOffersType
}

class OffersRepositoryImpl @Inject constructor(
    private val datasource: RemoteOffersDatasource
) : OffersRepository {
    override suspend fun getProduct(): GetOffersType = datasource.getOffers()
}
