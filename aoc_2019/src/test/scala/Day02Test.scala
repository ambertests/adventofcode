import org.scalatest.FunSpec

class Day02Test extends FunSpec {

  describe("Array computer"){
    it("handles addition correctly"){
      val result = Day02.computeArray(Array(1,0,0,0,99))
      assert(result sameElements Array(2,0,0,0,99L))
    }
    it("handles multiplication correctly"){
      val result = Day02.computeArray(Array(2,3,0,3,99))
      assert(result sameElements Array(2,3,0,6,99L))
    }
    it("handles array with values beyond 99"){
      assert(Day02.computeArray(Array(2,4,4,5,99,0)) sameElements Array(2,4,4,5,99,9801L))
    }
    it("handles array with multiple command sequences"){
      assert(Day02.computeArray(Array(1,1,1,4,99,5,6,0,99)) sameElements Array(30,1,1,4,2,5,6,0,99L))
    }
    it("handles the original example"){
      assert(Day02.computeArray(Array(1,9,10,3,2,3,11,0,99,30,40,50)) sameElements
        Array(3500,9,10,70,
        2,3,11,0,
        99,
        30,40,50L))
    }

  }

}
