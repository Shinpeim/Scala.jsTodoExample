package com.nekogata.scalajs_todo.usecase.command.add_todo

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository}
import com.nekogata.scalajs_todo.js_bridge.AddTodoRequestFailed
import org.threeten.bp.LocalDateTime

import scala.concurrent.ExecutionContext.Implicits.global

trait Service {
  protected val repository: TodoRepository

  def execute(command: Command) = {
    if ( command.isExecutable ) {
      val todo = Todo.open(
        id = repository.nextId(),
        body = command.todoInput,
        dueDate = command.dueDate
      )

      for {
        isSucceeded <- repository.storeThenSync(todo)
      } {
        if ( isSucceeded ) {
          repository.store(todo.synchronized)
        } else {
          repository.store(todo.synchronizeFailed)
          AddTodoRequestFailed.fire()
        }
      }
    }
  }
}
