package com.nekogata.scalajs_todo.js_bridge

import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.infrastracture.InMemoryTodoRepository
import com.nekogata.scalajs_todo.usecase.command.make_todo_done.Command

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("MakeTodoDoneCommand")
class MakeTodoDoneCommand extends Command{
  override val repository: TodoRepository = new InMemoryTodoRepository

  @JSExport
  override def execute(id: Int) = super.execute(id)
}
