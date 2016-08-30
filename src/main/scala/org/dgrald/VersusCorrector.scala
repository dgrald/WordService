package org.dgrald

/**
  * Created by dylangrald on 8/30/16.
  */
object VersusCorrector extends Corrector {
  override def correct(input: String): String = {
    val vsRegex = "vs ".r
    val newInput = vsRegex.replaceAllIn(input, "vs. ")
    val versusRegex = "versus".r
    versusRegex.replaceAllIn(newInput, "vs.")
  }
}
