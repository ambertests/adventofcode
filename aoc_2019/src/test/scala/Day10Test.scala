import org.scalatest.funspec.AnyFunSpec
import graph.Coordinate

class Day10Test extends AnyFunSpec {
    describe("Get Asteroids from Input"){
        describe("works with the small example"){
            val field = List(".#..#",
                             ".....",
                             "#####",
                             "....#",
                             "...##")
            val (size, asteroids) = Day10.getAsteroids(field)
            it("gets the correct number of asteroids"){
              assert(asteroids.length == 10)
            }
            it("sets the size correctly"){
              assert(size == (5,5))
            }
        }
        describe("works with a medium example"){
            val field = List(   "......#.#.",
                                "#..#.#....",
                                "..#######.",
                                ".#.#.###..",
                                ".#..#.....",
                                "..#....#.#",
                                "#..#....#.",
                                ".##.#..###",
                                "##...#..#.",
                                ".#....####")
            val (size, asteroids) = Day10.getAsteroids(field)
            it("gets the correct number of asteroids"){
              assert(asteroids.length == 40)
            }
            it("sets the size correctly"){
              assert(size == (10,10))
            }

        }
        describe("works with rectangle"){
            val field = List(
                ".#....#####...#..",
                "##...##.#####..##",
                "##...#...#.#####.",
                "..#.....X...###..",
                "..#.#.....#....##")
            val (size, asteroids) = Day10.getAsteroids(field)
            it("gets the correct number of asteroids"){
              assert(asteroids.length == 37)
            }
            it("sets the size correctly"){
              assert(size == (17,5))
            }
        }
    }

    describe("Scan Quadrant: Small"){
        val field = List(".#..#",
                         ".....",
                         "#####",
                         "....#",
                         "...##")
        val (size, asteroids) = Day10.getAsteroids(field)
        val a = Coordinate(2,2)
        it("works for top left"){
            val seen = Day10.scanQuadrant(asteroids, a, size, Day10.TOP_LEFT)
            assert(seen.size == 1)
            assert(seen.contains(Coordinate(1,0)))
        }
        it("works for bottom left"){
            val seen = Day10.scanQuadrant(asteroids, a, size, Day10.BOTTOM_LEFT)
            assert(seen.size == 1)
            assert(seen.contains(Coordinate(1,2)))
            assert(!seen.contains(Coordinate(0,2)))
        }
        it("works for top right"){
            val seen = Day10.scanQuadrant(asteroids, a, size, Day10.TOP_RIGHT)
            assert(seen.size == 2)
            assert(seen.contains(Coordinate(3,2)))
            assert(seen.contains(Coordinate(4,0)))
            assert(!seen.contains(Coordinate(4,2)))
        }
        it("works for bottom right"){
            val seen = Day10.scanQuadrant(asteroids, a, size, Day10.BOTTOM_RIGHT)
            assert(seen.size == 3)
            assert(seen.contains(Coordinate(4,3)))
            assert(seen.contains(Coordinate(4,4)))
            assert(seen.contains(Coordinate(3,4)))
        }
        describe("works for top right corner"){
            val tl = Coordinate(4,0)
            it("top left qudrant ok"){
                val seen = Day10.scanQuadrant(asteroids, tl, size, Day10.TOP_LEFT)
                assert(seen.size == 0)
            }
            it("top right qudrant ok"){
                val seen = Day10.scanQuadrant(asteroids, tl, size, Day10.TOP_RIGHT)
                assert(seen.size == 0)
            }
            it("bottom right qudrant ok"){
                val seen = Day10.scanQuadrant(asteroids, tl, size, Day10.BOTTOM_RIGHT)
                assert(seen.size == 1)
            }
            it("bottom left qudrant ok"){
                val seen = Day10.scanQuadrant(asteroids, tl, size, Day10.BOTTOM_LEFT)
                assert(seen.size == 6)
            }
        }
    }

    describe("Scan Quadrant: Medium"){
        it("calculates diagonal blocks"){
            val field = List(
                "#.........",
                "...A......",
                "...B..a...",
                ".EDCG....a",
                "..F.c.b...",
                ".....c....",
                "..efd.c.gb",
                ".......c..",
                "....f...c.",
                "...e..d..c"
            )
            val (size, asteroids) = Day10.getAsteroids(field)
            val a = Coordinate(0,0)
            val seen = Day10.scanQuadrant(asteroids, a, size, Day10.BOTTOM_RIGHT)
            assert(seen.size == 7)
        }
    }
    describe("Get Total Seen"){
        val field = List(".7..7",
                         ".....",
                         "67775",
                         "....7",
                         "...87")
        val (size, asteroids) = Day10.getAsteroids(field)
        val five = Coordinate(4,2)
        val six = Coordinate(0,2)
        val sevens = List(Coordinate(1,0), Coordinate(4,0),
        Coordinate(1,2),Coordinate(2,2),Coordinate(3,2),
        Coordinate(4,3),Coordinate(4,4))
        val eight = Coordinate(3,4)
        it(s"calculates $five correctly"){
            val seen = Day10.getTotalSeen(asteroids, five, size)
            assert(seen.size == 5)
        }
        it(s"calculates $six correctly"){
            val seen = Day10.getTotalSeen(asteroids, six, size)
            assert(seen.size == 6)
        }
        sevens.foreach(s => {
            it(s"calculates $s correctly"){
                val seen  = Day10.getTotalSeen(asteroids, s, size)
                assert(seen.size == 7)
            }
        })
        it(s"calculates $eight correctly"){
            val seen = Day10.getTotalSeen(asteroids, eight, size)
            assert(seen.size == 8)
        }
    }

    describe("Find Monitor Location"){
        it("works with the small example"){
            val field = List(".#..#",
                             ".....",
                             "#####",
                             "....#",
                             "...##")

            val (loc, seen) = Day10.findMonitorLocation(field)
            assert(loc == Coordinate(3,4))
            assert(seen == 8)
        }
        it("works with medium example 1"){
            val field = List(   "......#.#.",
                                "#..#.#....",
                                "..#######.",
                                ".#.#.###..",
                                ".#..#.....",
                                "..#....#.#",
                                "#..#....#.",
                                ".##.#..###",
                                "##...#..#.",
                                ".#....####")

            val (loc, seen) = Day10.findMonitorLocation(field)
            assert(loc == Coordinate(5,8))
            assert(seen == 33)
        }
        it("works with medium example 2"){
            val field = List(   "#.#...#.#.",
                                ".###....#.",
                                ".#....#...",
                                "##.#.#.#.#",
                                "....#.#.#.",
                                ".##..###.#",
                                "..#...##..",
                                "..##....##",
                                "......#...",
                                ".####.###.")

            val (loc, seen) = Day10.findMonitorLocation(field)
            assert(loc == Coordinate(1,2))
            assert(seen == 35)
        }
        it("works with medium example 3"){
            val field = List(   ".#..#..###",
                                "####.###.#",
                                "....###.#.",
                                "..###.##.#",
                                "##.##.#.#.",
                                "....###..#",
                                "..#.#..#.#",
                                "#..#.#.###",
                                ".##...##.#",
                                ".....#.#..")

            val (loc, seen) = Day10.findMonitorLocation(field)
            assert(loc == Coordinate(6,3))
            assert(seen == 41)
        }
        it("works with large example"){
            val field = List(   
                ".#..##.###...#######",
                "##.############..##.",
                ".#.######.########.#",
                ".###.#######.####.#.",
                "#####.##.#.##.###.##",
                "..#####..#.#########",
                "####################",
                "#.####....###.#.#.##",
                "##.#################",
                "#####.##.###..####..",
                "..######..##.#######",
                "####.##.####...##..#",
                ".#####..#.######.###",
                "##...#.##########...",
                "#.##########.#######",
                ".####.#.###.###.#.##",
                "....##.##.###..#####",
                ".#.#.###########.###",
                "#.#.#.#####.####.###",
                "###.##.####.##.#..##")

            val (loc, seen) = Day10.findMonitorLocation(field)
            assert(loc == Coordinate(11,13))
            assert(seen == 210)
        }
    }
    describe("Ordered asteroid list"){
        describe("Small example"){
            it("works in the top right quadrant"){
                val field = List(
                    ".#....###13...#..",
                    "##...##.02#56..8#",
                    "##...#...4.79012.",
                    "..#.....X...3##..",
                    "..#.#.....#....##")
                val (size, asteroids) = Day10.getAsteroids(field)
                val monitor = Coordinate(8,3)
                val seen = Day10.scanQuadrant(asteroids, monitor, size, Day10.TOP_RIGHT).toList
                val ordered = Day10.orderByArcTan(seen, monitor)
                assert(ordered(0) == Coordinate(8,1))
                assert(ordered(1) == Coordinate(9,0))
                assert(ordered(2) == Coordinate(9,1))
                assert(ordered(3) == Coordinate(10,0))
                assert(ordered(4) == Coordinate(9,2))
                assert(ordered(5) == Coordinate(11,1))
                assert(ordered(6) == Coordinate(12,1))
                assert(ordered(7) == Coordinate(11,2))
                assert(ordered(8) == Coordinate(15,1))
                assert(ordered(9) == Coordinate(12,2))
                assert(ordered(10) == Coordinate(13,2))
                assert(ordered(11) == Coordinate(14,2))
                assert(ordered(12) == Coordinate(15,2))
                assert(ordered(13) == Coordinate(12,3))
            }
            it("works in the bottom right quadrant"){
                val field = List(
                    ".#....###.....#..",
                    "##...##...#.....#",
                    "##...#......1234.",
                    "..#.....X...5##..",
                    "..#.#.....2....10")
                val (size, asteroids) = Day10.getAsteroids(field)
                val monitor = Coordinate(8,3)
                val seen = Day10.scanQuadrant(asteroids, monitor, size, Day10.BOTTOM_RIGHT).toList
                val ordered = Day10.orderByArcTan(seen, monitor)
                assert(ordered(0) == Coordinate(16,4))
                assert(ordered(1) == Coordinate(15,4))
                assert(ordered(2) == Coordinate(10,4))
            }
            it("works in the bottom left quadrant"){
                val field = List(
                    ".#....###.....#..",
                    "##...##...#.....#",
                    "##...#...........",
                    "..2.....X....##..",
                    "..1.0............")
                val (size, asteroids) = Day10.getAsteroids(field)
                val monitor = Coordinate(8,3)
                val seen = Day10.scanQuadrant(asteroids, monitor, size, Day10.BOTTOM_LEFT).toList
                val ordered = Day10.orderByArcTan(seen, monitor)
                assert(ordered(0) == Coordinate(4,4))
                assert(ordered(1) == Coordinate(2,4))
                assert(ordered(2) == Coordinate(2,3))
            }
            it("works in the top left quadrant"){
                val field = List(
                    ".5....89#.....#..",
                    "23...67...#.....#",
                    "01...4...........",
                    "........X....##..",
                    ".................")
                val (size, asteroids) = Day10.getAsteroids(field)
                val monitor = Coordinate(8,3)
                val seen = Day10.scanQuadrant(asteroids, monitor, size, Day10.TOP_LEFT).toList
                val ordered = Day10.orderByArcTan(seen, monitor)
                assert(ordered(0) == Coordinate(0,2))
                assert(ordered(1) == Coordinate(1,2))
                assert(ordered(2) == Coordinate(0,1))
                assert(ordered(3) == Coordinate(1,1))
                assert(ordered(4) == Coordinate(5,2))
                assert(ordered(5) == Coordinate(1,0))
                assert(ordered(6) == Coordinate(5,1))
                assert(ordered(7) == Coordinate(6,1))
                assert(ordered(8) == Coordinate(6,0))
                assert(ordered(9) == Coordinate(7,0))
            }
        }
        describe("Large Example"){
            val field = List(   
                ".#..##.###...#######",
                "##.############..##.",
                ".#.######.########.#",
                ".###.#######.####.#.",
                "#####.##.#.##.###.##",
                "..#####..#.#########",
                "####################",
                "#.####....###.#.#.##",
                "##.#################",
                "#####.##.###..####..",
                "..######..##.#######",
                "####.##.####...##..#",
                ".#####..#.######.###",
                "##...#.####X#####...",
                "#.##########.#######",
                ".####.#.###.###.#.##",
                "....##.##.###..#####",
                ".#.#.###########.###",
                "#.#.#.#####.####.###",
                "###.##.####.##.#..##")
            
            val monitor = Coordinate(11,13)
            val (size, asteroids) = Day10.getAsteroids(field)
            it("vaporize works correctly"){
                val vaporize = Day10.getVaporizeOrder(asteroids, monitor, size)
                assert(vaporize(0) == Coordinate(11,12))
                assert(vaporize(1) == Coordinate(12,1))
                assert(vaporize(2) == Coordinate(12,2))
                assert(vaporize(9) == Coordinate(12,8))
                assert(vaporize(19) == Coordinate(16,0))
                assert(vaporize(49) == Coordinate(16,9))
                assert(vaporize(99) == Coordinate(10,16))
                assert(vaporize(198) == Coordinate(9,6))
                assert(vaporize(199) == Coordinate(8,2))
                assert(vaporize(200) == Coordinate(10,9))
            }

        }
    }
}