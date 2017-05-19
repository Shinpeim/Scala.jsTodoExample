package com.nekogata.scalajs_todo.service

import com.nekogata.scalajs_todo.command.AddTodoCommand
import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.presentation.TodoPresentation

import scala.scalajs.js

trait TodoService{
  protected val repository: TodoRepository

  val addCommand: AddTodoCommand

  def executeAddCommand():Unit = {
    if ( addCommand.isExecutable ) {
      repository.store(addCommand.buildTodo)
    }
  }

  private def fetchTodoPresentations() =
    repository.all().map(t => new TodoPresentation(t).asJs())

  def fetchTodos() = {
    val todos = fetchTodoPresentations()
    js.Array(todos: _*)
  }
}
