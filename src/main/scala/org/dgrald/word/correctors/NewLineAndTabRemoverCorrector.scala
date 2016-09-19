package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
object NewLineAndTabRemoverCorrector extends Corrector {

  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val hyphensSurroundedByTabsOrNewLinesRegex = "([\\n\\r\\t]*)(-)([\\n\\r\\t]*)".r
    val input2 = hyphensSurroundedByTabsOrNewLinesRegex.replaceAllIn(input, "-")

    val tabsRegex = "\\t".r
    val input3 = tabsRegex.replaceAllIn(input2, " ")

    val newLineRegex = "[\\n\\r]".r
    newLineRegex.replaceAllIn(input3, " ")
  }

}
