package com.bed.core.data.repositories

import javax.inject.Inject

import com.bed.core.domain.alias.GetProductDomainType
import com.bed.core.data.datasources.remote.ProductRemoteDatasource

interface ProductRepository {
    suspend fun getProduct(): GetProductDomainType
}

class ProductRepositoryImpl @Inject constructor(
    private val datasource: ProductRemoteDatasource
) : ProductRepository {
    override suspend fun getProduct(): GetProductDomainType = datasource.getProduct()
}