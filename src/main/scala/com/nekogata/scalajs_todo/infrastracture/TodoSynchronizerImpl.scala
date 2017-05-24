package com.nekogata.scalajs_todo.infrastracture

import com.nekogata.scalajs_todo.domain.{Todo, TodoSynchronizer}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.Random

class TodoSynchronizerImpl extends TodoSynchronizer{
  val repository = new TodoRepositoryImpl

 // 10回に一回くらいの確立で失敗する非同期リクエストをエミュレーション
 override protected def syncRequest(todo: Todo): Future[Unit] = {
    import scala.scalajs.js.timers._
    val p = Promise[Unit]()

    setTimeout(1000) {
      val r = new Random

      if ( r.nextInt(10) == 1 ) {
        p.failure(new RuntimeException("request failed"))
      } else {
        p.success()
      }
    }

    return p.future
  }
}


