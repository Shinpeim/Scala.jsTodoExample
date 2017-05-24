package com.nekogata.scalajs_todo.usecase.command.resynchronize_todo

import com.nekogata.scalajs_todo.domain.{TodoRepository, TodoSynchronizer}
import com.nekogata.scalajs_todo.js_bridge.SynchronizeFailed

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Failure

trait Service {
  protected val repository: TodoRepository
  protected val synchronizer: TodoSynchronizer

  def execute(id: Int) = {
    repository.find(id).foreach {todo =>
      synchronizer.sync(todo).onComplete {
        case Failure(_) => SynchronizeFailed.fire()
        case _ => ()
      }
    }
  }
}
