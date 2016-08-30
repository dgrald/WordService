package org.dgrald

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylan
  * grald on 8/28/16.
  */
class NewLineAndTabCorrectorSpec extends Specification {

  "Should strip new line characters" in {
    val input = s"Something with multiple\r new lines\n\n and \ttabs \tin a string."

    var output = NewLineAndTabCorrector.correct(input)

    output must_== "Something with multiple new lines and tabs in a string."
  }
}
