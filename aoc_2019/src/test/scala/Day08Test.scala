import org.scalatest.FunSpec

class Day08Test extends FunSpec {

  describe("Create Layers from Input"){
    it("works with sample"){
      val encoded = "123456789012"
      val width  = 3
      val height = 2
      val expected = Vector(Vector("123","456"), Vector("789","012"))
      val actual = Day08.getLayers(encoded, width, height)
      assert(actual.size == expected.size)
      for (n <- 0 until expected.size){
        for(r <- 0 until expected(n).size) {
          assert(expected(n)(r) == actual(n)(r))
        }
      }
    }
  }

}
