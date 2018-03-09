package br.com.studiotrek.faculdadeimpacta.presentation.cardAccess

/**
 * Created by kleber on 09/03/2018.
 */
class EAN13CodeBuilder(codeString: String) {
    private val codeStringValue: String
    lateinit var code: String
    ////////////////////////////////////////////////////////////////
    // this method generates EAN 13 control number ans returns full
    // string to encode
    private val fullCode: String
        get() {
            var chetVal = 0
            var nechetVal = 0
            var codeToParse = codeStringValue
            for (index in 0..5) {
                chetVal += Integer.valueOf(codeToParse.substring(
                        index * 2 + 1, index * 2 + 2)).toInt()
                nechetVal += Integer.valueOf(codeToParse.substring(
                        index * 2, index * 2 + 1)).toInt()
            }
            chetVal *= 3
            var controlNumber = 10 - (chetVal + nechetVal) % 10
            if (controlNumber == 10) controlNumber = 0
            codeToParse += (controlNumber).toString()
            return codeToParse
        }

    init {
        codeStringValue = codeString
        parse()
    }

    private fun DigitToUpperCase(digit: String): String {
        val letters = "ABCDEFGHIJ"
        val position = Integer.valueOf(digit).toInt()
        val retVal = letters.substring(position, position + 1)
        return retVal
    }

    private fun DigitToLowerCase(digit: String): String {
        val letters = "abcdefghij"
        val position = Integer.valueOf(digit).toInt()
        val retVal = letters.substring(position, position + 1)
        return retVal
    }

    //////////////////////////////////////////////
    // this method generates EAN 13 encoded string
    // algorithm can be found at http://en.wikipedia.org/wiki/EAN-13
    private fun createEAN13Code(rawCode: String): String {
        val firstFlag = Integer.valueOf(
                rawCode.substring(0, 1)
        ).toInt()
        val leftString = rawCode.substring(1, 7)
        val rightString = rawCode.substring(7)
        var rightCode = ""
        var leftCode = ""
        for (i in 0..5) {
            rightCode += DigitToLowerCase(rightString.substring(i, i + 1))
        }
        if (firstFlag == 0) {
            leftCode = ("#!" + leftString.substring(0, 1)
                    + leftString.substring(1, 2)
                    + leftString.substring(2, 3)
                    + leftString.substring(3, 4)
                    + leftString.substring(4, 5)
                    + leftString.substring(5))
        }
        if (firstFlag == 1) {
            leftCode = ("$!" + leftString.substring(0, 1)
                    + leftString.substring(1, 2)
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + leftString.substring(3, 4)
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + DigitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 2) {
            leftCode = ("%!" + leftString.substring(0, 1)
                    + leftString.substring(1, 2)
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + leftString.substring(4, 5)
                    + DigitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 3) {
            leftCode = ("&!" + leftString.substring(0, 1)
                    + leftString.substring(1, 2)
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + leftString.substring(5))
        }
        if (firstFlag == 4) {
            leftCode = ("'!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + leftString.substring(2, 3)
                    + leftString.substring(3, 4)
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + DigitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 5) {
            leftCode = ("(!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + leftString.substring(3, 4)
                    + leftString.substring(4, 5)
                    + DigitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 6) {
            leftCode = (")!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + leftString.substring(4, 5)
                    + leftString.substring(5))
        }
        if (firstFlag == 7) {
            leftCode = ("*!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + leftString.substring(2, 3)
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + leftString.substring(4, 5)
                    + DigitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 8) {
            leftCode = ("+!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + leftString.substring(2, 3)
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + leftString.substring(5))
        }
        if (firstFlag == 9) {
            leftCode = (",!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + leftString.substring(3, 4)
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + leftString.substring(5))
        }
        val retVal = leftCode + "-" + rightCode + "!"
        return retVal
    }

    private fun parse() {
        val fullString = fullCode
        println("Full code: " + fullString)
        code = createEAN13Code(fullString)
        println("Generated code: " + code)
    }
}