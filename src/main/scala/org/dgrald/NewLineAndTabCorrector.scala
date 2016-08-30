package org.dgrald

/**
  * Created by dylangrald on 8/28/16.
  */
object NewLineAndTabCorrector extends Corrector {

  override def correct(input: String): String = {
    val multipleNewLineRegex = "[\\n\\r\\t]".r
    multipleNewLineRegex.replaceAllIn(input, "")
  }

}
