package ua.railian.mapper

/**
 * Mapper to map entity in both directions:
 * forward - from [T] to [R] and backward - from [R] to [T].
 */
public interface TwoWayMapper<T, R, F, B>
        where  F : OneWayMapper<out T, out R>,
               B : OneWayMapper<out R, out T> {

    /**
     * Mapper to map entity in forward direction from [T] to [R].
     *
     * @see OneWayMapper
     */
    public val forward: F

    /**
     * Mapper to map entity in backward direction from [R] to [T].
     *
     * @see OneWayMapper
     */
    public val backward: B
}

/**
 * Creates [TwoWayMapper] from two [OneWayMapper]s.
 *
 * @param forward mapper to map entity from [T] to [R]
 * @param backward mapper to map entity from [R] to [T]
 * @return mapper to map entities in both directions
 */
public fun <T, R, F, B> twoWayMapper(
    forward: F,
    backward: B,
): TwoWayMapper<out T, out R, F, B>
        where  F : OneWayMapper<out T, out R>,
               B : OneWayMapper<out R, out T> {
    return object : TwoWayMapper<T, R, F, B> {
        override val forward: F = forward
        override val backward: B = backward
    }
}
