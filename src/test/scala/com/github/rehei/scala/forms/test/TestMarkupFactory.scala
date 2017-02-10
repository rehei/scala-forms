package com.github.rehei.scala.forms.test

import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.binding.TextField
import com.github.rehei.scala.forms.binding.AbstractBinding
import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.binding.InlineBinding

sealed abstract class MyFormObject

case class MyForm(sub: MyFormObject) extends MyFormObject
case class MySection(sub: MyFormObject) extends MyFormObject
case class MyTextbox() extends MyFormObject
case class MyInlineFrame(sub: MyFormObject, addButton: MyFormObject) extends MyFormObject
case class MyInlineElement(sub: MyFormObject, removeButton: MyFormObject) extends MyFormObject
case class MyInsertButton(insertFunc: () => MyFormObject) extends MyFormObject
case class MyRemoveButton(removeFunc: () => Unit) extends MyFormObject

case class MySub(subs: MyFormObject*) extends MyFormObject

class TestMarkupFactory extends MarkupFactory[MyFormObject] {

  override def renderForm(model: AnyRef, validator: Validator, sub: MyFormObject, callback: () => Unit, isRootForm: Boolean) = {
    MyForm(sub)
  }

  override def renderSection(sub: MyFormObject) = {
    MySection(sub)
  }

  def renderBinding(context: Bindable, model: AnyRef, binding: AbstractBinding[_]) = {
    MyTextbox()
  }

  def getInlineMarkupFactory() = {
    new TestInlineMarkupFactory()
  }

  def reduce(in: Iterable[MyFormObject]) = {
    MySub(in.toSeq: _*)
  }

}