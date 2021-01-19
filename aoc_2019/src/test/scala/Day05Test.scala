import org.scalatest.FunSpec

class Day05Test extends FunSpec {
  describe("Input/Output example"){
    val program = Array(3,0,4,0,99L)
    it("returns the input value"){
      assert(Day05.doCompute(program, 42).equals("42"))
    }
  }
  describe ("Positional vs immediate mode example"){
    val program = Array(1002,4,3,4,33L)
    it("runs successfully with no output"){
      assert(Day05.doCompute(program, 0).equals(""))
    }
  }
  describe("Comparison tests using position mode"){
    val program = Array(3,9,8,9,10,9,4,9,99,-1,8L)
    it("returns 1 if input equals 8"){
      assert(Day05.doCompute(program, 8).equals("1"))
    }
    it("returns 0 if input is less than 8"){
      assert(Day05.doCompute(program, 7).equals("0"))
    }
    it("returns 0 if input is greater than 8"){
      assert(Day05.doCompute(program, 9).equals("0"))
    }
  }
  describe("Comparison tests using immediate mode"){
    val program = Array(3,3,1108,-1,8,3,4,3,99,0L)
    it("returns 1 if input equals 8"){
      assert(Day05.doCompute(program, 8).equals("1"))
    }
    it("returns 0 if input is less than 8"){
      assert(Day05.doCompute(program, 7).equals("0"))
    }
    it("returns 0 if input is greater than 8"){
      assert(Day05.doCompute(program, 9).equals("0"))
    }
  }
  describe("Jump tests using position mode"){
    val program = Array(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9L)
    it("returns 0 if input is 0"){
      assert(Day05.doCompute(program, 0).equals("0"))
    }
    it("returns 1 if input is not 0"){
      assert(Day05.doCompute(program, 7).equals("1"))
    }
  }
  describe("Jump tests using immediate mode"){
    val program = Array(3,3,1105,-1,9,1101,0,0,12,4,12,99,1L)
    it("returns 0 if input is 0"){
      assert(Day05.doCompute(program, 0).equals("0"))
    }
    it("returns 1 if input is not 0"){
      assert(Day05.doCompute(program, 7).equals("1"))
    }
  }
  describe("Larger example"){
    val program = Array(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
      1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
      999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99L)
    it("returns 999 if input is less than 8"){
      assert(Day05.doCompute(program, 3).equals("999"))
    }
    it("returns 1000 if input equals 8"){
      assert(Day05.doCompute(program, 8).equals("1000"))
    }
    it("returns 1001 if input is greater than 8"){
      assert(Day05.doCompute(program, 22).equals("1001"))
    }
  }


}
