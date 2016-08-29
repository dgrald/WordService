package org.dgrald

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 8/28/16.
  */
class MultipleSpaceCorrectorSpec extends Specification {

  "Should replace instances of multiple spaces with a single space" in {
    val input = "Something with   multiple   spaces."

    val output = MultipleSpaceCorrector.correct(input)

    output must_== "Something with multiple spaces."
  }

}
