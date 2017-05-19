package com.nekogata.scalajs_todo.domain

import org.threeten.bp.{LocalDate, LocalDateTime}

case class Todo(body: String, dueDate:LocalDate , createdAt: LocalDateTime, done: Boolean){
  def makeDone: Todo = copy(done = true)

  def isOverdue: Boolean = {
    val now = LocalDate.now
    now.isEqual(dueDate) || now.isAfter(dueDate)
  }
}
