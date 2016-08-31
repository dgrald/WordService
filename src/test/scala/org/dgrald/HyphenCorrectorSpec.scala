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

  "Should correct" >> {
    Fragment.foreach(prefixes) { prefix =>
      val prefixToUpperCase = prefix.head.toUpper +: prefix.tail
      s"${prefixToUpperCase}word to $prefix-word"  ! {
        val input = s"${prefixToUpperCase}word at the beginning."
        HyphenCorrector.correct(input) must_== s"$prefixToUpperCase-word at the beginning."
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

  "Should not change" >> {
    Fragment.foreach(prefixes) { prefix =>
      s"Something${prefix}word"  ! {
        val input = s"Something${prefix}word"
        HyphenCorrector.correct(input) must_== input
      }
    }
  }

  val exceptions = List("previous", "presentation", "presenter", "presented", "present", "preclinical", "press", "pregnant", "prevent", "prevented", "prevents")

  "Should not change exceptions to the rule for" >> {
    Fragment.foreach(exceptions ++ (for{e <- exceptions} yield StringUtils.capitalize(e))) { exception =>
      s"$exception"  ! {
        val input = s"Something with $exception."
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
