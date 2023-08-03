package com.bed.core.data.datasources.remote

import com.bed.core.domain.alias.GetProductDomainType

interface ProductRemoteDatasource {
    suspend fun getProduct(): GetProductDomainType
}
