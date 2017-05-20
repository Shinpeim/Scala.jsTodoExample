package com.nekogata.scalajs_todo.usecase.command.add_todo

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository}
import org.threeten.bp.LocalDateTime

trait Service {
  protected val repository: TodoRepository

  def execute(command: Command) = {
    if ( command.isExecutable ) {
      repository.store(buildTodo(command))
    }
  }

  private def buildTodo(c: Command) =
    Todo(repository.nextId(), c.todoInput, c.dueDate, LocalDateTime.now, false)
}
