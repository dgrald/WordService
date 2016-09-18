package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/8/16.
  */
object PercentageSpaceCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val regex = "[0-9]+ %".r
    regex.replaceAllIn(input, regexMatch => {
      regexMatch.toString().replaceAll(" ", "")
    })
  }
}
