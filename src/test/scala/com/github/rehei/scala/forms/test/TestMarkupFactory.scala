package com.github.rehei.scala.forms.test

import com.github.rehei.scala.forms.SubRendering
import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.model.MyFormObject
import com.github.rehei.scala.forms.model.MyForm
import com.github.rehei.scala.forms.model.MySection
import com.github.rehei.scala.forms.model.MyTextbox

class TestMarkupFactory extends MarkupFactory[MyFormObject] {
  
  def renderForm(subRendering: SubRendering[MyFormObject]) = {
    MyForm(subRendering.render())
  }

  def renderSection(subRendering: SubRendering[MyFormObject]) = {
    MySection(subRendering.render())
  }

  def renderTextbox() = {
    MyTextbox()
  }

}