package com.nekogata.scalajs_todo.usecase.command.resynchronize_todo

import com.nekogata.scalajs_todo.domain.{TodoRepository, TodoSynchronizer}

trait Service {
  protected val repository: TodoRepository
  protected val synchronizer: TodoSynchronizer

  def execute(id: Int) = {
    repository.find(id).foreach {todo =>
      synchronizer.sync(todo)
    }
  }
}
