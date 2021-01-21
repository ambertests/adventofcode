import scala.annotation.tailrec
import scala.math.abs

object Day12 extends Day {
  override var day: Int = 12

  def init(lines:List[String]):List[Moon]={
      lines.map(l => Moon(l))
  }
  def tick(moons:List[Moon]):List[Moon]={
      @tailrec
      def applyGravity(m:Moon, others:List[Moon]):Moon={
          if(others.isEmpty) m
          else applyGravity(m.applyGravity(others.head), others.tail)
      }
      
      moons.combinations(moons.length - 1)
           .map(t => applyGravity(moons.diff(t)(0), t))
           .map(m2 => m2.applyVelocity()).toList
  }

  @tailrec
  def update(moons:List[Moon], turns:Int):List[Moon]={
      if (turns == 0) moons
      else update(tick(moons), turns - 1)
  }

  def totalEnergy(moons:List[Moon]):Int = moons.map(m => m.totalEngergy()).sum
  
  printPartOne(totalEnergy(update(init(getInputStrings), 1000)))
  
}

case class Moon(x:Int, y:Int, z:Int, vx:Int=0, vy:Int=0, vz:Int=0){
    def applyGravity(moon:Moon):Moon ={
        val nx:Int = {if (moon.x > x) vx + 1 else if (moon.x < x) vx -1 else vx}
        val ny:Int = {if (moon.y > y) vy + 1 else if (moon.y < y) vy -1 else vy}
        val nz:Int = {if (moon.z > z) vz + 1 else if (moon.z < z) vz -1 else vz}
        new Moon(x, y, z, nx, ny, nz)
    }
    def applyVelocity():Moon = new Moon(x + vx, y + vy, z + vz, vx, vy, vz)

    def potentialEnergy():Int = abs(x) + abs(y) + abs(z)
    def kineticEngergy():Int = abs(vx) + abs(vy) + abs(vz)
    def totalEngergy():Int = potentialEnergy() * kineticEngergy()
}

object Moon{
    def apply(line:String):Moon={
        if (line.contains("vel")){
            val s = line.split("vel")
            val x = s(0).substring(s(0).indexOf("x=") + 2, s(0).indexOf(", y")).strip.toInt
            val y = s(0).substring(s(0).indexOf("y=") + 2, s(0).indexOf(", z")).strip.toInt
            val z = s(0).substring(s(0).indexOf("z=") + 2, s(0).indexOf(">")).strip.toInt
            val vx = s(1).substring(s(1).indexOf("x=") + 2, s(1).indexOf(", y")).strip.toInt
            val vy = s(1).substring(s(1).indexOf("y=") + 2, s(1).indexOf(", z")).strip.toInt
            val vz = s(1).substring(s(1).indexOf("z=") + 2, s(1).indexOf(">")).strip.toInt
            new Moon(x,y,z,vx,vy,vz)

        }
        else{
            val x = line.substring(line.indexOf("x=") + 2, line.indexOf(", y")).strip.toInt
            val y = line.substring(line.indexOf("y=") + 2, line.indexOf(", z")).strip.toInt
            val z = line.substring(line.indexOf("z=") + 2, line.indexOf(">")).strip.toInt
            new Moon(x,y,z)
        }
    }
}