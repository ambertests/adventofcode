import org.scalatest.funspec.AnyFunSpec


class Day12Test extends AnyFunSpec {
    describe("create moon"){
        it("understands negative input"){
            val input = "<x=-8, y=-9, z=-7>"
            val moon = Moon(input)
            assert(moon.x == -8)
            assert(moon.y == -9)
            assert(moon.z == -7)
        }
        it("handles whitespace"){
            val input = "<x= 4, y= 1, z= 5>"
            val moon = Moon(input)
            assert(moon.x == 4)
            assert(moon.y == 1)
            assert(moon.z == 5)
        }
        it("handles velocity"){
            val input = "pos=<x= 2, y= 1, z=-3>, vel=<x=-3, y=-2, z= 1>"
            val moon = Moon(input)
            assert(moon.x == 2)
            assert(moon.y == 1)
            assert(moon.z == -3)
            assert(moon.vx == -3)
            assert(moon.vy == -2)
            assert(moon.vz == 1)
        }
    }
    describe("apply gravity"){
        val g = new Moon(3,4,5)
        val c = new Moon(5,4,3)
        it("for vx"){
            assert(g.applyGravity(c).vx == 1)
            assert(c.applyGravity(g).vx == -1)
        }
        it("for vy"){
            assert(g.applyGravity(c).vy == 0)
            assert(c.applyGravity(g).vy == 0)
        }
        it("for vz"){
            assert(g.applyGravity(c).vz == -1)
            assert(c.applyGravity(g).vz == 1)
        }
    }
    describe("apply velocity"){
        //x=1, y=2, z=3 and a velocity of x=-2, y=0,z=3
        val e = new Moon(1,2,3,-2,0,3)
        it("moves correctly"){
            val e2 = e.applyVelocity()
            //x=-1, y=2, z=6
            assert(e2.x == -1)
            assert(e2.y == 2)
            assert(e2.z == 6)
        }
    }
    describe("initialize moons"){
        val input = List(
            "<x=-1, y=0, z=2>",
            "<x=2, y=-10, z=-7>",
            "<x=4, y=-8, z=8>",
            "<x=3, y=5, z=-1>")
        it("works correctly"){
            val moons = Day12.init(input)
            assert(moons.length == input.length)
            assert(moons(0) == new Moon(-1,0,2))
            assert(moons(1) == new Moon(2,-10,-7))
            assert(moons(2) == new Moon(4,-8,8))
            assert(moons(3) == new Moon(3,5,-1))
        }
    }
    describe("tick"){
        val input = List(
            "<x=-1, y=0, z=2>",
            "<x=2, y=-10, z=-7>",
            "<x=4, y=-8, z=8>",
            "<x=3, y=5, z=-1>")
        val moons = Day12.init(input)
        it("works correctly"){
            val updated = Day12.tick(moons)
            assert(updated.length == moons.length)
            assert(updated.contains(new Moon(2, -1, 1, 3, -1, -1)))
            assert(updated.contains(new Moon(3,-7,-4,1,3,3)))
            assert(updated.contains(new Moon(1,-7,5,-3,1,-3)))
            assert(updated.contains(new Moon(2,2,0,-1,-3,1)))
        }
    }
    describe("test updates"){
        val input = List(
            "<x=-1, y=0, z=2>",
            "<x=2, y=-10, z=-7>",
            "<x=4, y=-8, z=8>",
            "<x=3, y=5, z=-1>")
        val moons = Day12.init(input)
        it("works for 2 ticks"){
            val expected = List(
                "pos=<x= 5, y=-3, z=-1>, vel=<x= 3, y=-2, z=-2>",
                "pos=<x= 1, y=-2, z= 2>, vel=<x=-2, y= 5, z= 6>",
                "pos=<x= 1, y=-4, z=-1>, vel=<x= 0, y= 3, z=-6>",
                "pos=<x= 1, y=-4, z= 2>, vel=<x=-1, y=-6, z= 2>")
            val turn2 = expected.map(e => Moon(e)).toList
            val updated = Day12.update(moons, 2)
            assert(updated == turn2)
        }
        it("works for 10 ticks"){
            val expected = List(
                "pos=<x= 2, y= 1, z=-3>, vel=<x=-3, y=-2, z= 1>",
                "pos=<x= 1, y=-8, z= 0>, vel=<x=-1, y= 1, z= 3>",
                "pos=<x= 3, y=-6, z= 1>, vel=<x= 3, y= 2, z=-3>",
                "pos=<x= 2, y= 0, z= 4>, vel=<x= 1, y=-1, z=-1>")
            val turn10 = expected.map(e => Moon(e)).toList
            val updated = Day12.update(moons, 10)
            assert(updated == turn10)
        }
    }
    describe("total energy"){
        it("example 1"){
            val input = List(
                "<x=-1, y=0, z=2>",
                "<x=2, y=-10, z=-7>",
                "<x=4, y=-8, z=8>",
                "<x=3, y=5, z=-1>")
            val energy = Day12.totalEnergy(Day12.update(Day12.init(input), 10))
            assert(energy == 179)
        }
        it("example 2"){
            val input = List(
                "<x=-8, y=-10, z=0>",
                "<x=5, y=5, z=10>",
                "<x=2, y=-7, z=3>",
                "<x=9, y=-8, z=-3>")
            val energy = Day12.totalEnergy(Day12.update(Day12.init(input), 100))
            assert(energy == 1940)
        }
    }
    describe("find repeat"){
        it("fast example"){
            val input = List(
                "<x=-1, y=0, z=2>",
                "<x=2, y=-10, z=-7>",
                "<x=4, y=-8, z=8>",
                "<x=3, y=5, z=-1>")
            
            val moons = Day12.init(input)
            val r = Day12.findRepeat(moons, moons)
            assert(r == 2772)
        }
        it("slow example"){
            val input = List(
               "<x=-8, y=-10, z=0>",
                "<x=5, y=5, z=10>",
                "<x=2, y=-7, z=3>",
                "<x=9, y=-8, z=-3>")
            val moons = Day12.init(input)
            val r = Day12.findRepeat(moons, moons)
            assert(r == 4686774924L)
        }
    }

}