package xunit

import io.kotlintest.shouldBe
import org.junit.Test

class TestCaseTest(name: String): TestCase(name) {
    private lateinit var test: WasRun

    override fun setUp() {
        test = WasRun("testMethod")
    }

    fun testRunning() {
        test.wasRun shouldBe false

        test.run()
        test.wasRun shouldBe true
    }

    fun testSetUp() {
        test.run()
        test.wasSetUp shouldBe true
    }
}

class Tests {
    @Test
    fun testRunning() {
        TestCaseTest("testRunning").run()
    }

    @Test
    fun testSetUp() {
        TestCaseTest("testSetUp").run()
    }
}