package org.dgrald

/**
  * Created by dylangrald on 8/30/16.
  */
object StringUtils {

  def capitalize(input: String): String = {
    input.head.toUpper +: input.tail
  }

  def isCapitalized(input: String): Boolean = {
    input.head.isUpper
  }
}
