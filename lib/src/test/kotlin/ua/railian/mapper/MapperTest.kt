package ua.railian.mapper

import kotlin.test.Test
import kotlin.test.assertEquals

class MapperTest {

    @Test
    fun testOneWayMapper() {
        val stringToIntMapper = oneWayMapper<String, Int?> { it.toIntOrNull() }
        assertEquals(expected = 0, actual = stringToIntMapper.map(source = "0"))
        assertEquals(expected = 123, actual = stringToIntMapper.map(source = "123"))
        val intToStringMapper = oneWayMapper<Int, String> { it.toString() }
        assertEquals(expected = "0", actual = intToStringMapper.map(source = 0))
        assertEquals(expected = "123", actual = intToStringMapper.map(source = 123))
    }

    @Test
    fun testTwoWayMapper() {
        val stringIntMapper = twoWayMapper(
            forward = oneWayMapper<String, Int?> { it.toIntOrNull() },
            backward = oneWayMapper<Int, String> { it.toString() },
        )
        assertEquals(expected = 0, actual = stringIntMapper.forward.map(source = "0"))
        assertEquals(expected = 123, actual = stringIntMapper.forward.map(source = "123"))
        assertEquals(expected = "0", actual = stringIntMapper.backward.map(source = 0))
        assertEquals(expected = "123", actual = stringIntMapper.backward.map(source = 123))
    }

    private enum class TestEnum { ONE, TWO, THREE, FOUR, UNKNOWN }

    @Test
    fun testEnumTwoWayMapper() {
        val mapper = enumTwoWayMapper(
            default = TestEnum.UNKNOWN,
            associate = {
                when (it) {
                    TestEnum.ONE -> 1
                    TestEnum.TWO -> 2
                    TestEnum.THREE -> 3
                    TestEnum.FOUR -> 4
                    TestEnum.UNKNOWN -> null
                }
            },
        )
        assertEquals(expected = null, actual = mapper.forward.map(source = TestEnum.UNKNOWN))
        assertEquals(expected = 1, actual = mapper.forward.map(source = TestEnum.ONE))
        assertEquals(expected = 2, actual = mapper.forward.map(source = TestEnum.TWO))
        assertEquals(expected = TestEnum.THREE, actual = mapper.backward.map(source = 3))
        assertEquals(expected = TestEnum.FOUR, actual = mapper.backward.map(source = 4))
        assertEquals(expected = TestEnum.UNKNOWN, actual = mapper.backward.map(source = 123))
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    class IntToStringMapper : Mapper<Int, String>,
        MapperWith1Param<Int, String, Int>,
        MapperWith2Params<Int, String, Int, Boolean> {

        override fun map(source: Int): String {
            return map(source = source, radix = 10)
        }

        override fun map(source: Int, radix: Int): String {
            return source.toString(radix)
        }

        override fun map(source: Int, radix: Int, uppercase: Boolean): String {
            return source.toString(radix).run { if (uppercase) uppercase() else this }
        }
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    class StringToIntMapper : Mapper<String, Int?>,
        MapperWith1Param<String, Int?, Int> {

        override fun map(source: String, radix: Int): Int? {
            return source.toIntOrNull(radix)
        }

        override fun map(source: String): Int? {
            return map(source = source, radix = 10)
        }
    }

    @Test
    fun testTwoWayMapperWithParams() {
        val mapper = twoWayMapper(
            StringToIntMapper(),
            IntToStringMapper(),
        )
        assertEquals(expected = 244, actual = mapper.forward.map(source = "244"))
        assertEquals(expected = 255, actual = mapper.forward.map(source = "FF", radix = 16))
        assertEquals(expected = "12", actual = mapper.backward.map(source = 12))
        assertEquals(expected = "9c", actual = mapper.backward.map(source = 156, radix = 16))
        assertEquals(
            expected = "D3",
            actual = mapper.backward.map(source = 211, radix = 16, uppercase = true),
        )
    }
}
