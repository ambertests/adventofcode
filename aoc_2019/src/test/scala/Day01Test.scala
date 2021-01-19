import org.scalatest.FunSpec

class Day01Test extends FunSpec {

  describe("Fuel amount from mass"){
    it("correctly calculates the fuel needed") {
      assert(Day01.fuelForMass(12) == 2)
      assert(Day01.fuelForMass(14) == 2)
      assert(Day01.fuelForMass(1969) == 654)
      assert(Day01.fuelForMass(100756) == 33583)
    }
  }

  describe("Total fuel amount"){
    it("recursively calculates total fuel") {
      assert(Day01.totalFuel(1969, 0) == 966)
      assert(Day01.totalFuel(100756, 0) == 50346)
    }
  }

}
