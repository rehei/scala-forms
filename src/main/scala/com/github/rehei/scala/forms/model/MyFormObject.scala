package com.github.rehei.scala.forms.model

import com.github.rehei.scala.forms.Section

sealed abstract class MyFormObject

case class MyForm(sections: Seq[MyFormObject]) extends MyFormObject
case class MySection(fields: Seq[MyFormObject]) extends MyFormObject
case class MyTextbox() extends MyFormObject
case class MyInlineBinding(form: Seq[MyFormObject]) extends MyFormObject