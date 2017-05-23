package com.nekogata.scalajs_todo.usecase.command.add_todo

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeParseException

trait Command {
  protected val addTodoService: Service

  private var _todoInput = ""
  private var _dueDateInput = ""

  def todoInput = _todoInput
  def todoInput_=(v: String) {
    _todoInput = v
  }

  def dueDateInput = _dueDateInput
  def dueDateInput_=(v: String) {
    _dueDateInput = v
  }

  def isTodoInputValid = _todoInput != ""
  def isDueDateInputValid = {
    val ret:Boolean = try {
      dueDate
      true
    } catch {
      case _:DateTimeParseException => false
    }
    ret
  }

  def isExecutable = isTodoInputValid && isDueDateInputValid

  def dueDate = {
    LocalDate.parse(dueDateInput)
  }


  def execute() = addTodoService.execute(this)
}
