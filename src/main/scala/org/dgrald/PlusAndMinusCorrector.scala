package org.dgrald

/**
  * Created by dylangrald on 8/29/16.
  */
object PlusAndMinusCorrector extends Corrector {
  val plusOrMinusSymbol = "\u00B1"

  override def correct(input: String): String = {
    val regex = s"\\s($plusOrMinusSymbol|(\\+/-)) [0-9.]+".r

    regex.replaceAllIn(input, "")
  }
}
