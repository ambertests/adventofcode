import util.control.Breaks._

object Day08 extends Day {
  override var day = 8
  def getLayers(encoded: String, width: Int, height: Int):Vector[Vector[String]] = {
    val vol = width*height
    val layerCount = encoded.length / vol
    (for (l <- 0 until layerCount) yield {
      (for (h <- 0 until height) yield {
        val start = (l*vol) + (h*width)
        encoded.slice(start, start + width)
      }).toVector
    }).toVector
  }

  def charCount(layer:Vector[String], char:Char):Int = {
    (for (r <- layer) yield (r.toCharArray.count(c => c == char))).sum
  }
  def doPart1(layers:Vector[Vector[String]]):Int = {
    var zeroCount = Int.MaxValue
    var minZeroLayer:Vector[String] = null
    for (l <- layers) {
      val zeros = charCount(l, '0')
      if(zeros < zeroCount){
        zeroCount = zeros
        minZeroLayer = l
      }
    }
    val oneCount = charCount(minZeroLayer, '1')
    val twoCount = charCount(minZeroLayer, '2')
    oneCount*twoCount
  }

  def printLayers(layers:Vector[Vector[String]], width:Int, height:Int):String = {
    val sb = new StringBuilder
    sb.addOne('\n')
    for(h <- 0 until height){
      for(w <- 0 until width){
        breakable{
          for (l <- layers){
            val c = l(h).toCharArray()(w)
            if(c != '2'){
              if(c == '0') sb.append(' ')
              else if(c == '1') sb.append('#')
              break
            }
          }
        }
      }
      sb.append('\n')
    }
    sb.toString

  }

  val width = 25
  val height = 6
  val layers = getLayers(getInputString, width, height)
  printPartOne(doPart1(layers))
  printPartTwo(printLayers(layers, width, height))

}
