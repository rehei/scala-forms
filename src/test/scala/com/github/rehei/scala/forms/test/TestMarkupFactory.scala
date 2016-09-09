package com.github.rehei.scala.forms.test

import com.github.rehei.scala.forms.markup.MarkupFactory

sealed abstract class MyFormObject

case class MyForm(sub: MyFormObject) extends MyFormObject
case class MySection(sub: MyFormObject) extends MyFormObject
case class MyTextbox() extends MyFormObject
case class MyInlineFrame(sub: MyFormObject, addButton: MyFormObject) extends MyFormObject
case class MyInlineElement(sub: MyFormObject, removeButton: MyFormObject) extends MyFormObject
case class MyInsertButton() extends MyFormObject
case class MyRemoveButton() extends MyFormObject

case class MySub(sub: Iterable[MyFormObject]) extends MyFormObject

class TestMarkupFactory extends MarkupFactory[MyFormObject] {

  override def renderForm(sub: MyFormObject) = {
    MyForm(sub)
  }

  override def renderSection(sub: MyFormObject) = {
    MySection(sub)
  }

  override def renderTextbox(label: String) = {
    MyTextbox()
  }

 override  def renderInlineFrame(sub: MyFormObject, addButton: MyFormObject) = {
    MyInlineFrame(sub, addButton)
  }

  def renderInlineElement(sub: MyFormObject, removeButton: MyFormObject) = {
    MyInlineElement(sub, removeButton)
  }

  def renderInsertButton(insertFunc: () => MyFormObject) = {
    MyInsertButton()
  }

  def renderRemoveButton(removeFunc: () => Unit) = {
    MyRemoveButton()
  }

  def reduce(in: Iterable[MyFormObject]) = {
    MySub(in)
  }

}