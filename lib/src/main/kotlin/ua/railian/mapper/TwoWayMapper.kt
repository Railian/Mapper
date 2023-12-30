package ua.railian.mapper

/**
 * Mapper that can map both directions:
 * forward - from [T] to [R] and backward - from [R] to [T].
 */
public interface TwoWayMapper<T, R> {

    /**
     * Mapper to map object in forward direction from [T] to [R].
     *
     * @see Mapper
     */
    public val forward: Mapper<T, R>

    /**
     * Mapper to map object in backward direction from [R] to [T].
     *
     * @see Mapper
     */
    public val backward: Mapper<R, T>

    /**
     * Map object in forward direction from [T] to [R].
     *
     * @param source object to map
     * @return mapped object
     */
    public fun mapForward(source: T): R = forward.map(source)

    /**
     * Map object in backward direction from [R] to [T].
     *
     * @param source object to map
     * @return mapped object
     */
    public fun mapBackward(source: R): T = backward.map(source)
}

/**
 * Create [TwoWayMapper] from two simple [Mapper]s.
 *
 * @param forward mapper to map object from [T] to [R]
 * @param backward mapper to map object from [R] to [T]
 * @return mapper that can map both directions
 */
public fun <T, R> twoWayMapper(
    forward: Mapper<T, R>,
    backward: Mapper<R, T>,
): TwoWayMapper<T, R> {
    return object : TwoWayMapper<T, R> {
        override val forward: Mapper<T, R> = forward
        override val backward: Mapper<R, T> = backward
    }
}
