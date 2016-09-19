package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/29/16.
  */
object PlusAndMinusCorrector extends Corrector {
  val plusOrMinusSymbol = "\u00B1"

  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val regex = s"[\\s]*($plusOrMinusSymbol|(\\+/-))[\\s]*[0-9.]+".r

    regex.replaceAllIn(input, "")
  }
}
