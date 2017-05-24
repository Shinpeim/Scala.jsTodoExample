package com.nekogata.scalajs_todo.usecase.command.make_todo_done

import com.nekogata.scalajs_todo.domain.{TodoRepository, TodoSynchronizer}

trait Service {
  protected val repository: TodoRepository
  protected val synchronizer: TodoSynchronizer

  def execute(id: Int) = {
    repository.find(id).foreach {oldTodo =>
      val doneTodo = oldTodo.makeDone
      repository.store(doneTodo)
      synchronizer.sync(doneTodo)
    }
  }
}
