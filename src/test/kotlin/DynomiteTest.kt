import com.fasterxml.jackson.databind.ObjectMapper
import com.netflix.dyno.connectionpool.Host
import com.netflix.dyno.connectionpool.HostSupplier
import com.netflix.dyno.connectionpool.impl.ConnectionPoolConfigurationImpl
import com.netflix.dyno.connectionpool.impl.lb.AbstractTokenMapSupplier
import com.netflix.dyno.jedis.DynoJedisClient
import org.junit.Test


class DynomiteTest {

    @Test
    fun testInsertionInDynomite(){

        val hostSupplier = CustomHostSupplier()

        val tokenSupplier = CustomTokenSupplier()

        val poolConfiguration = ConnectionPoolConfigurationImpl("dynomite_cluster")
            .setLocalDataCenter("dc1")
            .setLocalRack("rack1")
            .setMaxConnsPerHost(10)
            .withHostSupplier(hostSupplier)
            .withTokenSupplier(tokenSupplier)

        val client: DynoJedisClient = DynoJedisClient.Builder()
            .withApplicationName("DYNOMITE_TEST")
            .withDynomiteClusterName("DYNOMITE_CLUSTER")
            .withHostSupplier(hostSupplier)
            .withCPConfig(poolConfiguration)
            .build()

        print("Testing client --------- $client")

    }

}

class CustomHostSupplier : HostSupplier {

    override fun getHosts(): List<Host> {
        return listOf(
            Host("dynomite01", "0.0.0.0", 8102, "rack1", "dc1", Host.Status.Up),
            Host("dynomite02", "0.0.0.0", 8102, "rack1", "dc1", Host.Status.Up)
        )
    }

}

class CustomTokenSupplier : AbstractTokenMapSupplier() {

    val objectMapper = ObjectMapper()

    val tokenHosts = listOf<TokenHost>(
        TokenHost(token = "1383429731", hostname = "dynomite01", zone = "rack1", dc = "dc1"),
        TokenHost(token = "1383429731", hostname = "dynomite02", zone = "rack1", dc = "dc1")
    )

    val json = objectMapper.writeValueAsString(tokenHosts)

    override fun getTopologyJsonPayload(p0: MutableSet<Host>?): String {
        return json
    }

    override fun getTopologyJsonPayload(p0: String?): String {
        return json
    }

}

data class TokenHost(
    val token: String,
    val hostname: String,
    val zone: String,
    val dc: String
)