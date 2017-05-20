package com.nekogata.scalajs_todo.js_bridge

import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.infrastracture.InMemoryTodoRepository
import com.nekogata.scalajs_todo.usecase.query.todo.Query

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("TodoQuery")
class TodoQuery extends Query{
  protected val repository: TodoRepository = new InMemoryTodoRepository

  @JSExport
  override def all() = super.all()
}
