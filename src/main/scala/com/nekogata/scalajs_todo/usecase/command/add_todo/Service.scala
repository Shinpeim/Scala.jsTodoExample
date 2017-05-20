package com.nekogata.scalajs_todo.usecase.command.add_todo

import com.nekogata.scalajs_todo.domain.TodoRepository

trait Service {
  protected val repository: TodoRepository

  def execute(command: Command) = {
    if ( command.isExecutable ) {
      repository.store(command.buildTodo)
    }
  }
}
