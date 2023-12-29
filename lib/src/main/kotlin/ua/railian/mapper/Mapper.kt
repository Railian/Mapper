package ua.railian.mapper

public fun interface Mapper<T, R> {
    public fun map(source: T): R
}
