package ua.railian.mapper

import kotlin.enums.EnumEntries
import kotlin.enums.enumEntries

public interface EnumTwoWayMapper<E, R> : TwoWayMapper<E, R> where E : Enum<E> {
    public val default: E
}

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
