import scala.annotation.tailrec
import graph.Coordinate
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

object Day10 extends Day {

  override var day: Int = 10
  def getAsteroids(field:List[String]):(Int, List[Coordinate]) = {
      val size = field.length
      val list = ListBuffer[Coordinate]()
      for(y <- 0 until size){
        val line = field(y).toCharArray
        for(x <- 0 until size){
          if (line(x) != '.'){
            list += Coordinate(x,y)
          }
        }
      }
      (size, list.toList)
    }

  final val TOP_LEFT = 1
  final val TOP_RIGHT = 2
  final val BOTTOM_LEFT = 3
  final val BOTTOM_RIGHT = 4

  def getLinesOfSight(a:Coordinate, size:Int, quadrant:Int):List[List[(Int,Int)]]={
    val los = ListBuffer[List[(Int,Int)]]()
    val x_range = {
        quadrant match {
          case TOP_LEFT | BOTTOM_LEFT => (a.x - 1 to 0 by -1)
          case TOP_RIGHT | BOTTOM_RIGHT => (a.x until size)
        }
      }
      val y_range = {
        quadrant match {
          case TOP_LEFT | TOP_RIGHT => (a.y to 0 by -1)
          case BOTTOM_LEFT | BOTTOM_RIGHT => (a.y + 1 until size)
        }
      }
    if (quadrant == TOP_LEFT || quadrant == TOP_RIGHT){
      los += {
        (for(x <- x_range) yield (x, a.y)).toList
      }
    }
    if (quadrant == TOP_RIGHT || quadrant == BOTTOM_RIGHT){
      los += {
        (for(y <- y_range) yield (a.x, y)).toList
      }
    }
    (1 to x_range.size).foreach(x_step => {
      val x_list = {
        quadrant match {
          case TOP_LEFT | BOTTOM_LEFT => (a.x - x_step to 0 by -(x_step))
          case TOP_RIGHT | BOTTOM_RIGHT => (a.x + x_step until size by x_step)
        }
      }
      (1 to y_range.size).foreach(y_step => {
        val y_list = {
          quadrant match {
            case TOP_LEFT | TOP_RIGHT => (a.y - y_step to 0 by -(y_step))
            case BOTTOM_LEFT | BOTTOM_RIGHT => (a.y + y_step until size by y_step)
          }
        }
        los += x_list.toList.zip(y_list.toList)
      })
    })
    los.toList
  }
  def scanQuadrant(asteroids:List[Coordinate], a:Coordinate, size:Int, quadrant:Int):Set[Coordinate] = {
    val los = getLinesOfSight(a, size, quadrant)
    val seen = ListBuffer[Coordinate]()
    val blocked = ListBuffer[Coordinate](a)
    los.foreach(line => {
      var block = false
      line.foreach(n => {
        val c = Coordinate(n._1,n._2)
        if(block){
          blocked += c
        }
        else if (asteroids.contains(c) && !seen.contains(c) && !blocked.contains(c)) {
          seen += c
          block = true
        }
      })
    })
    seen.toSet
  }

  def getTotalSeen(asteroids:List[Coordinate], a:Coordinate, size:Int):Set[Coordinate] = {
    (1 to 4).map(q => scanQuadrant(asteroids, a, size, q)).flatten.toSet
  }

  def findMonitorLocation(field:List[String]):(Coordinate, Int) = {
      val (size, asteroids) = getAsteroids(field)
      var best = Coordinate(0,0)
      var max_seen = 0
      asteroids.foreach(a => {
        val seen = getTotalSeen(asteroids, a, size)
        if (seen.size > max_seen) {
          max_seen = seen.size
          best = a
        }
      })
      (best, max_seen)
  }

  val (monitor, count) = findMonitorLocation(getInputStrings)
  printPartOne(count) //286

}

