package ua.railian.mapper.contract

public fun interface Mapper<T, R> {
    public fun map(source: T): R
}
