// WITH_RUNTIME

fun testContinue1() {
    for (i in 0..1) {
        for (j in continue downTo 1) {}
    }
}

fun testContinue2() {
    for (i in 0..1) {
        for (j in 1 downTo continue) {}
    }
}

fun falseCond() = false

fun testContinue3() {
    for (i in 0..1) {
        for (j in (if (falseCond()) 10 else continue) downTo 1) {}
    }
}

fun testContinue4() {
    for (i in 0..1) {
        for (j in 10 downTo (if (falseCond()) 1 else continue)) {}
    }
}

fun testContinue5() {
    for (i in 0..1) {
        for (j in (
                if (try {
                        if (falseCond()) true else return
                    } finally {
                        if (!falseCond()) continue
                    }
                )
                    10
                else
                    continue
                )
                downTo 1) {
            //...
        }
    }
}

fun box(): String {
    testContinue1()
    testContinue2()
    testContinue3()
    testContinue4()
    testContinue5()
    return "OK"
}