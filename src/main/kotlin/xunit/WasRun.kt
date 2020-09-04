package xunit

class WasRun(name: String): TestCase(name)
{
    var log = ""

    fun testMethod() {
        log += "testMethod "
    }

    fun testBrokenMethod() {
        log += "testMethod "
        throw Exception("Boom.")
    }

    override fun setUp() {
        log = "setUp "
    }

    override fun tearDown() {
        log += "tearDown "
    }
}