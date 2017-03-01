package com.github.rehei.scala.forms.table.action

import com.github.rehei.scala.forms.decorators.InvokeDecorator

case class OnEntity() extends Action[OnEntity] {
  
  def onInvoke(action: () => Unit) = {
    this.decorateWith(InvokeDecorator(action))
  }

  def onInvoke() = {
    this.getDecorator[InvokeDecorator].map(_.action).getOrElse(() => Unit)
  }

  def invoke() = {
    this.onInvoke().apply()
  }
  
}
