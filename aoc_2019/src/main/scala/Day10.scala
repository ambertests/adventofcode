import scala.annotation.tailrec
import graph.Coordinate
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

object Day10 extends Day {

  override var day: Int = 10

  def getAsteroids(field:List[String]):List[Coordinate] = {
      val list = ListBuffer[Coordinate]()
      for(y <- 0 until field.length){
          val line = field(y).toCharArray
          for(x <- 0 until line.length){
              if (line(x) == '#'){
                  list += Coordinate(x,y)
              }
          }
      }
      list.toList
    }

  def findMonitorLocation(field:List[String]):(Coordinate, Int) = {
      val asteroids = getAsteroids(field)
      val m = HashMap[Coordinate, List[Coordinate]]()
      asteroids.foreach(a => {
          if(!m.contains(a)){
              m += (a -> List[Coordinate]())
          }
          

      })
      (Coordinate(0,0), 0)
  }

}

