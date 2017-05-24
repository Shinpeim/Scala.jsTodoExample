package com.nekogata.scalajs_todo.usecase.command.resynchronize_todo

import com.nekogata.scalajs_todo.infrastracture.{TodoRepositoryImpl, TodoSynchronizerImpl}

class ServiceImpl extends Service {
  protected val repository = new TodoRepositoryImpl
  protected val synchronizer = new TodoSynchronizerImpl
}
