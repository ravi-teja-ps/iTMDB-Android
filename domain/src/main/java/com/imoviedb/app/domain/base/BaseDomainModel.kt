package com.imoviedb.app.domain.base

open class BaseDomainModel{

     var statusCode: Int = 0

     var statusMessage: String? = null

     fun isSuccess() :Boolean{
          return statusCode <= 0
     }

}