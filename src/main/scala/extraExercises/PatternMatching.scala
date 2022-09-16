package extraExercises

object PatternMatching extends App {

  class EnterAnyValue (val number: Int)

  object EnterAnyThing{
    def unapply( enterAnyValue: EnterAnyValue) : Option[(String)] = {
      Some(
        if (enterAnyValue.number < 10) "This is single Digit"
        else if(enterAnyValue.number % 2 == 0) "This is even Number"
        else "Nothing to say Anything"
      )
    }
  }

  val enterAnyValue = new EnterAnyValue(2)
  val testing = enterAnyValue match {
    case EnterAnyThing(a) => s"Your Answer of ${enterAnyValue.number} is $a."
  }

  println(testing)

}
