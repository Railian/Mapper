package ua.railian.mapper

import kotlin.enums.EnumEntries
import kotlin.enums.enumEntries

/**
 * Mapper that can map enums in both directions.
 *
 * @param E enum type
 * @param R some value type
 * @see TwoWayMapper
 */
public interface EnumTwoWayMapper<E, R> : TwoWayMapper<E, R> where E : Enum<E> {

    /**
     * Default enum value that will be used as a fallback for unknown values.
     */
    public val default: E
}

/**
 * Creates [EnumTwoWayMapper] for all [EnumEntries] by [associate] function.
 *
 * @param E enum type
 * @param R some value type
 * @param default enum value that will be used as a fallback for unknown values
 * @param enumEntries all enum entries
 * @param associate function that pairs enum with some value
 * @return mapper that can map enums in both directions
 */
public fun <E, R> enumTwoWayMapper(
    default: E,
    enumEntries: EnumEntries<E>,
    associate: (enum: E) -> R,
): EnumTwoWayMapper<E, R> where E : Enum<E> {
    return object : EnumTwoWayMapper<E, R> {
        val associations by lazy { enumEntries.associateWith(associate) }
        override val default = default
        override val forward = Mapper(associate)
        override val backward = Mapper { value: R ->
            val valueAssociations = associations.filterValues { value == it }
            when {
                valueAssociations.isEmpty() -> default
                else -> valueAssociations.entries.first().key
            }
        }
    }
}

/**
 * Creates [EnumTwoWayMapper] for all [EnumEntries] by [associate] function.
 *
 * @param E enum type
 * @param R some value type
 * @param default enum value that will be used as a fallback for unknown values
 * @param associate function that pairs enum with some value
 * @return mapper that can map enums in both directions
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified E, R> enumTwoWayMapper(
    default: E,
    noinline associate: (enum: E) -> R,
): EnumTwoWayMapper<E, R> where E : Enum<E> {
    return enumTwoWayMapper(
        default = default,
        enumEntries = enumEntries<E>(),
        associate = associate,
    )
}
