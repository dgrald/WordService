package org.dgrald

import java.util.Random

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/8/16.
  */
class PercentageSpaceCorrectorSpec extends Specification {

  "It should correct" >> {
    Fragment.foreach(for {_ <- Range(1,5)} yield someInteger) { integer =>
      s"$integer % to $integer%" ! {
        val input = s"Something with $integer % in it."
        PercentageSpaceCorrector.correct(input) must_== s"Something with $integer% in it."
      }
    }
  }

  "It should correct" >> {
    Fragment.foreach(for {_ <- Range(1,5)} yield someDecimal) { decimal =>
      s"$decimal % to $decimal%" ! {
        val input = s"Something with $decimal % in it."
        PercentageSpaceCorrector.correct(input) must_== s"Something with $decimal% in it."
      }
    }
  }

  def someInteger: Int = {
    new Random().nextInt(100)
  }

  def someDecimal: Double = {
    new Random().nextDouble()
  }
}
