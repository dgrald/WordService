package org.dgrald

import scala.util.Random

/**
  * Created by dylangrald on 9/24/16.
  */
object SomeRandom {
  def string: String = {
    val random = new Random()
    Range(1,10).foldRight("")((_, totalString) => totalString :+ random.nextPrintableChar())
  }
}
