package com.github.rehei.scala.forms.markup

abstract class AbstractInlineMarkupFactory[T] {

  def renderInlineFrame(sub: T, insertFunc: () => T): T

  def renderInlineElement(sub: T, removeFunc: () => Unit): T

}