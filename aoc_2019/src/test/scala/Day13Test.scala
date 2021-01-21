import org.scalatest.funspec.AnyFunSpec
import intcode.IntcodeComputer
import scala.util.Random

class Day13Test extends AnyFunSpec {
    describe("Intcode Computer"){
        it("can load long programs"){
        val program = Array.fill(6000)(Random.nextLong())
        val icc = Day13.load(program)
        assert(icc.program.length > program.length)
        }
    }
}