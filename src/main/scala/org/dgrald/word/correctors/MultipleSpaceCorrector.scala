package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
object MultipleSpaceCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val multipleSpacesRegex = " {2,}".r
    multipleSpacesRegex.replaceAllIn(input, " ")
  }
}
