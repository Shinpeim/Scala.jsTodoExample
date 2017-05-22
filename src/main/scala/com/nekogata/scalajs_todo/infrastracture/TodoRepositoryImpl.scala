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

  def storeThenSync(todo: Todo): Future[Boolean] = {
    val f = find(todo.id) match {
      case Some(_) => updateThenSync(todo)
      case None => addThenSync(todo)
    }
    TodoRepositoryChanged.fire()

    f
  }

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

  def sync(todo: Todo): Future[Boolean] = {
    store(todo.synchronize)
    requestAddToBackend(todo)
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

  private def addThenSync(todo: Todo): Future[Boolean] = {
    companion.inMemoryRecords = companion.inMemoryRecords ++ Seq(todo)
    requestAddToBackend(todo)
  }

  private def requestAddToBackend(todo: Todo):Future[Boolean] = {
    maybeFail
  }

  private def updateThenSync(todo: Todo): Future[Boolean] = {
    val oldTodoOpt = companion.inMemoryRecords.find(_.id == todo.id)
    if (oldTodoOpt.isEmpty) {
      return Future.failed(new RuntimeException("no such record"))
    }
    val oldTodo = oldTodoOpt.get

    remove(todo)
    add(todo)
    requestUpdateToBackend(todo)
  }

  private def requestUpdateToBackend(todo:Todo): Future[Boolean] = {
    maybeFail
  }

  private def remove(todo: Todo): Unit = {
    companion.inMemoryRecords = companion.inMemoryRecords.filterNot(_.id == todo.id)
  }

  // 10回に一回くらいの確立で失敗する非同期リクエスト
  private def maybeFail: Future[Boolean] = {
    import scala.scalajs.js.timers._
    val p = Promise[Boolean]()

    setTimeout(1000) {
      val r = new Random

      if ( r.nextInt(10) == 1 ) {
        p.success(false)
      } else {
        p.success(true)
      }
    }

    return p.future
  }
}

object TodoRepositoryImpl {
  private var nextId:Int = 0
  private var inMemoryRecords: Seq[Todo] = Seq.empty
}
