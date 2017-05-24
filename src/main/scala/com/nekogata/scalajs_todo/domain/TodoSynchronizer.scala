package com.nekogata.scalajs_todo.domain

import com.nekogata.scalajs_todo.js_bridge.SynchronizeFailed

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

trait TodoSynchronizer {
  val repository: TodoRepository

  def sync(todo: Todo): Future[Unit] = {
    val oldTodo = todo

    repository.store(todo.startSync)

    val result = syncRequest(todo)

    result.onComplete {
      case Success(()) =>
        repository.store(todo.finishSync)
      case Failure(_) =>
        SynchronizeFailed.fire()
        repository.store(oldTodo)
    }

    result
  }

  protected def syncRequest(todo: Todo): Future[Unit]
}
