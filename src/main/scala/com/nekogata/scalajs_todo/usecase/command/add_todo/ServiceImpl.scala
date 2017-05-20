package com.nekogata.scalajs_todo.usecase.command.add_todo

import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.infrastracture.InMemoryTodoRepository

class ServiceImpl extends Service {
  protected val repository: TodoRepository = new InMemoryTodoRepository

  override def execute(command: Command): Unit = super.execute(command)

}
