package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/30/16.
  */
object VersusCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val vsRegex = "vs ".r
    val newInput = vsRegex.replaceAllIn(input, "vs. ")
    val versusRegex = "versus".r
    versusRegex.replaceAllIn(newInput, "vs.")
  }
}
