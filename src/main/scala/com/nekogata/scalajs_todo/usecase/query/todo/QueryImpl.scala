package com.nekogata.scalajs_todo.usecase.query.todo

import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.infrastracture.InMemoryTodoRepository

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("TodoQuery")
class QueryImpl extends Query{
  protected val repository: TodoRepository = new InMemoryTodoRepository

  @JSExport
  override def all() = super.all()
}
