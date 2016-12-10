package org.dgrald.word.correctors

import org.dgrald.StringUtils

/**
  * Created by dylangrald on 12/10/16.
  */
object NewLineCapitalizationCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    def capitalize(line: String): String = {
      if(line.length > 0) {
        StringUtils.capitalize(line)
      } else {
        line
      }
    }

    (for {
      line <- input.split("\n")
    } yield capitalize(line)).mkString("\n")
  }
}
