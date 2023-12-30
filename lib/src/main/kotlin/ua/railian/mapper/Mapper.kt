package ua.railian.mapper

/**
 * Simple mapper to map object from one type to another.
 *
 * @param T source object type
 * @param R destination object type
 */
public fun interface Mapper<T, R> {

    /**
     * Map object from one type to another.
     *
     * @param source object to map
     * @return mapped object
     */
    public fun map(source: T): R
}
