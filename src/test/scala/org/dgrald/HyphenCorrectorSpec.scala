package org.dgrald

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/30/16.
  */
class HyphenCorrectorSpec extends Specification {

  val prefixes = List("pre", "post", "intra", "anti")

  "Should correct" >> {
    Fragment.foreach(prefixes) { prefix =>
      s"${prefix}word to $prefix-word"  ! {
        val input = s"Something with ${prefix}word."
        HyphenCorrector.correct(input) must_== s"Something with $prefix-word."
      }
    }
  }

  "Should not change" >> {
    Fragment.foreach(prefixes) { prefix =>
      s"$prefix-word"  ! {
        val input = s"Something with $prefix-word."
        HyphenCorrector.correct(input) must_== input
      }
    }
  }

  "Should replace all prefixes with hyphenated prefix" in {
    val input = (for {
      prefix <- prefixes
    } yield s"${prefix}word").mkString(" ")

    val output = HyphenCorrector.correct(input)

    val expected = (for {
      prefix <- prefixes
    } yield s"$prefix-word").mkString(" ")
    output must_== expected
  }
}
