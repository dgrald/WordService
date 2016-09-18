package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/18/16.
  */
object AsteriskCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    (for {
      line <- input.split("\n")
    } yield s"* $line").mkString("\n")
  }
}
