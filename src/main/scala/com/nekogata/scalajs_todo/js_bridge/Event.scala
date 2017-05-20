package com.nekogata.scalajs_todo.js_bridge

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

trait Event{
  var subscriptions: Set[Subscription] = Set.empty

  def subscribe(f: js.Function) = {
    val s = new Subscription(this, f)
    subscriptions = subscriptions + s
  }

  def fire() = {
    subscriptions.foreach(_.f.call((): Unit))
  }

  def unsubscribe(s: Subscription): Unit ={
    subscriptions = subscriptions - s
  }
}

class Subscription(dispatcher: Event, val f: js.Function){
  @JSExport
  def unsubscribe() = {
    dispatcher.unsubscribe(this)
  }
}
