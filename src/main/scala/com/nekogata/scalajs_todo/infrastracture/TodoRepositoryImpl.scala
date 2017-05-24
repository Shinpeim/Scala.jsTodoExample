package com.nekogata.scalajs_todo.infrastracture

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository}
import com.nekogata.scalajs_todo.js_bridge.TodoRepositoryChanged

import scala.concurrent.{Future, Promise}
import scala.util.Random

import scala.concurrent.ExecutionContext.Implicits.global

class TodoRepositoryImpl extends TodoRepository{
  val companion = TodoRepositoryImpl

  def nextId() = {
    companion.nextId += 1
    companion.nextId
  }

  def find(id: Int) = companion.inMemoryRecords.find(_.id == id)

  def store(todo: Todo): Unit = {
    val f = find(todo.id) match {
      case Some(_) => update(todo)
      case None => add(todo)
    }
    TodoRepositoryChanged.fire()
  }

  def destroy(todo: Todo): Unit = {
    remove(todo)
    TodoRepositoryChanged.fire()
  }

  def all = companion.inMemoryRecords

  private def add(todo: Todo): Unit = {
    companion.inMemoryRecords = companion.inMemoryRecords ++ Seq(todo)
  }

  private def update(todo: Todo): Unit = {
    val oldTodoOpt = companion.inMemoryRecords.find(_.id == todo.id)
    if (oldTodoOpt.isEmpty) {
      throw new RuntimeException("no such record")
    }
    val oldTodo = oldTodoOpt.get
    remove(todo)
    add(todo)
  }

  private def remove(todo: Todo): Unit = {
    companion.inMemoryRecords = companion.inMemoryRecords.filterNot(_.id == todo.id)
  }
}

object TodoRepositoryImpl {
  private var nextId:Int = 0
  private var inMemoryRecords: Seq[Todo] = Seq.empty
}
