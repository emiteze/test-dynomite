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
        val value = "test1"

        println("Saving new record to $node in $rack ---- ${dynoClient.set(value, value)}")
        println("Trying to get $value from $node in $rack ---- ${dynoClient.get(value)}")

    }

    @Test
    fun testDynoClient(){

        println("Inserting registers on dyno cluster")
        dynoClient.set("test1", "test1")
        dynoClient.set("test2", "test2")
        dynoClient.set("test3", "test3")

        println("Getting nodes from dyno cluster")
        println(dynoClient.get("test1"))
        println(dynoClient.get("test2"))
        println(dynoClient.get("test3"))

        println("Sleeping thread for 30 seconds")

        Thread.sleep(30000)

        println("Trying to insert register on dyno cluster")
        dynoClient.set("test4", "test4")

    }

}