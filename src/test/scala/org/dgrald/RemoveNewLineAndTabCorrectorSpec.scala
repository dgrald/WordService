package org.dgrald

import org.specs2.mutable.Specification

/**
  * Created by dylan
  * grald on 8/28/16.
  */
class RemoveNewLineAndTabCorrectorSpec extends Specification {

  "Should strip new line characters" in {
    val input = "Something with multiple\r new lines\n\n and \ttabs \tin a string."

    var output = RemoveNewLineAndTabCorrector.correct(input)

    output must_== "Something with multiple new lines and tabs in a string."
  }

  "Should replace new line characters with space when there is a period followed by a new line" in {
    val input = "Something sentence one.\nSomething sentence two.\n\nSomething sentence three."

    var output = RemoveNewLineAndTabCorrector.correct(input)

    output must_== "Something sentence one. Something sentence two. Something sentence three."
  }
}
