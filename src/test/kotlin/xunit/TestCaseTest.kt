package xunit

import io.kotlintest.shouldBe
import org.junit.Test

class TestCaseTest(name: String): TestCase(name) {
    override fun setUp() {
    }

    fun testTemplateMethod() {
        val test = WasRun("testMethod")
        test.run(TestResult())

        test.log shouldBe "setUp testMethod tearDown "
    }

    fun testResult() {
        val test = WasRun("testMethod")
        val result = TestResult()
        test.run(result)

        result.summary() shouldBe "1 run, 0 failed"
    }

    fun testFailedResult() {
        val test = WasRun("testBrokenMethod")
        val result = TestResult()
        test.run(result)

        result.summary() shouldBe "1 run, 1 failed"
    }

    fun testFailedResultFormatting() {
        val result = TestResult()
        result.testStarted()
        result.testFailed()

        result.summary() shouldBe "1 run, 1 failed"
    }

    fun testSuite() {
        val suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("testBrokenMethod"))
        val result = TestResult()
        suite.run(result)
        result.summary() shouldBe "2 run, 1 failed"
    }
}

/**
 * Actually use JUnit to drive the above methods, like how he's running them from main() in python
 */
class Tests {
    @Test
    fun testTemplateMethod() {
        TestCaseTest("testTemplateMethod").run(TestResult())
    }

    @Test
    fun testResult() {
        TestCaseTest("testResult").run(TestResult())
    }

    @Test
    fun testBrokenMethod() {
        TestCaseTest("testBrokenMethod").run(TestResult())
    }

    @Test
    fun testSuite() {
        TestCaseTest("testSuite").run(TestResult())
    }
}