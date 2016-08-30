package org.dgrald

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 8/28/16.
  */
class CorrectorSpec extends Specification {


  "Should run correctors in the right order" in {
    val input = "This is one. This is two."

    val output = WordServiceCorrector.correct(input)

    output must_== "This is one.\nThis is two."
  }
}
