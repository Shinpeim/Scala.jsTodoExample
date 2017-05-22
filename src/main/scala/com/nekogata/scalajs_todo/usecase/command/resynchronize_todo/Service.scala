package com.nekogata.scalajs_todo.usecase.command.resynchronize_todo

import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.js_bridge.UpdateTodoRequestFailed

import scala.concurrent.ExecutionContext.Implicits.global

trait Service {
  protected val repository: TodoRepository

  def execute(id: Int) = {
    repository.find(id).foreach {todo =>
      for {
        isSucceeded <- repository.sync(todo)
      } {
        if (isSucceeded) {
          repository.store(todo.synchronized)
        } else {
          UpdateTodoRequestFailed.fire()
        }
      }
    }
  }
}
