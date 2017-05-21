package com.nekogata.scalajs_todo.usecase.command.make_todo_done

import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.js_bridge.UpdateTodoRequestFailed

import scala.concurrent.ExecutionContext.Implicits.global

trait Service {
  protected val repository: TodoRepository

  def execute(id: Int) = {
    repository.find(id).foreach {oldTodo =>
      val doneTodo = oldTodo.makeDone

      for {
        isSucceeded <- repository.storeThenSync(doneTodo)
      } {
        if (isSucceeded) {
          repository.store(doneTodo.synchronized)
        } else {
          repository.store(oldTodo)
          UpdateTodoRequestFailed.fire()
        }
      }
    }
  }
}
