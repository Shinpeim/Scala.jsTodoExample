package com.nekogata.scalajs_todo.js_bridge

import com.nekogata.scalajs_todo.infrastracture.TodoRepositoryImpl
import com.nekogata.scalajs_todo.usecase.query.todo.Query

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("TodoQuery")
class TodoQuery extends Query{
  protected val repository = new TodoRepositoryImpl

  @JSExport
  override def all() = super.all()
}
