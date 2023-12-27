package ua.railian.mapper.contract

import kotlin.enums.EnumEntries
import kotlin.enums.enumEntries

public interface EnumMapper<E, R> : TwoWayMapper<E, R> where E : Enum<E> {
    public val default: E
}

public fun <E, R> enumMapper(
    default: E,
    enumEntries: EnumEntries<E>,
    associate: (enum: E) -> R,
): EnumMapper<E, R> where E : Enum<E> {
    return object : EnumMapper<E, R> {
        val associations by lazy { enumEntries.associateWith(associate) }
        override val default = default
        override val oneWay: Mapper<E, R> = Mapper(associate)
        override val backWay: Mapper<R, E> = Mapper { value ->
            val valueAssociations = associations.filterValues { value == it }
            when {
                valueAssociations.isEmpty() -> default
                else -> valueAssociations.entries.first().key
            }
        }
    }
}

@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified E, R> enumMapper(
    default: E,
    noinline associate: (enum: E) -> R,
): EnumMapper<E, R> where E : Enum<E> {
    return enumMapper(
        default = default,
        enumEntries = enumEntries<E>(),
        associate = associate,
    )
}
