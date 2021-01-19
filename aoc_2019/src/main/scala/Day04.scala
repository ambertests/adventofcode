import util.control.Breaks._

object Day04 extends Day {
  override var day = 4

  def isValid(i: Int): (Boolean, Boolean) = {
    val nums = for (n <- i.toString.toCharArray) yield n - '0' //offset the ascii code with the value for '0' (48)
    var hasDouble = false
    var noDecrease = true
    var hasPureDouble = false
    breakable {
      for (a <- nums.indices) {
        if (a > 0 && nums(a) < nums(a - 1)) {
          noDecrease = false
          break
        }
        if (a < nums.length - 1 && nums(a) == nums(a + 1)) {
          hasDouble = true
          //if it's a double, check before and after to make sure it is only two
          if ((a >= nums.length - 2 || nums(a + 1) != nums(a + 2)) &&
            (a < 1 || nums(a - 1) != nums(a))) hasPureDouble = true
        }
      }
    }
    (hasDouble & noDecrease, hasPureDouble & noDecrease)
  }

  val input = getInputString
  val lower = input.split("-")(0).toInt
  val upper = input.split("-")(1).toInt

  var valid = 0
  var valid2 = 0
  for (num <- lower to upper) {
    val validity = isValid(num)
    if (validity._1) valid += 1
    if (validity._2) valid2 += 1
  }
  printPartOne(valid)
  printPartTwo(valid2)

}
