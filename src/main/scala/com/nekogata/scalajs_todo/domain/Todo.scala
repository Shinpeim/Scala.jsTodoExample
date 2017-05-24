package com.nekogata.scalajs_todo.domain

import org.threeten.bp.{LocalDate, LocalDateTime}

case class Todo(
  id: Int,
  body: String,
  dueDate:LocalDate,
  createdAt: LocalDateTime,
  done: Boolean,
  synchronizingStatus: SynchronizingStatus){

  def makeDone: Todo = copy(done = true, synchronizingStatus = UpdatedLocally)

  def startSync = copy(synchronizingStatus = Synchronizing)

  def finishSync = copy(synchronizingStatus = Synchronized)

  def isSynchronizing = synchronizingStatus == Synchronizing

  def isSynchronizeNeeded = (
    synchronizingStatus == CreatedLocally ||
    synchronizingStatus == UpdatedLocally
  )

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
    synchronizingStatus = CreatedLocally
  )
}
