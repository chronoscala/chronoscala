package jp.ne.opt.chronoscala

import jp.ne.opt.chronoscala.Tag.CS

import scala.language.implicitConversions

trait NamespacedImplicits extends Implicits with NamespacedIntImplicits

trait NamespacedIntImplicits extends IntImplicits {
  override def richInt(n: Int): RichInt = new RichInt(n)
  implicit def richIntCs(n: Int): RichAny[Int] = new RichAny(n)
  implicit def richCsInt(n: CS[Int]): RichInt = new RichInt(n)
}
