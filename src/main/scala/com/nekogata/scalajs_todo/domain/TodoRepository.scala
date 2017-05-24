package com.nekogata.scalajs_todo.domain

import scala.concurrent.Future

trait TodoRepository {
  def nextId(): Int

  def find(id: Int): Option[Todo]

  def store(todo: Todo): Unit

  def destroy(todo: Todo): Unit

  def all(): Seq[Todo]
}
