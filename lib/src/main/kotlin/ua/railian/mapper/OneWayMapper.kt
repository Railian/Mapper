package ua.railian.mapper

/**
 * Mapper to map entity from [T] to [R].
 *
 * @param T source entity type
 * @param R destination entity type
 */
public sealed interface OneWayMapper<T, R>

/**
 * Simple mapper to map entity from [T] to [R].
 *
 * @param T source entity type
 * @param R destination entity type
 */
public fun interface Mapper<T, R> : OneWayMapper<T, R> {

    /**
     * Map entity from [T] to [R].
     *
     * @param source entity to map
     * @return mapped entity
     */
    public operator fun invoke(source: T): R
}

/**
 * Mapper to map entity from [T] to [R] with 1 additional parameter [P1].
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 additional parameter type
 */
public fun interface MapperWith1Param<T, R, P1> : OneWayMapper<T, R> {

    /**
     * Map entity from [T] to [R] with 1 additional parameter [P1].
     *
     * @param source entity to map
     * @param p1 additional parameter
     * @return mapped entity
     */
    public operator fun invoke(source: T, p1: P1): R
}

/**
 * Mapper to map entity from [T] to [R] with 2 additional parameters [P1] & [P2].
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 first additional parameter type
 * @param P2 second additional parameter type
 */
public fun interface MapperWith2Params<T, R, P1, P2> : OneWayMapper<T, R> {

    /**
     * Map entity from [T] to [R] with 2 additional parameters [P1] & [P2].
     *
     * @param source entity to map
     * @param p1 first additional parameter
     * @param p2 second additional parameter
     * @return mapped entity
     */
    public operator fun invoke(source: T, p1: P1, p2: P2): R
}

/**
 * Mapper to map entity from [T] to [R] with 3 additional parameters [P1], [P2] & [P3].
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 first additional parameter type
 * @param P2 second additional parameter type
 * @param P3 third additional parameter type
 */
public fun interface MapperWith3Params<T, R, P1, P2, P3> : OneWayMapper<T, R> {

    /**
     * Map entity from [T] to [R] with 3 additional parameters [P1], [P2] & [P3].
     *
     * @param source entity to map
     * @param p1 first additional parameter
     * @param p2 second additional parameter
     * @param p3 third additional parameter
     * @return mapped entity
     */
    public operator fun invoke(source: T, p1: P1, p2: P2, p3: P3): R
}

/**
 * Mapper to map entity from [T] to [R] with 4 additional parameters [P1], [P2], [P3] & [P4].
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 first additional parameter type
 * @param P2 second additional parameter type
 * @param P3 third additional parameter type
 * @param P4 fourth additional parameter type
 */
public fun interface MapperWith4Params<T, R, P1, P2, P3, P4> : OneWayMapper<T, R> {

    /**
     * Map entity from [T] to [R] with 4 additional parameters [P1], [P2], [P3] & [P4].
     *
     * @param source entity to map
     * @param p1 first additional parameter
     * @param p2 second additional parameter
     * @param p3 third additional parameter
     * @param p4 fourth additional parameter
     * @return mapped entity
     */
    public operator fun invoke(source: T, p1: P1, p2: P2, p3: P3, p4: P4): R
}

/**
 * Mapper to map entity from [T] to [R] with 5 additional parameters [P1], [P2], [P3], [P4] & [P5].
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 first additional parameter type
 * @param P2 second additional parameter type
 * @param P3 third additional parameter type
 * @param P4 fourth additional parameter type
 * @param P5 fifth additional parameter type
 */
public fun interface MapperWith5Params<T, R, P1, P2, P3, P4, P5> : OneWayMapper<T, R> {

    /**
     * Map entity from [T] to [R] with 5 additional parameters [P1], [P2], [P3], [P4] & [P5].
     *
     * @param source entity to map
     * @param p1 first additional parameter
     * @param p2 second additional parameter
     * @param p3 third additional parameter
     * @param p4 fourth additional parameter
     * @param p5 fifth additional parameter
     * @return mapped entity
     */
    public operator fun invoke(source: T, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5): R
}

/**
 * Creates simple [OneWayMapper].
 *
 * @param T source entity type
 * @param R destination entity type
 * @param map mapping function
 * @return [Mapper]
 */
public fun <T, R> oneWayMapper(
    map: (source: T) -> R,
): Mapper<T, R> {
    return Mapper(map)
}

/**
 * Creates [OneWayMapper] with 1 additional parameter.
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 additional parameter type
 * @param map mapping function
 * @return [MapperWith1Param]
 */
public fun <T, R, P1> oneWayMapper(
    map: (source: T, P1) -> R,
): MapperWith1Param<T, R, P1> {
    return MapperWith1Param(map)
}

/**
 * Creates [OneWayMapper] with 2 additional parameters.
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 first additional parameter type
 * @param P2 second additional parameter type
 * @param map mapping function
 * @return [MapperWith2Params]
 */
public fun <T, R, P1, P2> oneWayMapper(
    map: (source: T, P1, P2) -> R,
): MapperWith2Params<T, R, P1, P2> {
    return MapperWith2Params(map)
}

/**
 * Creates [OneWayMapper] with 3 additional parameters.
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 first additional parameter type
 * @param P2 second additional parameter type
 * @param P3 third additional parameter type
 * @param map mapping function
 * @return [MapperWith3Params]
 */
public fun <T, R, P1, P2, P3> oneWayMapper(
    map: (source: T, P1, P2, P3) -> R,
): MapperWith3Params<T, R, P1, P2, P3> {
    return MapperWith3Params(map)
}

/**
 * Creates [OneWayMapper] with 4 additional parameters.
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 first additional parameter type
 * @param P2 second additional parameter type
 * @param P3 third additional parameter type
 * @param P4 fourth additional parameter type
 * @param map mapping function
 * @return [MapperWith4Params]
 */
public fun <T, R, P1, P2, P3, P4> oneWayMapper(
    map: (source: T, P1, P2, P3, P4) -> R,
): MapperWith4Params<T, R, P1, P2, P3, P4> {
    return MapperWith4Params(map)
}


/**
 * Creates [OneWayMapper] with 5 additional parameters.
 *
 * @param T source entity type
 * @param R destination entity type
 * @param P1 first additional parameter type
 * @param P2 second additional parameter type
 * @param P3 third additional parameter type
 * @param P4 fourth additional parameter type
 * @param P5 fifth additional parameter type
 * @param map mapping function
 * @return [MapperWith5Params]
 */
public fun <T, R, P1, P2, P3, P4, P5> oneWayMapper(
    map: (source: T, P1, P2, P3, P4, P5) -> R,
): MapperWith5Params<T, R, P1, P2, P3, P4, P5> {
    return MapperWith5Params(map)
}
