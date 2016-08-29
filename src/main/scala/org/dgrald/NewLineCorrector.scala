package org.dgrald

/**
  * Created by dylangrald on 8/28/16.
  */
object NewLineCorrector extends Corrector {

  override def correct(input: String): String = {
    val multipleNewLineRegex = "\\n{2,}".r
    multipleNewLineRegex.replaceAllIn(input, "")
  }

}
