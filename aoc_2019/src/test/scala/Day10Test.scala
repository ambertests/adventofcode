import org.scalatest.funspec.AnyFunSpec
import graph.Coordinate

class Day10Test extends AnyFunSpec {
    describe("Get Asteroids from Input"){
        it("works with the small example"){
            val field = List(".#..#",
                             ".....",
                             "#####",
                             "....#",
                             "...##")

            assert(Day10.getAsteroids(field).length == 10)
        }
    }

    describe("Find Monitor Location"){
        it("works with the small example"){
            val field = List(".#..#",
                             ".....",
                             "#####",
                             "....#",
                             "...##")

            val ml = Day10.findMonitorLocation(field)
            assert(ml._1 == Coordinate(3,4))
            assert(ml._2 == 8)
        }
    }
}