package com.github.rehei.scala.forms.markup

import com.github.rehei.scala.forms.Form
import com.github.rehei.scala.forms.Section
import scala.xml.NodeSeq

abstract class MarkupFactory[T] {

  def renderForm(sub: T): T

  def renderSection(sub: T): T

  def renderTextbox(label: String): T

  def renderInlineFrame(sub: T, addButton: T): T

  def renderInlineElement(sub: T, removeButton: T): T

  def renderInsertButton(myInsertFunc: () => T): T
  
  def renderRemoveButton(myRemoveFunc: () => Unit): T
  
  def reduce(in: Iterable[T]): T

}