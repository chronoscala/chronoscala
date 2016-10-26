package jp.ne.opt.chronoscala

trait Tag

object Tag {
  /**
   * Type A with Chronoscala tag
   */
  type CS[A] = A with ({ type CsTag = Tag })
}
