import scala.annotation.tailrec

object Day06 extends Day {

  override var day = 6

  def processInput(input:List[String]): Map[String, String] = {
    //input is in the format "Planet)Satellite", which
    //becomes a satellite -> planet mapping, because each
    //satellite can only orbit around one planet
    (for (x <- input) yield x.split("\\)")(1) -> x.split("\\)")(0)).toMap
  }

  @tailrec
  def countOrbits(orbitMap:Map[String, String], satName:String, count:Int): Int = {
    if(!orbitMap.contains(satName)) count
    else countOrbits(orbitMap, orbitMap(satName), count + 1)
  }

  def calculateOrbits(orbitMap:Map[String, String]): Int = {
    var total = 0
    for(s <- orbitMap.keys) {total += countOrbits(orbitMap, s, 0)}
    total
  }

  def minPathToSanta(orbitMap: Map[String, String]): Int = {
    @tailrec
    def getPathToCom(s:String, path:List[String]):List[String] = {
      if(s == "COM") path
      else getPathToCom(orbitMap(s), path :+ s)
    }
    //first get the full path from YOU and SAN to the end point COM
    val youPath = getPathToCom(orbitMap("YOU"), List())
    val sanPath = getPathToCom(orbitMap("SAN"), List())

    //next get the first point where the two paths intersect
    val crossPoint = youPath.intersect(sanPath).head

    //the minimum path is the distance from YOU and SAN to the intersection
    youPath.indexOf(crossPoint) + sanPath.indexOf(crossPoint)
  }

  val orbitMap = processInput(getInputStrings)
  printPartOne(calculateOrbits(orbitMap))
  printPartTwo(minPathToSanta(orbitMap))
}
