package com.imoviedb.app.data.dto.base.mapper

interface Mapper<in T ,out R> {
    fun  map(from : T) : R
}

