package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 9/18/16.
  */
class PeriodRemoverCorrectorSpec extends Specification {

  "Should remove periods when there is a new line preceded by any number of spaces and a period or at the end of the text" in {
    val input = "Something one.\nSomething two.  \nSomething three."

    val output = PeriodRemoverCorrector.correct(input)

    output must_== "Something one\nSomething two\nSomething three"
  }
}
