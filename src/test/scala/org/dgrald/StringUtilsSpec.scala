package org.dgrald

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 8/30/16.
  */
class StringUtilsSpec extends Specification {

  "StringUtil.capitalize should capitalize the input string" in {
    val lower = "lower"

    val capitalized = StringUtils.capitalize(lower)

    capitalized must_== "Lower"
  }

  "StringUtil.isCapitalized should return false when the input string is not capitalized" in {
    val lower = "lower"

    val capitalized = StringUtils.isCapitalized(lower)

    capitalized must_== false
  }

  "StringUtil.isCapitalized should return true when the input string is capitalized" in {
    val upper = "Upper"

    val capitalized = StringUtils.isCapitalized(upper)

    capitalized must_== true
  }
}
