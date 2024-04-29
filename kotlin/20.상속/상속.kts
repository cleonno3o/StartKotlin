class SumClass {
    fun sum(): Int {
        return 10
    }

    fun sum(number1: Int): Int {
        return number1 + 10
    }

    fun sum(number1: Int, number2: Int): Int {
        return number1 + number2 + 10
    }
}

val s = SumClass()
println(s.sum())
println(s.sum(10))
println(s.sum(10,10))
open class MySuperClass(var name: String, var power: Int, var type: String) {
    open fun attack() {
        println("공격!")
    }
    open fun getPower(): Int {
        return power
    }
}

class MySubClass(name: String, power: Int) : MySuperClass(name, power, "공대생") {
    fun defense() {
        println("방어!")
    }

    override fun attack() {
        super.attack()
        println("오버라이딩")
    }

    override fun getPower(): Int {
        return super.getPower() + 5
    }
}
