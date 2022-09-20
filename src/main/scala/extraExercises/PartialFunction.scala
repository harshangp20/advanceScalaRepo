package extraExercises

import scala.io.StdIn

object PartialFunction extends App {

  print("Enter value for Variable:-")
  val variable = StdIn.readLine()
  println("Value of variable is :-" + variable )

  val aManualFussyFunction = new PartialFunction[Int, String] {

    override def apply(x: Int): String = x match {
      case 1 => "This is case 1"
      case 2 => "This is case 2"
      case 3 => "This is case 3"
      case 4 => "This is case 4"
      case _ => "Nothing to say anything"
    }

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 3 || x == 4

  }

  println(aManualFussyFunction.apply(96))

  val chatbot: PartialFunction[String,String] = {

    case "hyy" => "Hyy, My name is Robo3t"
    case "Call to Papa" => "Calling papa......"
    case "Open Youtube" => "Opening Youtube....."
    case "Open WhatsApp" => "Opening WhatsApp........."
    case "Bye" => "Bye, Nice to meet You"
    case _ => "Sorry tell me again i can`t recognize!!!!"

  }

  print("Hello I am Chat Bot Tell me Anything: ")
  val speaker = scala.io.Source.stdin.getLines().foreach(chatbotReads => println( chatbot(chatbotReads)))


}
