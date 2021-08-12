package com.github.chronoscala

trait Tag

object Tag {

  /**
   * Type A with Chronoscala tag
   */
  type CS[A] = A with ({ type CsTag = Tag })
}
