package xunit

class WasRun(name: String): TestCase(name)
{
    var log = ""

    fun testMethod() {
        log += "testMethod "
    }

    override fun setUp() {
        log = "setUp "
    }

    override fun tearDown() {
        log += "tearDown "
    }
}