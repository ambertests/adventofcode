import org.scalatest.funspec.AnyFunSpec

class Day04Test extends AnyFunSpec {

  describe("Password Validation Part 1"){
    /*
    111111 meets these criteria (double 11, never decreases).
    223450 does not meet these criteria (decreasing pair of digits 50).
    123789 does not meet these criteria (no double).
    */
    it("example 1"){
      assert(Day04.isValid(111111)._1)
    }
    it("example 2"){
      assert(!Day04.isValid(223450)._1)
    }
    it("example 3"){
      assert(!Day04.isValid(123789)._1)
    }
  }
  describe("Password Validation Part 2") {
    /*
    112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
    123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
    111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).
     */
    it("example 1"){
      assert(Day04.isValid(112233)._2)
    }
    it("example 2"){
      assert(!Day04.isValid(123444)._2)
    }
    it("example 3"){
      assert(Day04.isValid(111122)._2)
    }
  }

}
