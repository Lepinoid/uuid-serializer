# uuid-serializer

> [!WARNING]
> This project archived.  
> In Kotlin 2.0.20, std and kotlinx.serialization supports Uuid.
> - https://www.jetbrains.com/guide/kotlin/links/uuid-in-kotlin-multiplatform/
> - https://github.com/Kotlin/kotlinx.serialization/releases/tag/v1.7.2

LatestVersion :arrow_right: ![](https://img.shields.io/maven-metadata/v?label=%20&metadataUrl=https%3A%2F%2Flepinoid.github.io%2Fmaven-repo%2Fnet%2Flepinoid%2Fuuid-serializer%2Fmaven-metadata.xml)

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
  implementation("net.lepinoid:uuid-serializer-jvm:$VERSION")
}
```

### Multiplatform:

```kotlin
commonMain {
    dependencies {
        implementation("net.lepinoid:uuid-serializer:$VERSION")
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

