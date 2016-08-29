package org.dgrald

/**
  * Created by dylangrald on 8/28/16.
  */
trait Corrector {
  def correct(input: String): String
}

object WordServiceCorrector {
  def correct(input: String): String = {
    List(NewLineCorrector, MultipleSpaceCorrector, ComparisonSymbolCorrector).foldRight(List(input))((corrector, resultList) => {
      corrector.correct(resultList.head) +: resultList
    }).head
  }
}
