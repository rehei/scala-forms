package com.github.rehei.scala.forms.binding

import scala.xml.NodeSeq
import com.github.rehei.scala.forms.Field
import com.github.rehei.scala.forms.markup.MarkupFactory

abstract class AbstractBinding {

  def bind(context: Field, model: AnyRef, markupFactory: MarkupFactory): NodeSeq

}