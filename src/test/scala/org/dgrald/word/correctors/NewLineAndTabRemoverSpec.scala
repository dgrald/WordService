package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylan
  * grald on 8/28/16.
  */
class NewLineAndTabRemoverSpec extends Specification {

  "Should replace tabs and new lines with spaces" in {
    val input = "Something with multiple\rnew lines\n\nand \ttabs \tin a string."

    val output = NewLineAndTabRemoverCorrector.correct(input)

    output must_== "Something with multiple new lines  and  tabs  in a string."
  }

  "Should not add a space if a new line is proceeded by a hyphen" in {
    val input = "Something that has a hyphenated-\nword in it,\nman."

    val output = NewLineAndTabRemoverCorrector.correct(input)

    output must_== "Something that has a hyphenated-word in it, man."
  }
}
