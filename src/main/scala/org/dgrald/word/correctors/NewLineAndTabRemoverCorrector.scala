package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
object NewLineAndTabRemoverCorrector extends Corrector {

  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val tabsRegex = "\\t".r
    val input2 = tabsRegex.replaceAllIn(input, " ")

    val hyphensSurroundedByWhiteSpace = "[\\s]*-[\\s]*".r
    val input3 = hyphensSurroundedByWhiteSpace.replaceAllIn(input2, "-")

    val newLineRegex = "[\\n\\r]".r
    newLineRegex.replaceAllIn(input3, " ")
  }

}
