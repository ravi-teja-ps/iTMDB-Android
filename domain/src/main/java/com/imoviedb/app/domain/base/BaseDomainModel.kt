package com.imoviedb.app.domain.base

/**
 * Common behaviour of all domain models to return the result of code as success or failure
 */
interface BaseDomainModel {
    fun isResponseSuccessful(): Boolean //later rename statusCode tos to errorCode by default -1 else > 0 is error
}