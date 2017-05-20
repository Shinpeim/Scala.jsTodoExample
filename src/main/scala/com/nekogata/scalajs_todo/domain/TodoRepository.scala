package com.nekogata.scalajs_todo.domain

trait TodoRepository {
  def nextId(): Int

  def find(id: Int): Option[Todo]

  def store(todo: Todo):Unit

  def all(): Seq[Todo]
}
