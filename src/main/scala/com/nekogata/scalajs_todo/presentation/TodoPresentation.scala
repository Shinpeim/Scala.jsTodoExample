package com.nekogata.scalajs_todo.presentation

import com.nekogata.scalajs_todo.domain.Todo
import org.threeten.bp.format.DateTimeFormatter

import scala.scalajs.js

class TodoPresentation(t: Todo) {
  def asJs():js.Dictionary[Any] = js.Dictionary(
    "id" -> t.id,
    "body" -> t.body,
    "dueDate" -> t.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    "isOverdue" -> t.isOverdue,
    "craetedAT" -> t.createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    "done" -> t.done,
    "synchronizing" -> t.synchronizing
  )
}


