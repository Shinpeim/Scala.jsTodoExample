package com.nekogata.scalajs_todo.usecase.command.add_todo

import com.nekogata.scalajs_todo.infrastracture.TodoRepositoryImpl

class ServiceImpl extends Service {
  protected val repository: TodoRepositoryImpl = new TodoRepositoryImpl
}
