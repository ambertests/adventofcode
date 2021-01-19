import org.scalatest.funspec.AnyFunSpec

class Day06Test extends AnyFunSpec {
  val part1: List[String] = List("COM)B", "B)C", "C)D", "D)E", "E)F",
    "B)G", "G)H", "D)I", "E)J", "J)K", "K)L")

  describe("Orbit example"){
    it("has 42 direct and indirect orbits"){
      assert(Day06.calculateOrbits(Day06.processInput(part1)) == 42)
    }
  }
  describe("Orbit Counter"){
    val orbitMap = Day06.processInput(part1)
    it("returns 3 orbits for D"){
      assert(Day06.countOrbits(orbitMap, "D", 0) == 3)
    }
    it("returns 7 orbits for L"){
      assert(Day06.countOrbits(orbitMap, "L", 0) == 7)
    }
    it("returns 0 orbits for COM"){
      assert(Day06.countOrbits(orbitMap, "COM", 0) == 0)
    }
  }
  val part2: List[String] = List("COM)B", "B)C", "C)D", "D)E", "E)F",
    "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN")
  describe("Min Path Calculator"){
    it("gets YOU to SAN in 4 steps"){
      assert(Day06.minPathToSanta(Day06.processInput(part2)) == 4)
    }
  }
}
