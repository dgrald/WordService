package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylan
  * grald on 8/28/16.
  */
class RemoveNewLineAndTabCorrectorSpec extends Specification {

  "Should replace tabs and new lines with spaces" in {
    val input = "Something with multiple\rnew lines\n\nand \ttabs \tin a string."

    val output = RemoveNewLineAndTabCorrector.correct(input)

    output must_== "Something with multiple new lines  and  tabs  in a string."
  }
}
