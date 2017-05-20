package com.nekogata.scalajs_todo.usecase.command.make_todo_done

import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.infrastracture.InMemoryTodoRepository

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("MakeTodoDoneCommand")
class CommandImpl extends Command{
  override val repository: TodoRepository = new InMemoryTodoRepository

  @JSExport
  override def execute(id: Int) = super.execute(id)
}
