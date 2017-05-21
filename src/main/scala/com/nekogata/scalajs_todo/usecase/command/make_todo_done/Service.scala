package com.nekogata.scalajs_todo.usecase.command.make_todo_done

import com.nekogata.scalajs_todo.domain.TodoRepository

trait Service {
  protected val repository: TodoRepository

  def execute(id: Int) = {
    repository.find(id).foreach {t =>
      val doneTodo = t.makeDone
      repository.store(doneTodo)
    }
  }
}
