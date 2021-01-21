import org.scalatest.funspec.AnyFunSpec
import graph.Coordinate

class Day11Test extends AnyFunSpec {
    describe("Coordinate orientation"){
        describe("move by orientation"){
            it("north"){
                val c = Coordinate(1,1).move()
                assert(c == Coordinate(1,0,'N'))
            }
            it("south"){
                val c = Coordinate(1,1,'S').move()
                assert(c == Coordinate(1,2,'S'))
            }
            it("east"){
                val c = Coordinate(1,1,'E').move()
                assert(c == Coordinate(2,1,'E'))
            }
            it("west"){
                val c = Coordinate(1,1,'W').move()
                assert(c == Coordinate(0,1,'W'))
            }
        }
        describe("turn right"){
            it("north to east"){
                val c = Coordinate(0,0,'N')
                assert(c.turnRight.o == 'E')
            }
            it("east to south"){
                val c = Coordinate(0,0,'E')
                assert(c.turnRight.o == 'S')
            }
            it("south to west"){
                val c = Coordinate(0,0,'S')
                assert(c.turnRight.o == 'W')
            }
            it("west to north"){
                val c = Coordinate(0,0,'W')
                assert(c.turnRight.o == 'N')
            }
        }
        describe("turn left"){
            it("north to west"){
                val c = Coordinate(0,0,'N')
                assert(c.turnLeft.o == 'W')
            }
            it("west to south"){
                val c = Coordinate(0,0,'W')
                assert(c.turnLeft.o == 'S')
            }
            it("south to east"){
                val c = Coordinate(0,0,'S')
                assert(c.turnLeft.o == 'E')
            }
            it("east to north"){
                val c = Coordinate(0,0,'E')
                assert(c.turnLeft.o == 'N')
            }
        }
    }
}