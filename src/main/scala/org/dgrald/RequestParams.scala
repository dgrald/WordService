package org.dgrald

import org.scalatra.Params
import org.scalatra.servlet.FileItem
import org.scalatra.util.MultiMapHeadView

/**
  * Created by dylangrald on 9/24/16.
  */
abstract class RequestParams {
  def getFileParam(name: String): Option[Array[Byte]]
  def getParam(name: String, defaultTo: String): String
  def getParam(name: String): Option[String]
}

object RequestParams {
  def apply(params: Params, fileParams: MultiMapHeadView[String, FileItem]): RequestParams = new RequestParamsImplementation(params, fileParams)
}

private class RequestParamsImplementation(params: Params, fileParams: MultiMapHeadView[String, FileItem]) extends RequestParams {
  override def getFileParam(name: String): Option[Array[Byte]] = {
    fileParams.get(name) match {
      case Some(file) => Some(file.get())
      case _ => None
    }
  }

  override def getParam(name: String, defaultTo: String): String = {
    params.getOrElse(name, defaultTo)
  }

  override def getParam(name: String): Option[String] = {
    params.get(name)
  }
}
