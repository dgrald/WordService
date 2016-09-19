package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
object NewLineAndTabRemoverCorrector extends Corrector {

  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val tabsOrNewLines = "\\n\\r\\t"

    val commaSurroundedByTabsOrNewLinesRegex = s"[$tabsOrNewLines]*,[$tabsOrNewLines]*".r
    val input2 = commaSurroundedByTabsOrNewLinesRegex.replaceAllIn(input, ", ")

    val hyphensSurroundedByTabsOrNewLinesRegex = s"[$tabsOrNewLines]*-[$tabsOrNewLines]*".r
    val input3 = hyphensSurroundedByTabsOrNewLinesRegex.replaceAllIn(input2, "-")

    val tabsRegex = "\\t".r
    val input4 = tabsRegex.replaceAllIn(input3, " ")

    val newLineRegex = "[\\n\\r]".r
    newLineRegex.replaceAllIn(input4, " ")
  }

}
