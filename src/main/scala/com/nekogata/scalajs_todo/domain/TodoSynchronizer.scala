package com.nekogata.scalajs_todo.domain

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
      case Success(()) => repository.store(todo.finishSync)
      case Failure(_) => repository.store(oldTodo)
    }

    result
  }

  protected def syncRequest(todo: Todo): Future[Unit]
}
