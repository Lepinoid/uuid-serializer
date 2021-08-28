# uuid-serializer

:book: Simple [Uuid](https://github.com/benasher44/uuid) serializer library

# Gradle

KotlinDSL:

```kotlin
repositories {
     maven { 
         name = "lepinoid"
         url = uri("https://lepinoid.github.io/maven-repo/")
     }
}

dependencies {
  implementation("net.lepinoid:uuid-serializer:1.0")
}
```

### Multiplatform:

```kotlin
commonMain {
    dependencies {
        implementation("net.lepinoid:uuid-serializer:1.0")
    }
}
```



# Examples

```kotlin
@Serializable
class SomeClass(
    @Serializable(with = UuidSerialzier::class)
    val uuid: Uuid
)
```

```kotlin
fun main() {
    val uuid = uuid4()
    val json = Json.encodeToString(UuidSerializer, uuid)
    val decoded = Json.decodeFromString<Uuid>(UuidSerializer, json)
}
```

