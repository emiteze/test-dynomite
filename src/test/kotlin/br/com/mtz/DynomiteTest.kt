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

        val node = "node1"
        val rack = "rack1"
        val value = "test5"

        println("Saving new record to $node in $rack ---- ${dynoClient.set(value, value)}")
        println("Trying to get $value from $node in $rack ---- ${dynoClient.get(value)}")

    }

}