package com.nekogata.scalajs_todo.usecase.command.make_todo_done

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository}

trait Command {
  val repository: TodoRepository

  def execute(id: Int) = {
    val todo = repository.find(id).foreach {t =>
      val doneTodo = t.makeDone
      repository.store(doneTodo)
    }
  }
}
