package intcode

import scala.annotation.tailrec
import scala.collection.mutable

class IntcodeComputer(val program:Array[Long],
                      val inputs:mutable.Queue[Long]) {

  val output = new StringBuilder
  var complete = false
  var currentPointer = 0
  var relativeBase = 0

  def input(i:Long) = inputs.enqueue(i)
  
  def getParamValue(pType:Int, pPosition:Int): Long = {
    val raw = program(pPosition)
    if(pType == 0) program(raw.intValue)
    else if (pType == 1) raw
    else program(raw.intValue + relativeBase)
  }

  def run(input: Long):Long = {
    val lastOutput = output.toString
    output.clear()
    inputs.enqueue(input)
    val result = compute(currentPointer)
    result.toLongOption.getOrElse(lastOutput.toLongOption.getOrElse(0))
  }
  @tailrec
  final def compute(pointer: Int): String = {

    val opCode:Int = program(pointer).intValue
    val cmd:Int = opCode % 100
    if(cmd == 99) {
      complete = true
      output.toString
    }
    else {
      val pType1 = (opCode.intValue / 100) % 10
      val pType2 = (opCode.intValue / 1000) % 10
      val pType3 = opCode.intValue / 10000
      val param1:Long = getParamValue(pType1, pointer + 1)
      val param2:Long = {
        // ops 3 and 4 only take one param, so trying to get the second could cause an error
        if(cmd != 3 && cmd != 4 && cmd != 9){
          getParamValue(pType2, pointer + 2)
        }
        else 0
      }
      val writeLoc:Int = {
        if(cmd == 3) {
          if (pType1 == 0)program(pointer + 1).intValue
          // The relativeBase needs to be added to the *value* of what's in the program
          else if(pType1 == 2) program(pointer + 1).intValue + relativeBase
          else -1

        }
        else if(Array(1,2,7,8).contains(cmd)) {
          if(pType3 == 0) program(pointer + 3).intValue
          else if(pType3 == 2) program(pointer + 3).intValue + relativeBase
          else -1
        }
        else 0
      }
      var pause = false
      var newPointer:Int = pointer
      cmd match {
        case 1 => {
          program(writeLoc) = param1 + param2
          newPointer += 4
        }
        case 2 => {
          program(writeLoc) = param1 * param2
          newPointer += 4
        }
        case 3 => {
          if(inputs.nonEmpty) program(writeLoc) = inputs.dequeue
          else pause = true //pause and wait for new input
          newPointer += 2
        }
        case 4 => {
          output.append(param1)
          newPointer += 2
        }
        case 5 => {
          if(param1 != 0) newPointer = param2.intValue
          else newPointer += 3 //opcode + 2 parameters
        }
        case 6 => {
          if(param1 == 0) newPointer = param2.intValue
          else newPointer += 3
        }
        case 7 => {
          if(param1 < param2) program(writeLoc) = 1
          else program(writeLoc) = 0
          newPointer += 4
        }
        case 8 => {
          if(param1 == param2) program(writeLoc) = 1
          else program(writeLoc) = 0
          newPointer += 4
        }
          /*
          Opcode 9 adjusts the relative base by the value of its only parameter.
          The relative base increases (or decreases, if the value is negative)
          by the value of the parameter.
           */
        case 9 => {
          relativeBase += param1.intValue
          newPointer += 2
        }

      }
      if(pause) {
        currentPointer = pointer //we want to be able to pick up where we left off
        output.toString
      }
      else compute(newPointer)
    }
  }
}

object IntcodeComputer{
  def apply(program:Array[Long], phase:Long = -1): IntcodeComputer = {
    val arr:Array[Long] = new Array[Long](5000)
    program.copyToArray(arr)
    new IntcodeComputer(arr, {if(phase >= 0)mutable.Queue(phase) else mutable.Queue()})
  }
}
