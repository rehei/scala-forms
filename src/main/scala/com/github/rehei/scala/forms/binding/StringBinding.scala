package com.github.rehei.scala.forms.binding

import scala.xml.NodeSeq
import com.github.rehei.scala.forms.Field
import com.github.rehei.scala.forms.markup.MarkupFactory

class StringBinding extends AbstractBinding {

  def bind[T](context: Field, model: AnyRef, markupFactory: MarkupFactory[T]) = {
    markupFactory.renderTextbox("bla")
  }

}