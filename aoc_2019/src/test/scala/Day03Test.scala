import org.scalatest.FunSpec

class Day03Test extends FunSpec {

  describe("Distance Calculator"){
    it("handles first example"){
      val line1 = Day03.processLine("R8,U5,L5,D3")
      val line2 = Day03.processLine("U7,R6,D4,L4")
      assert(Day03.getClosestIntersectionDistance(line1, line2) == 6)
    }
    it("handles second example"){
      val line1 = Day03.processLine("R75,D30,R83,U83,L12,D49,R71,U7,L72")
      val line2 = Day03.processLine("U62,R66,U55,R34,D71,R55,D58,R83")
      assert(Day03.getClosestIntersectionDistance(line1, line2) == 159)
    }
    it("handles third example"){
      val line1 = Day03.processLine("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
      val line2 = Day03.processLine("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
      assert(Day03.getClosestIntersectionDistance(line1, line2) == 135)
    }
  }
  describe("Path Calculator") {
    it("handles first example") {
      val line1 = Day03.processLine("R8,U5,L5,D3")
      val line2 = Day03.processLine("U7,R6,D4,L4")
      assert(Day03.getShortestIntersectionPath(line1, line2) == 30)
    }
    it("handles second example"){
      val line1 = Day03.processLine("R75,D30,R83,U83,L12,D49,R71,U7,L72")
      val line2 = Day03.processLine("U62,R66,U55,R34,D71,R55,D58,R83")
      assert(Day03.getShortestIntersectionPath(line1, line2) == 610)
    }
    it("handles third example"){
      val line1 = Day03.processLine("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
      val line2 = Day03.processLine("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
      assert(Day03.getShortestIntersectionPath(line1, line2) == 410)
    }
  }

}
