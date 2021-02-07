package graph

case class Coordinate(x: Int=0, y: Int=0, o:Char='N'){

  def setDirection(d:Char) = Coordinate(x, y, d)

  def moveRight(n:Int) = Coordinate(x + n, y, o)
  def moveLeft(n:Int) = Coordinate(x - n, y, o)
  def moveUp(n:Int) = Coordinate(x, y - n, o)
  def moveDown(n:Int) = Coordinate(x, y + n, o)

  def distance(c:Coordinate): Int = {
    Math.abs(x - c.x) + Math.abs(y - c.y)
  }

  def move(n:Int=1):Coordinate = {
    o match  {
      case 'N' => moveUp(n)
      case 'S' => moveDown(n)
      case 'E' => moveRight(n)
      case 'W' => moveLeft(n)
    }
  }

  def turnRight():Coordinate = {
    o match {
      case 'N' => Coordinate(x, y, 'E')
      case 'E' => Coordinate(x, y, 'S')
      case 'S' => Coordinate(x, y, 'W')
      case 'W' => Coordinate(x, y, 'N')
    }
  }

  def turnLeft() = turnRight().turnRight().turnRight()

}
