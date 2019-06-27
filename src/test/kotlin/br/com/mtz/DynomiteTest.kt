package br.com.mtz

import com.netflix.dyno.jedis.DynoJedisClient
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class DynomiteTest {

    @Autowired
    private lateinit var dynoClient: DynoJedisClient

    @Test
    fun testInsertionInDynomite(){

        println("Trying to get mtz ---- ${dynoClient.get("mtz")}")
        println("Trying to get brn ---- ${dynoClient.get("brn")}")
        println("Trying to get htk ---- ${dynoClient.get("htk")}")
        println("Trying to get palco_verde ---- ${dynoClient.get("palco_verde")}")

    }

}