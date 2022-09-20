package exercise.advanceScala

/**
 * Created by Harshang
 */

import scala.annotation.tailrec

object Recap extends App {

  val aCondition: Boolean = false
  val aConditionalValue = if(aCondition) 42 else 36
  println(aConditionalValue)

//   instructions vs expressions
//   compiler infers types for us

  val aCodeBlock = {
    if (aCondition) 54
    println(69)
  }

//   Unit => void
  val theUnit: Unit = println("hello,Scala")

  // Recursion: stack and tail
  @tailrec
  def factorial(n: Int, accumulator: Int): Int =
    if (n <= 0) accumulator
    else factorial(n-1, n * accumulator)
  val number = factorial(10,1)
  println(number)
    // Object-oriented Programming

    class Animal
    class Dog extends Animal
    val aDog: Animal = new Dog
    trait Carnivore {
      def eat(a: Animal): Unit
    }

    class Crocodile extends Animal with Carnivore {
       override def eat(a : Animal): Unit = println(a.toString + " is dead by crocodile")
    }
    val crocodile = new Crocodile
    crocodile.eat(aDog)

  //  generics
  abstract class MyList[+A]

  // singletons and compaions
  object MyList

  case class Person(name: String, age: Int)

  //  val throwsException = throw new RuntimeException

  val aPotentialFailure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "I caught an Exception"
  } finally {
    println("Some Logs")
  }

  val incremeter = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  println(incremeter(1))

  val anonymousIncrementer = (x: Int) => x + 1
  List(1, 2, 3).map(anonymousIncrementer)

  val pairs = for {
    num <- List(1, 2, 3)
    char <- List('a', 'b', 'c')
  } yield println(num + "-" + char)

  val aMap = Map(
    "Harshang" -> 121,
    "Manthan" -> 125
  )

  println(aMap)

  val anOption = Some(2)
  println(anOption)

  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "This is second number"
    case _ => "There is nothing say anything"
  }
  println(order)

}
