import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.lepinoid.uuidserializer.UuidSerializer
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun commonSerializer() {
        val uuid = uuid4()
        val json = Json.encodeToString(UuidSerializer, uuid)
        val decoded = Json.decodeFromString(UuidSerializer, json)
        assertEquals(uuid, decoded)
    }

    @Test
    fun dataClassSerializer() {
        val data = TestData(uuid4(), "this is uuid!!")
        val json = Json.encodeToString(TestData.serializer(), data)
        val decoded = Json.decodeFromString<TestData>(TestData.serializer(), json)
        assertEquals(data, decoded)
    }

    @Serializable
    data class TestData(
        @Serializable(with = UuidSerializer::class)
        val uuid: Uuid,
        val some: String
    )
}