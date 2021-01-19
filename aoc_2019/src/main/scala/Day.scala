import scala.io.Source
import scala.util.Using

trait Day extends App {
  var day: Int

  def getInputStrings: List[String] = {
    val fileName = f"day$day%02d.txt"
    val input = Using(Source.fromResource(fileName)){_.getLines().toList}
    if(input.isFailure) throw new RuntimeException(s"could not load input from file $fileName")
    else input.get
  }

  def getInputString: String = {
    getInputStrings(0)
  }

  def getInputAsLongArray: Array[Long] = {
    getInputString.split(",").map(x=>x.toLong)
  }

  def printPartOne(answer: Any): Unit = printAnswer(1, answer)
  def printPartTwo(answer: Any): Unit = printAnswer(2, answer)

  def printAnswer(part: Int, answer: Any): Unit = {
    println(s"Solution $day.$part: $answer")
  }
}

