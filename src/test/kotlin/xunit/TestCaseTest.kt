package xunit

import io.kotlintest.shouldBe
import org.junit.Test

class TestCaseTest(name: String): TestCase(name) {
    override fun setUp() {
    }

    fun testTemplateMethod() {
        val test = WasRun("testMethod")
        test.run()

        test.log shouldBe "setUp testMethod tearDown "
    }
}

class Tests {
    @Test
    fun testRunning() {
        TestCaseTest("testTemplateMethod").run()
    }
}