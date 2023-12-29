package ua.railian.mapper

import kotlin.test.Test
import kotlin.test.assertEquals

class MapperTest {

    @Test
    fun testMapper() {
        val mapper1 = Mapper<String, Int?> { it.filter(Char::isDigit).toIntOrNull() }
        assertEquals(expected = 0, actual = mapper1.map(source = "qwer00rty00-=*"))
        assertEquals(expected = 123456789, actual = mapper1.map(source = "1q2w3e4r5r6t7y8-9=*"))
        val mapper2 = Mapper<Int, String> { "**$it**" }
        assertEquals(expected = "**0**", actual = mapper2.map(source = 0))
        assertEquals(expected = "**123**", actual = mapper2.map(source = 123))
    }

    @Test
    fun testTwoWayMapper() {
        val mapper = twoWayMapper<String?, Int?>(
            forward = { it?.filter(Char::isDigit)?.toIntOrNull() },
            backward = { it?.toString() },
        )
        assertEquals(expected = 0, actual = mapper.mapForward(source = "qwer00rty00-=*"))
        assertEquals(expected = 123456789, actual = mapper.mapForward(source = "1q2w3e4r5r6t7y8-9=*"))
        assertEquals(expected = "0", actual = mapper.mapBackward(source = 0))
        assertEquals(expected = "123", actual = mapper.mapBackward(source = 123))
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
        assertEquals(expected = null, actual = mapper.mapForward(source = TestEnum.UNKNOWN))
        assertEquals(expected = 1, actual = mapper.mapForward(source = TestEnum.ONE))
        assertEquals(expected = 2, actual = mapper.mapForward(source = TestEnum.TWO))
        assertEquals(expected = TestEnum.THREE, actual = mapper.mapBackward(source = 3))
        assertEquals(expected = TestEnum.FOUR, actual = mapper.mapBackward(source = 4))
        assertEquals(expected = TestEnum.UNKNOWN, actual = mapper.mapBackward(source = 123))
    }
}