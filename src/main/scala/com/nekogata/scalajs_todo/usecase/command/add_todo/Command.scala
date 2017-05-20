package com.nekogata.scalajs_todo.usecase.command.add_todo

import com.nekogata.scalajs_todo.domain.Todo
import org.threeten.bp.{LocalDate, LocalDateTime}

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
      case _:Throwable => false
    }
    ret
  }

  def execute() = addTodoService.execute(this)

  def isExecutable = isTodoInputValid && isDueDateInputValid

  def buildTodo = Todo(todoInput, dueDate, LocalDateTime.now, false)

  private def dueDate = {
    LocalDate.parse(dueDateInput)
  }
}
