package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/18/16.
  */
object BulletCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val bulletUnicode = "\u2022"
    val regex = s"$bulletUnicode[\\s]*".r
    regex.replaceAllIn(input, "\n")
  }
}
