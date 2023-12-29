package ua.railian.mapper

public interface TwoWayMapper<T, R> {
    public val forward: Mapper<T, R>
    public val backward: Mapper<R, T>
    public fun mapForward(source: T): R = forward.map(source)
    public fun mapBackward(source: R): T = backward.map(source)
}

public fun <T, R> twoWayMapper(
    forward: Mapper<T, R>,
    backward: Mapper<R, T>,
): TwoWayMapper<T, R> {
    return object : TwoWayMapper<T, R> {
        override val forward: Mapper<T, R> = forward
        override val backward: Mapper<R, T> = backward
    }
}
