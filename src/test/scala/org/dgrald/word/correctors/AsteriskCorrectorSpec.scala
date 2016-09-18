package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 9/18/16.
  */
class AsteriskCorrectorSpec extends Specification {

  "Should add an asterisk and a space at the beginning of each line" in {
    val input = "Line one\nLine two\nLine three"

    val output = AsteriskCorrector.correct(input)

    output must_== "* Line one\n* Line two\n* Line three"
  }
}
