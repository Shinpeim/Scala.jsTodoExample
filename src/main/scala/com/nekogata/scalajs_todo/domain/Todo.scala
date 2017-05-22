package com.nekogata.scalajs_todo.domain

import org.threeten.bp.{LocalDate, LocalDateTime}

case class Todo(
  id: Int,
  body: String,
  dueDate:LocalDate,
  createdAt: LocalDateTime,
  done: Boolean,
  synchronizingStatus: SynchronizingStatus){

  def makeDone: Todo = copy(done = true, synchronizingStatus = Synchronizing)

  def synchronized = copy(synchronizingStatus = Synchronized)

  def synchronizeFailed = copy(synchronizingStatus = SynchronizingFailed)

  def synchronize = copy(synchronizingStatus = Synchronizing)

  def isOverdue: Boolean = {
    if ( done ) {
      return false
    }
    val now = LocalDate.now
    now.isEqual(dueDate) || now.isAfter(dueDate)
  }
}

object Todo{
  def open(id: Int, body: String, dueDate:LocalDate) = Todo(
    id,
    body,
    dueDate,
    LocalDateTime.now,
    done = false,
    synchronizingStatus = Synchronizing
  )
}
