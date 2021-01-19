import intcode.IntcodeComputer


object Day05 extends Day {
  override var day = 5

  def doCompute(program:Array[Long], input:Int):String = {
    val icc = IntcodeComputer(program.clone(), input)
    icc.compute(0)
  }

  val program = getInputAsLongArray
  printPartOne(doCompute(program, 1))
  printPartTwo(doCompute(program, 5))
}
