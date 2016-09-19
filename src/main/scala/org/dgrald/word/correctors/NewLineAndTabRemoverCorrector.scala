package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
object NewLineAndTabRemoverCorrector extends Corrector {

  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val tabsRegex = "\\t".r
    val tabsReplaced = tabsRegex.replaceAllIn(input, " ")

    val newLineRegex = "[\\n\\r]".r
    newLineRegex.replaceAllIn(tabsReplaced, regexMatch => {
      val start = regexMatch.start
      if(start > 0) {
        if(tabsReplaced.charAt(start - 1) == '-') {
          ""
        } else {
          " "
        }
      } else {
        " "
      }
    })
  }

}
