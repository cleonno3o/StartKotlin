open class Something(initHp: Int, initAtk: Int, initDef: Int, initType: String) {
    open var hp = initHp
    var atk = initAtk
    var def = initDef
    var type = initType
    open fun attack(target: Something) {
        println("${this.type}가 공격합니다.")
        target.hp -= this.atk
    }
}

open class Warrior(initHp: Int, initAtk: Int, initDef: Int, initType: String)
    : Something(initHp, initAtk, initDef, initType) {
    var lvUp = 3
    var kill: Int = 0
        set(value) {
            field = value
            if (field > lvUp) {

            }
        }

    override fun attack(target: Something) {
        super.attack(target)
        target.
    }
}

class Knight(initHp: Int, initAtk: Int, initDef: Int, initEnergy: Int, initType: String)
    : Warrior(initHp, initAtk, initDef, initType) {
    var energy: Int = initEnergy
    fun hardAttack() {

    }
}

class Monster(initHp: Int, initAtk: Int, initDef: Int, initType: String)
    : Something(initHp, initAtk, initDef, initType) {
    override var hp: Int = 0
        get() = super.hp
        set(value) {
            field = value
            if (field > 0) {
                println("Monster 아직 살아 있습니다. 체력: $field")
            }
            else {
                println("Monster가 죽었습니다.")
            }
        }
    init {
        println("Monster 생성")
        hp = initHp
    }

}