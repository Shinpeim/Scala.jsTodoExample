package com.nekogata.scalajs_todo.usecase.command.add_todo

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository, TodoSynchronizer}

trait Service {
  protected val repository: TodoRepository
  protected val synchronizer: TodoSynchronizer

  def execute(command: Command): Unit = {
    if ( ! command.isExecutable ) {
      return
    }

    val todo = Todo.open(
      id = repository.nextId(),
      body = command.todoInput,
      dueDate = command.dueDate
    )

    repository.store(todo)
    synchronizer.sync(todo)
  }
}
