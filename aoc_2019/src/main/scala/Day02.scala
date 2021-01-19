import intcode.IntcodeComputer
import util.control.Breaks._

object Day02 extends Day {

  override var day: Int = 2

  def computeArray(arr:Array[Long]): Array[Long] = {
    val icc = IntcodeComputer(arr)
    icc.compute(0)
    icc.program.slice(0,arr.length)
  }

  val orig:Array[Long] = getInputAsLongArray

  def checkInputs(noun: Long, verb: Long):Long = {
    val arr = orig.clone
    arr(1) = noun
    arr(2) = verb
    computeArray(arr)(0)
  }

  printPartOne(checkInputs(12, 2))

  breakable {
    0 to 99 foreach { n =>
      0 to 99 foreach { v =>
        val output = checkInputs(n, v)
        if (output == 19690720) {
          printPartTwo(100 * n + v)
          break
        }
      }
    }
  }

}
