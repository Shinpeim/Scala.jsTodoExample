package com.nekogata.scalajs_todo.usecase.command.add_todo

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository}
import com.nekogata.scalajs_todo.js_bridge.AddTodoRequestFailed
import org.threeten.bp.LocalDateTime

import scala.concurrent.ExecutionContext.Implicits.global

trait Service {
  protected val repository: TodoRepository

  def execute(command: Command) = {
    val todo = buildTodo(command)

    if ( command.isExecutable ) {
      for {
        isSucceeded <- repository.storeThenSync(todo)
      } {
        println(isSucceeded)
        if ( isSucceeded ) {
          repository.store(todo.synchronized)
        } else {
          repository.destroy(todo)
          AddTodoRequestFailed.fire()
        }
      }
    }
  }

  private def buildTodo(c: Command) =
    Todo(
      id = repository.nextId(),
      body = c.todoInput,
      dueDate = c.dueDate,
      createdAt = LocalDateTime.now,
      done = false,
      synchronizing = true
    )
}
