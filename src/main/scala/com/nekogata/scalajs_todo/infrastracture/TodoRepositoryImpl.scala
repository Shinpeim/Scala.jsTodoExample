package com.nekogata.scalajs_todo.infrastracture

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository}
import com.nekogata.scalajs_todo.js_bridge.TodoRepositoryChanged

class TodoRepositoryImpl extends TodoRepository{
  def nextId() = {
    TodoRepositoryImpl.nextId += 1
    TodoRepositoryImpl.nextId
  }

  def find(id: Int) = TodoRepositoryImpl.inMemoryRecords.find(_.id == id)

  def store(todo: Todo) = {
    val companion = TodoRepositoryImpl
    find(todo.id) match {
      case Some(t) => {
        companion.inMemoryRecords = companion.inMemoryRecords.filterNot(_.id == t.id) ++ Seq(todo)
      }
      case None => {
        companion.inMemoryRecords = companion.inMemoryRecords ++ Seq(todo)
      }
    }
    TodoRepositoryChanged.fire()
  }

  def all = TodoRepositoryImpl.inMemoryRecords
}

object TodoRepositoryImpl {
  private var nextId:Int = 0;
  private var inMemoryRecords: Seq[Todo] = Seq.empty
}
