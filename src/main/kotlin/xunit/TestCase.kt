package xunit

open class TestCase(val name: String) {
    fun run() {
        javaClass.getMethod(name).invoke(this)
    }
}