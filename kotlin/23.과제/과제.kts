open class Something(initHp: Int, initAtk: Int, initDef: Int, initType: String) {
    var hp = initHp
        set(value) {
            field = value
            if (field > 0) {
                println("$type 아직 살아 있습니다. 체력: $field")
            }
            else {
                println("$type 죽었습니다.")
            }
        }
    var atk = initAtk
    var def = initDef
    var type = initType
    var kill = 0
    var alive = true
    fun attack(target: Something) {
        println("${this.type}가 공격합니다.")
        if (!target.alive) {
            println("이미 죽은 대상입니다.")
            return
        }
        target.hp -= (this.atk - target.def)
        if (target.hp <= 0) {
            target.alive = false
            kill++
        }
    }
}

open class Warrior(initHp: Int, initAtk: Int, initDef: Int, initType: String = "Warrior")
    : Something(initHp, initAtk, initDef, initType) {
    val lvUp = 2
}

class Knight(initHp: Int, initAtk: Int, initDef: Int, initEnergy: Int, initType: String = "Knight")
    : Warrior(initHp, initAtk, initDef, initType) {
    var energy: Int = initEnergy
    fun hardAttack(target: Something) {
        if (energy >= 3) {
            println("에너지를 소모하여 공격력이 2배가 됩니다.")
            this.atk *= 2
            this.attack(target)
            this.atk /= 2
            this.energy -= 3
            println("공격력이 다시 원상태로 돌아옵니다.")
        } else println("에너지가 ${3 - energy} 부족합니다.")
    }
}

class Monster(initHp: Int, initAtk: Int, initDef: Int, initType: String = "Monster")
    : Something(initHp, initAtk, initDef, initType) {
    init {
        println("Monster 생성")
        hp = initHp
    }

}

val mon1 = Monster(50, 5, 3)
val mon2 = Monster(50, 5, 3)
val mon3 = Monster(50, 5, 3)

val warrior = Warrior(100, 40, 2)

warrior.attack(mon1)
warrior.attack(mon1)
warrior.attack(mon2)
warrior.attack(mon2)
warrior.attack(mon3)
warrior.attack(mon3)

val knight =