package com.github.rehei.scala.forms.test

import com.github.rehei.scala.forms.markup.AbstractInlineMarkupFactory

class TestInlineMarkupFactory extends AbstractInlineMarkupFactory[MyFormObject] {

  override def renderInlineFrame(sub: MyFormObject, insertFunc: () => MyFormObject) = {
    MyInlineFrame(sub, MyInsertButton(insertFunc))
  }

  def renderInlineElement(sub: MyFormObject, removeFunc: () => Unit) = {
    MyInlineElement(sub, MyRemoveButton(removeFunc))
  }

}