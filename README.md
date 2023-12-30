# mapper
[![Maven Central](https://img.shields.io/maven-central/v/io.github.railian.mapper/mapper.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.railian.mapper%22%20AND%20a:%22mapper%22)

Simple library that helps to create an easy mappers from one entity to another.

One way mapper
```kotlin
val mapper = oneWayMapper<String, Int?> { it.toIntOrNull() }
val value = mapper.map(source = "12") // 12
```

Two way mapper
```kotlin
val mapper = twoWayMapper(
    forward = oneWayMapper<String, Int?> { it.toIntOrNull() },
    backward = oneWayMapper<Int, String> { it.toString() },
)
val intValue = mapper.forward.map(source = "34") // 34
val stringValue = mapper.backward.map(source = 56) // "56"
```
 
Enum two way mapper
```kotlin
enum class TestEnum { ONE, TWO, UNKNOWN }
```
```kotlin
val mapper = enumTwoWayMapper(
    default = TestEnum.UNKNOWN,
    associate = {
        when (it) {
            TestEnum.ONE -> 1
            TestEnum.TWO -> 2
            TestEnum.UNKNOWN -> null
        }
    },
)
val value = mapper.forward.map(source = TestEnum.ONE) // 1
val enum = mapper.backward.map(source = 2) // TWO
val fallback = mapper.backward.map(source = 123) // UNKNOWN
```

You can also use using delegate to create mapper class
```kotlin
class StringToIntMapper : Mapper<String, Int?> by Mapper({ it.toIntOrNull() })
```
```kotlin
class TestEnumMapper : EnumTwoWayMapper<TestEnum, Int?> by enumTwoWayMapper(
    default = TestEnum.UNKNOWN,
    associate = {
        when (it) {
            TestEnum.ONE -> 1
            TestEnum.TWO -> 2
            TestEnum.UNKNOWN -> null
        }
    },
)
```

## Using in your projects
### Maven
Add dependencies (you can also add other modules that you need) and make sure that you use the correct version:

```xml
<dependency>
    <groupId>io.github.railian.mapper</groupId>
    <artifactId>mapper</artifactId>
    <version>0.1.4</version>
</dependency>
```

### Gradle
Add dependencies (you can also add other modules that you need) and make sure that you use the correct version:

```kotlin
dependencies {
    implementation("io.github.railian.mapper:mapper:0.1.4")
}
```
Make sure that you have mavenCentral() in the list of repositories:

```kotlin
repositories {
    mavenCentral()
}
```
