import graph.Coordinate

import scala.collection.mutable._

object Day03 extends Day {
  override var day = 3

  def getPath(pos: Coordinate, cmd: String) = {
    val dir = cmd.slice(0,1)
    val num = cmd.slice(1, cmd.length).toInt
    dir match {
      case "R" => for(n <- 1 to num) yield pos.moveRight(n)
      case "L" => for(n <- 1 to num) yield pos.moveLeft(n)
      case "U" => for(n <- 1 to num) yield pos.moveUp(n)
      case "D" => for(n <- 1 to num) yield pos.moveDown(n)
    }
  }

  def processLine(str: String): List[Coordinate] = {
    val list = ListBuffer(Coordinate(0,0))
    str.split(",").foreach(cmd => {
      list.addAll(getPath(list.last, cmd))
    })
    //don't want to include the (0,0) origin point
    list.slice(1, list.size).toList
  }

  def getClosestIntersectionDistance(list1: List[Coordinate], list2: List[Coordinate]): Int = {
    list1.intersect(list2).map(c => c.distance(Coordinate(0,0))).min
  }

  def getShortestIntersectionPath(list1: List[Coordinate], list2: List[Coordinate]): Int = {
    list1.intersect(list2).map(c => list1.indexOf(c) + list2.indexOf(c) + 2).min
  }

  val lines = getInputStrings
  val wire1 = processLine(lines(0))
  val wire2 = processLine(lines(1))
  printPartOne(getClosestIntersectionDistance(wire1, wire2))
  printPartTwo(getShortestIntersectionPath(wire1, wire2))
}
