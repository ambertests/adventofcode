import scala.annotation.tailrec
import graph.Coordinate
import intcode.IntcodeComputer
import scala.collection.mutable.ListBuffer

object Day11 extends Day {

  override var day: Int = 11
  val robot = Robot(getInputAsLongArray)
  val painted = robot.runProgram()
  printPartOne(painted)
  val robot2 = Robot(getInputAsLongArray)
  robot2.white.addOne((0,0))
  robot2.runProgram()
  robot2.showPaint() //GREJALPR
}

class Robot(icc:IntcodeComputer) {
  val painted = ListBuffer[(Int,Int)]()
  var white = ListBuffer[(Int,Int)]()
  
  var loc = Coordinate()
  def runProgram():Int={
    while (!icc.complete){
      val xy = (loc.x,loc.y)
      icc.input({if(white.contains(xy)) 1 else 0})
      val output = icc.compute(icc.currentPointer)
      val cmd = output.slice(output.length - 2, output.length)
      if (cmd(0) == '1' && !white.contains(xy)) {
        white += xy
        painted.addOne(xy)
      }
      else if (white.contains(xy)) {
        white -= xy
        painted.addOne(xy)
      }
      
      if (cmd(1) == '0') loc = loc.turnLeft.move()
      else loc = loc.turnRight.move()

    }
    painted.toSet.size
  }
  def showPaint() = {
    val p = painted.toSet
    val w = white.toSet
    val xs = p.unzip._1
    val ys = p.unzip._2
    (ys.min to ys.max).foreach(y => {
      val sb = new StringBuffer()
      (xs.min to xs.max).foreach(x => {
        if (w.contains((x,y))) sb.append('#')
        else sb.append('.')
      })
      println(sb.toString)
    })
  }
}

object Robot{
    def apply(program:Array[Long]):Robot = {
        new Robot(IntcodeComputer(program))
    }
}