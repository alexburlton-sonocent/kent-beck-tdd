package xunit

import io.kotlintest.shouldBe
import org.junit.Test

class TestCaseTest(name: String): TestCase(name) {
    fun testRunning() {
        val test = WasRun("testMethod")
        test.wasRun shouldBe false

        test.run()
        test.wasRun shouldBe true
    }
}

class Tests {
    @Test
    fun testRunning() {
        TestCaseTest("testRunning").run()
    }
}