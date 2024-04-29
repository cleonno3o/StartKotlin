val number: Int = 10
val numberString: String = number.toString()

open class Warrior1(var name: String, var power: Int, var type: String) { //부모클래스, 슈퍼클래스
    open fun attack() {
        println("복잡한 코드 + 공격")
    }
}

class DefenseWarrior1 constructor(name: String, power: Int) : Warrior1(name, power, "고블린") {
    fun defense() {
        println("방어")
    }
}
var w1: Warrior1 = DefenseWarrior1("수민", 10)
var war: DefenseWarrior1 = w1 as DefenseWarrior1
war.defense()