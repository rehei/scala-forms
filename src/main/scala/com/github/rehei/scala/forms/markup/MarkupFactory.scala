package com.github.rehei.scala.forms.markup

import com.github.rehei.scala.forms.binding.AbstractBinding
import com.github.rehei.scala.forms.Field

abstract class MarkupFactory[T] {

  def renderForm(sub: T, callback: () => Unit): T

  def renderSection(sub: T): T

  def renderBinding(context: Field, model: AnyRef, binding: AbstractBinding[_]): T

  def renderInlineFrame(sub: T, addButton: T): T

  def renderInlineElement(sub: T, removeButton: T): T

  def renderInsertButton(myInsertFunc: () => T): T
  
  def renderRemoveButton(myRemoveFunc: () => Unit): T
  
  def reduce(in: Iterable[T]): T

}