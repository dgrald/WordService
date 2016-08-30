package org.dgrald

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/28/16.
  */
class NewLineCorrectorSpec extends Specification {

  "Should strip new line characters" in {
    val input = s"Something with multiple\r new lines\n\n in a string."

    var output = NewLineCorrector.correct(input)

    output must_== "Something with multiple new lines in a string."
  }
}
