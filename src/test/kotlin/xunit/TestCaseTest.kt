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

    fun testResult() {
        val test = WasRun("testMethod")
        val result = test.run()

        result.summary() shouldBe "1 run, 0 failed"
    }

    fun testFailedResult() {
        val test = WasRun("testBrokenMethod")
        val result = test.run()

        result.summary() shouldBe "1 run, 1 failed"
    }
}

/**
 * Actually use JUnit to drive the above methods, like how he's running them from main() in python
 */
class Tests {
    @Test
    fun testTemplateMethod() {
        TestCaseTest("testTemplateMethod").run()
    }

    @Test
    fun testResult() {
        TestCaseTest("testResult").run()
    }

    @Test
    fun testBrokenMethod() {
        TestCaseTest("testBrokenMethod").run()
    }
}