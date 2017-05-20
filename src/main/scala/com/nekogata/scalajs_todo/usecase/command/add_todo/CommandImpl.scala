package com.nekogata.scalajs_todo.usecase.command.add_todo

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("AddTodoCommand")
class CommandImpl extends Command {
  protected val addTodoService = new ServiceImpl

  @JSExport
  override def todoInput = super.todoInput
  @JSExport
  override def todoInput_=(v: String) = super.todoInput_=(v)


  @JSExport
  override def dueDateInput = super.dueDateInput
  @JSExport
  override def dueDateInput_=(v: String) = super.dueDateInput_=(v)

  @JSExport
  override def isTodoInputValid = super.isTodoInputValid
  @JSExport
  override def isDueDateInputValid = super.isDueDateInputValid

  @JSExport
  override def execute() = super.execute()
}
