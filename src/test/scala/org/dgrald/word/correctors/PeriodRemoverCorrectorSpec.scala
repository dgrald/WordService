package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/18/16.
  */
class PeriodRemoverCorrectorSpec extends Specification {

  "Should remove periods when there is a new line preceded by any number of spaces and a period or at the end of the text" in {
    val input = "Something one.\nSomething two.  \nSomething three."

    val output = PeriodRemoverCorrector.correct(input)

    output must_== "Something one\nSomething two\nSomething three"
  }

  "Should not remove the last character of a string if it's not a period" in {
    val input = "Something not ending a period"

    val output = PeriodRemoverCorrector.correct(input)

    output must_== input
  }

  "Should maintain input if there are no periods" in {
    val input = "Something one\nSomething two\nSomething three"

    val output = PeriodRemoverCorrector.correct(input)

    output must_== input
  }

  "Should not remove period at end of line in" >> {
    Fragment.foreach(Constants.exceptionsToTrailingPeriod){ exception =>
      val input = s"Something ending in $exception\nAnd another thing."
      s"'$input'" ! {
        val output = PeriodRemoverCorrector.correct(input)

        output must_== s"Something ending in $exception\nAnd another thing"
      }
    }
  }

  "Should not remove period at end of" >> {
    Fragment.foreach(Constants.exceptionsToTrailingPeriod){ exception =>
      val input = s"Something ending in $exception"
      s"'$input'" ! {
        val output = PeriodRemoverCorrector.correct(input)

        output must_== input
      }
    }
  }
}
