package com.nekogata.scalajs_todo

import scala.scalajs.js.annotation._

@JSExportAll
@JSExportTopLevel("Nyan")
class Nyan{
  private val privateVal = "mew"

  def mew() = privateVal
}

