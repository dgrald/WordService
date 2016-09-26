package org.dgrald

import org.dgrald.file.parsers.FileParser
import org.dgrald.word.correctors.WordServiceCorrector
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 9/24/16.
  */
class ContentExtractorSpec extends Specification with Mockito {

  "Should default to running input through corrector" in {
    val correctorMock = mock[WordServiceCorrector]
    val fileParser = mock[FileParser]
    val contentExtractor = ContentExtractor(correctorMock, fileParser)
    val requestParams = mock[RequestParams]

    val input = SomeRandom.string
    requestParams.getParam("filecontents").returns(Some(input))
    requestParams.getParam("linebreaks", "false").returns("false")
    requestParams.getParam("asterisks", "false").returns("false")
    requestParams.getParam("removenewlines", "false").returns("false")
    Range(1, 4).foreach(num => {
      requestParams.getParam(s"replace$num", "").returns("")
      requestParams.getParam(s"replacement$num", "").returns("")
      requestParams.getParam(s"replaceallbutfirst$num", "").returns("")
    })

    val expected = SomeRandom.string
    correctorMock.correct(input, createNewLines = false, removeNewLines = false, addAsterisks = false, List()).returns(expected)

    val actual = contentExtractor.getContent(requestParams)

    actual must_== (input, expected)
  }

  "Should default createNewLines and removeNewlines to 'on'" in {
    val correctorMock = mock[WordServiceCorrector]
    val fileParser = mock[FileParser]
    val contentExtractor = ContentExtractor(correctorMock, fileParser)
    val requestParams = mock[RequestParams]

    val input = SomeRandom.string
    requestParams.getParam("filecontents").returns(Some(input))
    requestParams.getParam("linebreaks", "false").returns("on")
    requestParams.getParam("asterisks", "false").returns("false")
    requestParams.getParam("removenewlines", "false").returns("on")
    Range(1, 4).foreach(num => {
      requestParams.getParam(s"replace$num", "").returns("")
      requestParams.getParam(s"replacement$num", "").returns("")
      requestParams.getParam(s"replaceallbutfirst$num", "").returns("")
    })

    val expected = SomeRandom.string
    correctorMock.correct(input, createNewLines = true, removeNewLines = true, addAsterisks = false, List()).returns(expected)

    val actual = contentExtractor.getContent(requestParams)

    actual must_== (input, expected)

  }
}
