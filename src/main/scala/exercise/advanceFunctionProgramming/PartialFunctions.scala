package exercise.advanceFunctionProgramming

object PartialFunctions extends App {

  val aFunction = (x: Int) => x + 1

  println(aFunction.apply(121))

  val aFussyFucntion = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

    class FunctionNotApplicableException extends RuntimeException

    val aNicerFussyFucntion = (x: Int) => x match {
      case 1 => 42
      case 2 => 56
      case 5 => 999
    }

  val aPartialFunction: PartialFunction[Int, String] = {
    case 1 => "This is a Case 1"
    case 2 => "This is a Case 2"
    case 5 => "This is a Case 5"
//    case _ => "Nothing to say function"
  }

//  println(aPartialFunction(54))

//  println(aPartialFunction.isDefinedAt(69))

  val printed = aPartialFunction.lift

//  println(printed(5))
//  println(printed(69))

  val orElse = aPartialFunction.orElse[Int,String] {
    case 96 => "Hello My name is ,,,,,,,,,,,,,,,,"
//    case _ => "Hi, My Name is Harshang."
  }

  println(orElse(2))
  println(orElse(96))

//  PF extend normal functions

  val aTotalFunction : Int => Int = {
    case 1 => 99
  }

//  HOFs accept partial functions as well

  val aMappedList = List(1,2,3).map{
    case 1 => "Hello"
    case 2 => "My"
    case 3 => "Name"
    case 4 => "Harshang"
    case _ => "Is"
  }

  println(aMappedList)

  /*val set = Set(1, 2, 4, "Hello")
println(set("hello"))*/

  /*
  * Note :- PF can only have ONE parameter type
  */

}
