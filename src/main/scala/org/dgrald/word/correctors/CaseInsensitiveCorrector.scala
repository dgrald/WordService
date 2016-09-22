package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/21/16.
  */
object CaseInsensitiveCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val regex = s"(?i)euroscore".r

    regex.replaceAllIn(input, "EuroSCORE")
  }
}
