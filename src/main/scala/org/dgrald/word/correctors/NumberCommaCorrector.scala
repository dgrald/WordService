package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/20/16.
  */
object NumberCommaCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val regex = "\\b[0-9]+,[0-9]+\\b".r

    regex.replaceAllIn(input, regexMatch => {
      val split = regexMatch.toString().split(",")
      if(split.length == 2) {
        val secondPart = split(1)
        if(split(0).head == '0' || secondPart.length != 3) {
          split.mkString(".")
        } else {
          regexMatch.toString()
        }
      } else {
        regexMatch.toString()
      }
    })
  }
}
