import scala.annotation.tailrec

object Day01 extends Day {

  override var day: Int = 1

  def fuelForMass(mass: Double): Double = {
    Math.floor(mass / 3) - 2
  }

  @tailrec
  def totalFuel(mass: Double, total: Double): Double = {
    val fuel = fuelForMass(mass)
    if (fuel <= 0) total
    else {
      totalFuel(fuel, total + fuel)
    }
  }

  var partOne: Double = 0
  var partTwo: Double = 0

  getInputStrings.foreach(line => {
    val mass = Integer.valueOf(line).doubleValue()
    partOne += fuelForMass(mass)
    partTwo += totalFuel(mass, 0)
  })

  printPartOne(partOne)
  printPartTwo(partTwo)

}

