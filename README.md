# mapper
[![maven-central](https://maven-badges.herokuapp.com/maven-central/io.github.railian.mapper/mapper/badge.svg?style=default&color=blue)](https://maven-badges.herokuapp.com/maven-central/io.github.railian.mapper/mapper)

Simple library that helps to create an easy mappers from one entity to another.

One way mapper
```kotlin
val mapper = Mapper<String, Int?> { it.toIntOrNull() }
val value = mapper.map(source = "12") // 12
```

Two way mapper
```kotlin
val mapper = twoWayMapper<String?, Int?>(
    forward = { it?.toIntOrNull() },
    backward = { it?.toString() },
)
val intValue = mapper.mapForward(source = "34") // 34
val stringValue = mapper.mapBackward(source = 56) // "56"
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
val value = mapper.mapForward(source = TestEnum.ONE) // 1
val enum = mapper.mapBackward(source = 2) // TWO
val fallback = mapper.mapBackward(source = 123) // UNKNOWN
```

## Using in your projects
### Maven
Add dependencies (you can also add other modules that you need) and make sure that you use the correct version:

```xml
<dependency>
    <groupId>io.github.railian.mapper</groupId>
    <artifactId>mapper</artifactId>
    <version>0.1.3</version>
</dependency>
```

### Gradle
Add dependencies (you can also add other modules that you need) and make sure that you use the correct version:

```kotlin
dependencies {
    implementation("io.github.railian.mapper:mapper:0.1.3")
}
```
Make sure that you have mavenCentral() in the list of repositories:

```kotlin
repositories {
    mavenCentral()
}
```
