package com.github.rehei.scala.forms.test

import com.github.rehei.scala.forms.markup.AbstractInlineMarkupFactory

class TestInlineMarkupFactory extends AbstractInlineMarkupFactory[MyFormObject] {

  override def renderInlineFrame(sub: MyFormObject, addButton: MyFormObject) = {
    MyInlineFrame(sub, addButton)
  }

  def renderInlineElement(sub: MyFormObject, removeButton: MyFormObject) = {
    MyInlineElement(sub, removeButton)
  }

  def renderInsertButton(insertFunc: () => MyFormObject) = {
    MyInsertButton(insertFunc)
  }

  def renderRemoveButton(removeFunc: () => Unit) = {
    MyRemoveButton(removeFunc)
  }

}