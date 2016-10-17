package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 10/17/16.
  */
class MonthWordCorrectorSpec extends Specification {

  "Should change 'By 12 mo, LVEF decreased by 30%.' to 'By 12 months, LVEF decreased by 30%.'" in {
    val input = "By 12 mo, LVEF decreased by 30%."

    val output = MonthWordCorrector.correct(input)

    output must_== "By 12 months, LVEF decreased by 30%."
  }

  "Should not change 'Something with mo in it without number preceding it'" in {
    val input = "Something with mo in it without number preceding it"

    val output = MonthWordCorrector.correct(input)

    output must_== input
  }

  "Should not change 'Something with 1 moment'" in {
    val input = "Something with 1 moment"

    val output = MonthWordCorrector.correct(input)

    output must_== input
  }

  "Should change 'Something 1 mo' to 'Something 1 month'" in {
    val input = "Something 1 mo"

    val output = MonthWordCorrector.correct(input)

    output must_== "Something 1 month"
  }

}
