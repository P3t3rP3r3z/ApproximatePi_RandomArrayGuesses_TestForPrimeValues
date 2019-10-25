import java.util.*
import kotlin.math.*

class PiApproximator {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            var answer: String

            println("Want to run the dart experiment? \nyes/no?")
            answer = readLine()!!
            when (answer) {
                "yes", "y", "Yes", "Y", "YES" -> dartExperiment()
            }

            println("Want to check and see if an Integer is prime? \nyes/no?")
            answer = readLine()!!
            when (answer) {
                "yes", "y", "Yes", "Y", "YES" -> primeExperiment()
            }

            println("Want to check and see how long it takes to randomly find a value in an array? \nyes/no?")
            answer = readLine()!!
            when (answer) {
                "yes", "y", "Yes", "Y", "YES" -> arrayExperiment()
            }

        }

        private fun arrayExperiment() {
            var ran = Random()
            var experimentArray = (1..1000).map { ran.nextInt() }
            val position: Int = ran.nextInt(999)
            val maxAttempts = 5000
            var attemptsTaken: Int
            var searchesDone = 0
            val valueToSearch: Int = experimentArray.get(position)
            var timesFound = 0

            for (i in 1..100) {
                searchesDone++
                attemptsTaken = 0
                for (x in 1..maxAttempts) {
                    var matchTest = ran.nextInt(999)
                    attemptsTaken++

                    if (experimentArray.get(matchTest) == valueToSearch) {
                        timesFound++
                        println("Surprisingly, the program did find the value it was searching for after $attemptsTaken attempts. " +
                                "This makes $timesFound times that the value has been found.")
                    }
                    // println("in search $searchesDone this is attempt#$attemptsTaken")
                }
            }
            println("A total of $searchesDone searches were done. Found $timesFound")
        }

        private fun dartExperiment() {
            var totalDartsOnBoard: Int = 0
            var timesToThrow: Int = 0

            println("How many times should I throw a dart?")
            timesToThrow = readLine()!!.toInt()

            if (timesToThrow != 0) {

                for (i in 0..timesToThrow) {
                    if (tossADartAndGetItsPosition()) {
                        totalDartsOnBoard++
                    }
                }

                val shouldBePiDivFour: Double = totalDartsOnBoard.toDouble() / timesToThrow.toDouble()
                val difFromPiDivFour: Double = (.78539816339 - shouldBePiDivFour).absoluteValue
                println("Total number of Darts on Board is: " + totalDartsOnBoard + " out of " + timesToThrow)
                println("This ratio should be close to Pi/4 (which is .78539816339): " + shouldBePiDivFour)
                println("The difference between Pi/4 and this value is $difFromPiDivFour\n")
            }

        }

        private fun primeExperiment() {
            var checkPrime: Int = 0

            var numOfRandomVals: Int = 0

            println("Time to test some numbers to see if they are prime! \n" +
                    "Please enter the number we want to check if it is a prime value. (Must be less than 2,147,483,647)")

            checkPrime = getNumberToTest()

            println("Now, how many random values would you like to compare against our possibly-prime number?")

            numOfRandomVals = readLine()!!.toInt()

            var randomValues = randomVals(numOfRandomVals.toLong())
            var possiblePrime: Boolean = true


            for (i in 0..numOfRandomVals - 1) {
                val mod: Int = checkPrimeInt(checkPrime, randomValues.get(i))
                if (mod == 0) {
                    println("mod is 0 when you divide $checkPrime by ${randomValues.get(i)}, so this number, $checkPrime is not prime")
                    possiblePrime = false
                    break
                }
            }

            if (possiblePrime) {
                println("None of the test values generated prove that this number is not prime.")
            }
        }

        private fun randomVals(numOfRandomVals: Long): List<Int> {
            println("OK, now tell me the maximum size of the values to compare to our possibly-prime number?\n" +
                    "Keep in mind that the minimum value generated will be a 3.")
            val maxSize = readLine()!!.toInt()

            var ran = Random(numOfRandomVals)
            var randomValues = (1..numOfRandomVals).map { ran.nextInt(maxSize) }

            return randomValues
        }

        private fun getNumberToTest(): Int {

            val checkPrime = readLine()!!.toInt()
            if (checkPrime <= 1) {
                println("Well that was just wrong. Try again.")
                getNumberToTest()
            }
            return checkPrime
        }

        private fun checkPrimeInt(checkPrime: Int, int: Int): Int {
            var ran = Random(int.toLong())
            var temp = int
            if (temp <= 2) {
                temp = ran.nextInt(checkPrime)
            }
            return (checkPrime % temp)
        }

        private fun isInCircle(circle: Circle, dart: Dart): Boolean {

            var xDelta = dart.x?.pow(2)
            var yDelta = dart.y?.pow(2)

            var distance = xDelta!! + yDelta!!
            distance = sqrt(distance)

            return distance <= circle.radius
        }

        private fun tossADartAndGetItsPosition(): Boolean {

            var dart: Dart = Dart(0.0, 0.0)
            var circle: Circle = Circle(0, 1.0)
            dart.throwDart()

            return isInCircle(circle, dart)
        }

    }
}