package com.github.rehei.scala.forms.markup

abstract class AbstractInlineMarkupFactory[T] {

  def renderInlineFrame(sub: T, addButton: T): T

  def renderInlineElement(sub: T, removeButton: T): T

  def renderInsertButton(myInsertFunc: () => T): T

  def renderRemoveButton(myRemoveFunc: () => Unit): T

}