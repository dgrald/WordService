package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 9/15/16.
  */
class FindAndReplaceCorrectorSpec extends Specification {

  "Should correct '?' with 'ti' when instructed" in {
    val instructions = List(("?", "ti", false))
    val input = "Something with ?e and abbrevia?on."

    val output = FindAndReplaceCorrector.correct(input, instructions)

    output must_== "Something with tie and abbreviation."
  }

  "Should correct '4' with 'ft' and '3' with 'ti' when instructed" in {
    val instructions = List(("4", "ft", false), ("3", "ti", false))
    val input = "Something with li4 and abbrevia3on in it."

    val output = FindAndReplaceCorrector.correct(input, instructions)

    output must_== "Something with lift and abbreviation in it."
  }

  "Should correct '4' with 'ft' and '3' with 'ti' when instructed all but the first time when instructed" in {
    val instructions = List(("4", "ft", true), ("3", "ti", true))
    val input = "Something with li4, gi4, 3re, and abbrevia3on in it."

    val output = FindAndReplaceCorrector.correct(input, instructions)

    output must_== "Something with li4, gift, 3re, and abbreviation in it."
  }

  "Should not throw error when other instructions includes an object that is not a tuple of strings and a boolean" in {
    val instructions = List(("?", "ti", false), true, List("Random"), 5)
    val input = "Something with ?e and abbrevia?on."

    val output = FindAndReplaceCorrector.correct(input, instructions)

    output must_== "Something with tie and abbreviation."
  }

}
