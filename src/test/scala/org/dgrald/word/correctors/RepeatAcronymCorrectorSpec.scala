package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 10/30/16.
  */
class RepeatAcronymCorrectorSpec extends Specification {

  "Should change 'Something with USA (USA) in it.' to 'Something USA in it.'" in {
    val input = "Something with USA (USA) in it."

    val output = RepeatAcryonymCorrector.correct(input)

    output must_== "Something with USA in it."
  }

  "Should not change 'Something with something (another thing) in it.'" in {
    val input = "Something with something (another thing) in it."

    val output = RepeatAcryonymCorrector.correct(input)

    output must_== input
  }

}
