package com.omise.charity.network


interface DomainMappable<R> {
    fun asDomain(): R
}