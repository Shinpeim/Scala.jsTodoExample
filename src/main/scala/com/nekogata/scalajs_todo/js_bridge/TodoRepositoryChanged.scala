package com.nekogata.scalajs_todo.js_bridge

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("TodoRepositoryChanged")
object TodoRepositoryChanged extends Event{
  @JSExport
  override def subscribe(f: js.Function) = super.subscribe(f)
}
