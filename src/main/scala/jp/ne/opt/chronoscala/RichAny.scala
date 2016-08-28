package jp.ne.opt.chronoscala

import jp.ne.opt.chronoscala.Tag.CS

class RichAny[A](val underlying: A) extends AnyVal {
  def cs: CS[A] = underlying.asInstanceOf[CS[A]]
}
