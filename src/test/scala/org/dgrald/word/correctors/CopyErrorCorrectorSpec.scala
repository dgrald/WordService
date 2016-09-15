package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 9/15/16.
  */
class CopyErrorCorrectorSpec extends Specification {

  "Should correct '?' with 'ti' when instructed" in {
    val instructions = List(("?", "ti"))
    val input = "Something with ?e and abbrevia?on."

    val output = CopyErrorCorrector.correct(input, instructions)

    output must_== "Something with tie and abbreviation."
  }

  "Should correct '4' with 'ft' and '3' with 'ti' when instructed" in {
    val instructions = List(("4", "ft"), ("3", "ti"))
    val input = "Something with li4 and abbrevia3on in it."

    val output = CopyErrorCorrector.correct(input, instructions)

    output must_== "Something with lift and abbreviation in it."
  }

  "Should not throw error when other instructions includes an object that is not a tuple of strings" in {
    val instructions = List(("?", "ti"), true)
    val input = "Something with ?e and abbrevia?on."

    val output = CopyErrorCorrector.correct(input, instructions)

    output must_== "Something with tie and abbreviation."
  }

}
