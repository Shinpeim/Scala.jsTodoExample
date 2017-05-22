package com.nekogata.scalajs_todo.usecase.command.resynchronize_todo

import com.nekogata.scalajs_todo.domain.TodoRepository

trait Command {
  val repository: TodoRepository
  val service: Service

  def execute(id: Int) = service.execute(id)
}
