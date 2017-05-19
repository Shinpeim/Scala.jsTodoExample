package com.nekogata.scalajs_todo.domain

trait TodoRepository {
  def store(todo: Todo):Unit

  def all(): Seq[Todo]
}
