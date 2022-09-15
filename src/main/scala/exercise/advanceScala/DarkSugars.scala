package exercise.advanceScala

import scala.::
import scala.util.Try

object DarkSugars extends App {
  def singleArgMethod(number: Int): String = s"$number little puppies"

  println(singleArgMethod(20))

  val description = singleArgMethod {
    42

  }

  println(description)

  val aTryInstance = Try {
    throw new RuntimeException

  }

  List(1,2,3).map { x =>
    x + 1
  }

  trait Action {
    def act(x:Int): Int

  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val instance: Action = (x :Int ) => x + 1

  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Hello, Scala")
  })

  aThread.run()

  val aSweeterThread = new Thread(() => println("sweet, scala"))

    aSweeterThread.run()

    abstract class AnAbstractType {

      def implemented: Int = 23
      def f(a: Int): Unit

    }

    val anAbstractInstance: AnAbstractType = (a: Int) => println("Sweet")

    println(anAbstractInstance.f(5))

    val prependedList = 2 :: List(1,2,3,4,5,6,6,3,4)

    println(prependedList)

  val aPrependedList = 234 :: 3 :: 43 :: List(1,23,3,3,5,4,5,5) .:: (2783) .:: (2,3,4,5,6,7,8)
//    val aPrependedList = List(4,4).::(3).::(3).::(2)
  println(aPrependedList)

  class MyStream[T] {
    def -->: (value: T): MyStream[T] = this

  }

  val myStream = 1 -->: 2 -->: 3 -->: 4 -->: new MyStream[Int]

  println(myStream.-->:(678))


  class Boy(name: String) {

    def - (message: String) : Unit = println(s"$name said that $message")

  }

  val harshang = new Boy("Harshang")
  harshang - ("Scala is Very awsome language")



}
