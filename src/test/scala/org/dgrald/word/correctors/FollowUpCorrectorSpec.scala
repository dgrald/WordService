package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/8/16.
  */
class FollowUpCorrectorSpec extends Specification {

  "Should correct" >> {
    Fragment.foreach(List("Follow", "follow")) { word =>
      s"'$word up' to '$word-up'"  ! {
        val input = s"Something with $word up and $word up."
        FollowUpCorrector.correct(input) must_== s"Something with $word-up and $word-up."
      }
    }
  }
}
