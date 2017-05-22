package com.nekogata.scalajs_todo.js_bridge

import com.nekogata.scalajs_todo.infrastracture.TodoRepositoryImpl
import com.nekogata.scalajs_todo.usecase.command.resynchronize_todo.{Command, ServiceImpl}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("ResynchronizeTodoCommand")
class ResynchronizeTodoCommand extends Command{
  override val repository = new TodoRepositoryImpl
  override val service = new ServiceImpl

  @JSExport
  override def execute(id: Int) = super.execute(id)
}
