import intcode.IntcodeComputer
import graph.Coordinate
import scala.collection.mutable.ListBuffer

object Day13 extends Day {
  override var day: Int = 13
  def load(program:Array[Long]):IntcodeComputer = IntcodeComputer(program)
  def countBlocks():Int = {
    val icc = load(getInputAsLongArray)
    val output = icc.compute(0,true).split(",")
    var blocks = 0
    (0 until output.length - 2 by 3).foreach(i => {
      val t = output(i+2).toLong
      if(t == 2) blocks += 1
    })
    blocks
}
def gameFromOutput(output:List[Int]):Game = {
  val blocks = ListBuffer[Coordinate]()
  val walls = ListBuffer[Coordinate]()
  var paddle = Coordinate()
  var balls = ListBuffer[Coordinate]()
  var empty = ListBuffer[Coordinate]()
  var score = 0
  (0 until output.length - 2 by 3).foreach(i => {
    val x = output(i)
    val y = output(i+1)
    val t = output(i+2)
    if (x == -1 && y == 0) score = t 
    else {
      t match {
        case 0 => empty += Coordinate(x,y)
        case 1 => walls += Coordinate(x,y)
        case 2 => blocks += Coordinate(x,y)
        case 3 => paddle = Coordinate(x,y)
        case 4 => balls += Coordinate(x,y)
        case _ => true
      }
    }
  })
  
  new Game(walls.toSet.toList, 
          blocks.toSet.diff(empty.toSet).diff(balls.toSet).toList, 
          paddle, balls.last, score)
}

def playGame():Int = {
  val program = getInputAsLongArray
  program.update(0, 2L)
  val icc = load(program)
  var score = 0
  var blockCount = Int.MaxValue
  var boardSize = -1
  var joystick = 0

  while(blockCount > 0){
    icc.input(joystick)
    val output = icc.compute(icc.currentPointer,true)
                    .split(",")
                    .map(l => l.toInt)
                    .toList
    if(boardSize < 0) boardSize = output.length
    val game = gameFromOutput(output.slice(output.length - boardSize,output.length))
    joystick = game.nextMove()
    blockCount = game.blockCount
    score = game.score
    game.printScreen()
    //println(s"blocks: $blockCount,  score: $score")
  }
  score
}
  printPartOne(countBlocks())
  printPartTwo(playGame())
}

case class Game(walls:List[Coordinate], blocks:List[Coordinate], paddle:Coordinate, ball:Coordinate, score:Int){
  
  def height:Int = walls.map(w => w.y).max
  def width:Int = walls.map(w => w.x).max
  def blockCount:Int = blocks.length

  def printScreen() = {
    (0 to height).foreach(y => {
      val sb = new StringBuffer()
      (0 to width).foreach(x => {
        if (walls.contains(Coordinate(x,y))) sb.append('#')
        else if (blocks.contains(Coordinate(x,y))) sb.append('B')
        else if (Coordinate(x,y) == paddle) sb.append('=')
        else if (x == ball.x && y == ball.y) sb.append('@')
        else sb.append(' ')
      })
      println(sb.toString)
    })
    println('\n')
  }
  def nextMove():Int = {
    if (blockCount == 0) 0
    else if (ball.x > paddle.x) 1
    else if (ball.x < paddle.x) -1
    else 0
  }

}
