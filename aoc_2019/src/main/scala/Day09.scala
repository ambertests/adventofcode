import intcode.IntcodeComputer

object Day09 extends Day {
  override var day: Int = 9

  printPartOne(IntcodeComputer(getInputAsLongArray).run(1))
  printPartTwo(IntcodeComputer(getInputAsLongArray).run(2))

}
