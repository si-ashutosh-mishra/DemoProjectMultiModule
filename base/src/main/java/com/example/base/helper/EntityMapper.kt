package com.example.base.helper

interface EntityMapper<E, D> {
    fun toDomain(entity: E): D
}