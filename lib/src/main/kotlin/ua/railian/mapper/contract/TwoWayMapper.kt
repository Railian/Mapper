package ua.railian.mapper.contract

public interface TwoWayMapper<T, R> {
    public val oneWay: Mapper<T, R>
    public val backWay: Mapper<R, T>
    public fun map(source: T): R = oneWay.map(source)
    public fun mapBack(source: R): T = backWay.map(source)
}

public fun <T, R> twoWayMapper(
    oneWay: Mapper<T, R>,
    backWay: Mapper<R, T>,
): TwoWayMapper<T, R> {
    return object : TwoWayMapper<T, R> {
        override val oneWay: Mapper<T, R> = oneWay
        override val backWay: Mapper<R, T> = backWay
    }
}
