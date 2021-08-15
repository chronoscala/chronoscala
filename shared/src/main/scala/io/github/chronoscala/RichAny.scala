package io.github.chronoscala

import Tag.CS

class RichAny[A](val underlying: A) extends AnyVal {
  def cs: CS[A] = underlying.asInstanceOf[CS[A]]
}
