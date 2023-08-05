package com.bed.core.data.datasources.remote

import com.bed.core.domain.alias.GetProductDomainType

interface RemoteProductDatasource {
    suspend fun getProduct(): GetProductDomainType
}
