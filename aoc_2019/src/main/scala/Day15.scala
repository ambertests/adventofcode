import scala.collection.mutable.{ListBuffer, Set}
import scala.annotation.tailrec
import intcode.IntcodeComputer
import graph.Coordinate
import util.control.Breaks._



object Day15 extends Day {
  override var day: Int = 15

    final val NORTH:Long = 1
    final val SOUTH:Long = 2
    final val WEST:Long = 3
    final val EAST:Long = 4
/*
        loc = numbers[n]
        paths = [[loc]]
        seen = set()
        seen.add(loc)
        while paths:
            curr_path = paths.pop(0)
            x,y = curr_path[-1]
            if (x, y) in numbers.values() and len(curr_path) > 1:
                lengths[(n, get_num_from_pos(numbers, (x,y)))] = len(curr_path) - 1
                continue
            moves = [(x+1, y),(x-1, y),(x, y+1),(x, y-1)]
            for dx, dy in moves:
                if (dx,dy) not in walls and (dx,dy) not in seen:
                    paths.append(curr_path + [(dx, dy)])
                    seen.add((dx, dy))
*/
    def findOxygen(droid:Droid):Int = {
      val seen = Set[(Int,Int)]()
      seen.add((0,0))
      val paths = ListBuffer[ListBuffer[(Int, Int)]]()
      paths += ListBuffer((0,0))
      var output:Long = 0
      while(paths.length > 0){
        val path = paths.head
        paths -= path
        if(output != 2){
          (1 to 4).foreach{ d =>
            val peek = droid.peek(d)
            if(!seen.contains(peek) && !droid.walls.contains(peek)){
              //println(s"moving droid $d")
              output = droid.move(d)
              println("droid output: " + output)
  
                val p = droid.position
                paths += (path += ((p.x, p.y)))
                seen += ((p.x, p.y))
          }
        }
      }
    }
      droid.steps
    }
    printPartOne(findOxygen(Droid(getInputAsLongArray)))

}

case class Droid(icc:IntcodeComputer){
    var position = Coordinate()
    val walls = Set[(Int, Int)]()
    var steps = 0
    def move(direction:Long):Long={
        val dir = direction match {
            case 1 => 'N'
            case 2 => 'S'
            case 3 => 'E'
            case 4 => 'W'
        }
        icc.input(direction)
        val out = icc.compute(icc.currentPointer, true).split(",").last.toLong
        val newPos = position.setDirection(dir).move()
        if(out != 0){
          position = newPos
          steps += 1
        }else{
          walls += ((newPos.x, newPos.y))
        }
        out
    }
    def peek(direction:Long):(Int, Int)={
        val dir = direction match {
            case 1 => 'N'
            case 2 => 'S'
            case 3 => 'E'
            case 4 => 'W'
        }
        val peek = position.setDirection(dir).move()
        (peek.x, peek.y)
    }
}

object Droid{
    def apply(program:Array[Long]):Droid = {
        new Droid(IntcodeComputer(program))
    }
}

