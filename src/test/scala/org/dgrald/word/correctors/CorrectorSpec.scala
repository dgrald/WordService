package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 8/28/16.
  */
class CorrectorSpec extends Specification {

  "Should run correctors in the right order" in {
    val input = "This is something. This is another thing."

    val output = WordServiceCorrector.correct(input, createNewLines = true, removeNewLines = true, addAsterisks = false, List())

    output must_== "This is something\nThis is another thing"
  }
}
