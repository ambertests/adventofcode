import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec
import scala.math.ceil

object Day14 extends Day {
  override var day: Int = 14

  def processInput(input:List[String]):Map[String, Formula] = {
    input.map(line => {
        val f = Formula(line)
        (f.name -> f)
    }).toMap
  }

  @tailrec
  def calculateOre(mapping:Map[String, Formula], requirements:List[Requirement], totalOre:Int):Int ={
    if (requirements.isEmpty) totalOre
    else{
      val req = requirements.head
      if (req.name == "ORE"){
        calculateOre(mapping, requirements.tail, totalOre + req.amount)
      }else{
        var existingAmounts = requirements.tail.map(rt => {rt.name -> rt.amount}).toMap
        val f = mapping.get(req.name).get
        f.requirements.foreach(r => {
          val batchCount = {
            if(f.batchSize >= req.amount) 1
            else if (req.amount % f.batchSize == 0) req.amount / f.batchSize
            else (req.amount/f.batchSize) + 1
          }
          val reqAmount = r.amount * batchCount
          var prevAmount = existingAmounts.getOrElse(r.name, 0)
          existingAmounts = existingAmounts + (r.name -> (prevAmount + reqAmount))
        })
        calculateOre(mapping, 
          existingAmounts.keys
            .map(k => {
              val amt = existingAmounts.getOrElse(k,0)
              new Requirement(k, amt)
            }).toList, 
          totalOre)
      }
    }
  }

  def calculateOreForFuel(mapping:Map[String, Formula]):Int={
    val fuelFormula = mapping.get("FUEL").get
    @tailrec
    def totalRequirements(consolidated:Map[String, Int], requirements:List[Requirement]):Map[String, Int] = {
      if (requirements.isEmpty){
        consolidated
      }else{
        var req = requirements.head
        var f = mapping.get(req.name).get
        var expandedReq = f.requirements.map(r => 
            new Requirement(r.name, r.amount * batchCount(f.batchSize, req.amount))).toList
        if(f.requirements(0).name != "ORE"){
          totalRequirements(consolidated, requirements.tail :++ expandedReq)
        }
        else {
          var sub = consolidated.getOrElse(req.name, 0)
          var c = consolidated + (req.name -> (sub + req.amount))
          totalRequirements(c, requirements.tail)
        }
      }
    }

    def batchCount(batchSize:Int, targetAmount:Int):Int ={
      if(batchSize >= targetAmount) 1
      else if (targetAmount % batchSize == 0) targetAmount / batchSize
      else (targetAmount/batchSize) + 1
    }
    val requirements = totalRequirements(Map[String, Int](), fuelFormula.requirements)
    println(requirements)
    requirements.keys.map(name =>{
      val needed = requirements.getOrElse(name, 0)
      val formula = mapping.get(name).get
      if(formula.requirements(0).name == "ORE"){
        val oreReq = formula.requirements(0)
        val bc = batchCount(formula.batchSize, needed)
        println(s"need $bc sets of " + oreReq.amount + s" to make $needed $name (" + formula.batchSize + " per batch)")
        bc * oreReq.amount
      }else 0
    }).sum 
  }    
}   
case class Requirement(name:String, amount:Int)
case class Formula(name:String, batchSize:Int, requirements:List[Requirement])

object Formula{
    def apply(line:String):Formula = {
        //"7 A, 1 B => 1 C"
        val arr = line.split(" => ")
        val key = arr(1).split(" ")(1)
        val amt = arr(1).split(" ")(0).toInt
        val req = arr(0).split(", ")
                        .map(_.split(" "))
                        .map(s=>{new Requirement(s(1), s(0).toInt)})
                        .toList
        Formula(key, amt, req)
    }
}