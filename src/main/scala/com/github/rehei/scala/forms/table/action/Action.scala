package com.github.rehei.scala.forms.table.action

import scala.xml.NodeSeq
import scala.xml.Text
import com.github.rehei.scala.forms.binding.Decoratable
import com.github.rehei.scala.forms.decorators.LabelDecorator

abstract class Action[T <: Decoratable[_]] extends Decoratable[T] {

  def label(label: String) = {
    this.decorateWith(LabelDecorator(label))
  }
  
  def label() = {
    this.getDecorator[LabelDecorator].map(_.label).getOrElse("Unnamed action")
  }

}