package com.nekogata.scalajs_todo.command

import com.nekogata.scalajs_todo.domain.Todo
import org.threeten.bp.{LocalDate, LocalDateTime}

import scala.scalajs.js.annotation.JSExport

class AddTodoCommand {
  private var _todoInput = ""
  private var _dueDateInput = ""

  @JSExport
  def todoInput = _todoInput
  @JSExport
  def todoInput_=(v: String) {
    _todoInput = v
  }

  @JSExport
  def dueDateInput = _dueDateInput
  @JSExport
  def dueDateInput_=(v: String) {
    _dueDateInput = v
  }

  @JSExport
  def isTodoInputValid = _todoInput != ""
  @JSExport
  def isDueDateInputValid = {
    val ret:Boolean = try {
      dueDate
      true
    } catch {
      case _:Throwable => false
    }
    ret
  }

  def isExecutable = isTodoInputValid && isDueDateInputValid

  def buildTodo = Todo(todoInput, dueDate, LocalDateTime.now, false)

  private def dueDate = {
    LocalDate.parse(dueDateInput)
  }
}
