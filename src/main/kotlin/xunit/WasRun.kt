package xunit

class WasRun(name: String): TestCase(name)
{
    var wasRun = false
    var wasSetUp = false

    fun testMethod() {
        wasRun = true
    }

    override fun setUp() {
        wasRun = false
        wasSetUp = true
    }
}