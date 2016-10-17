package org.dgrald.word.correctors

/**
  * Created by dylangrald on 10/17/16.
  */
object MonthWordCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val regex = "[0-9]+ mo\\b".r
    regex.replaceAllIn(input, (regexMatch) => {
      val replaceWith = if(regexMatch.toString().equals("1 mo")) {
        "month"
      } else {
        "months"
      }
      regexMatch.toString().replaceAll("mo", replaceWith)
    })
  }
}
