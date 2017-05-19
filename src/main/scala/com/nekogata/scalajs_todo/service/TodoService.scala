package com.nekogata.scalajs_todo.service

import com.nekogata.scalajs_todo.command.AddTodoCommand

import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel}

@JSExportAll
@JSExportTopLevel("TodoService")
class TodoService {
  val addCommand = new AddTodoCommand()

  def executeAddCommand():Unit = {
    if ( addCommand.isExecutable ) {
      println(addCommand.toTodo)
    }
  }
}
