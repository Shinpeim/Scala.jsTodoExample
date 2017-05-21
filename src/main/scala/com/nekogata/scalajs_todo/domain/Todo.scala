package com.nekogata.scalajs_todo.domain

import org.threeten.bp.{LocalDate, LocalDateTime}

case class Todo(
  id: Int,
  body: String,
  dueDate:LocalDate,
  createdAt: LocalDateTime,
  done: Boolean,
  synchronizing: Boolean){

  def makeDone: Todo = copy(done = true, synchronizing = true)

  def synchronized = copy(synchronizing = false)

  def isOverdue: Boolean = {
    if ( done ) {
      return false
    }
    val now = LocalDate.now
    now.isEqual(dueDate) || now.isAfter(dueDate)
  }
}
