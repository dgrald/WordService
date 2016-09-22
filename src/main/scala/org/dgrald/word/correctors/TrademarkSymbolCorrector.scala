package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/22/16.
  */
object TrademarkSymbolCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val registeredTrademarkUnicode = "\u00AE"
    val trademarkUnicode = "\u2122"

    val regex = s"($registeredTrademarkUnicode)|($trademarkUnicode)".r
    MultipleSpaceCorrector.correct(regex.replaceAllIn(input, ""))
  }
}
