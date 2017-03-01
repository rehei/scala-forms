package com.github.rehei.scala.forms.table.action

import com.github.rehei.scala.forms.decorators.ModelInvokeDecorator

case class OnInstance() extends Action[OnInstance] {

  def onInvoke(action: (String) => Unit) = {
    this.decorateWith(ModelInvokeDecorator(action))
  }

  def onInvoke() = {
    this.getDecorator[ModelInvokeDecorator].map(_.action).getOrElse((model: AnyRef) => Unit)
  }

  def invoke(modelID: String) = {
    this.onInvoke().apply(modelID)
  }

}