package com.nekogata.scalajs_todo.js_bridge

import com.nekogata.scalajs_todo.infrastracture.TodoRepositoryImpl
import com.nekogata.scalajs_todo.usecase.command.make_todo_done.{Command, ServiceImpl}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("MakeTodoDoneCommand")
class MakeTodoDoneCommand extends Command{
  override val repository = new TodoRepositoryImpl
  override val service = new ServiceImpl

  @JSExport
  override def execute(id: Int) = super.execute(id)
}
