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

        print("Testing client --------- ${dynoClient.applicationName}")

    }

}