package com.yigitco.springsecurityclient.util.mapper

interface Mapper<E, D> {

    fun mapToDto(entity: E): D

    fun mapToEntityFromDto(dto: D): E

}