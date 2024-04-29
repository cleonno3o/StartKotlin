// 1번
class Calc1 {
    var res: Int = 0
        get() {
            println("res는" + field)
            return field
        }
        set(value) {
            field = value
            println("중간 결과: " + field)
        }

    fun add(imm: Int) {
        res += imm
    }
    fun sub(imm: Int) {
        res -= imm
    }
    fun div(imm: Int) {
        res /= imm
    }
    fun mul(imm: Int) {
        res *= imm
    }
}
val cal1 = Calc1()
cal1.add(10)
cal1.sub(5)
cal1.div(2)
cal1.mul(3)

// 2번
class Calc2(initVal: Int = 0){
    var res: Int = initVal
        set(value) {
            field = value
            println("결과값: $field")
        }

    fun calculate(op: Char, imm: Int) {
        if (op == '+') {
            res += imm
        } else if (op == '-') {
            res -= imm
        } else if (op == '*') {
            res *= imm
        } else if (op == '/' && imm != 0) {
            res /= imm
        } else {
            println("잘못된 연산 입니다")
        }
    }
}
val cal = Calc2(19)
cal.calculate('-',100)
cal.calculate('/',0)

// 3번
class Calc3(initVal: Int = 0){
    var res: Int = initVal
        set(value) {
            field = value
            println("결과값: $field")
        }

    fun calculate(op: List<Char>, imm: List<Int>) {
        for (i in op.indices) {
            val currOp: Char = op[i]
            val currImm: Int = imm[i]
            if (currOp == '+') {
                res += currImm
            } else if (currOp == '-') {
                res -= currImm
            } else if (currOp == '*') {
                res *= currImm
            } else if (currOp == '/' && currImm != 0) {
                res /= currImm
            } else {
                println("잘못된 연산 입니다")
            }
        }
    }
}

val call = Calc3(0)
val ops = listOf('+','-','*','/')
val imms = listOf(10,20,30,3)
call.calculate(ops, imms)