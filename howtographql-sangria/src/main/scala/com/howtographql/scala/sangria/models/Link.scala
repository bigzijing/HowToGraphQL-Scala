package com.howtographql.scala.sangria

import akka.http.scaladsl.model.DateTime
import sangria.validation.Violation
import sangria.execution.deferred.HasId
import sangria.execution.FieldTag

package object models {

  trait Identifiable {
    val id: Int
  }

  object Identifiable {
    implicit def hasId[T <: Identifiable]: HasId[T, Int] = HasId(_.id)
  }

  case class Link(id: Int, url: String, description: String, postedBy: Int, createdAt: DateTime = DateTime.now) extends Identifiable

  case object DateTimeCoerceViolation extends Violation {
    override def errorMessage: String = "Error during parsing DateTime"
  }

  case class User(id: Int, name: String, email: String, password: String, createdAt: DateTime = DateTime.now) extends Identifiable

  case class Vote(id: Int, userId: Int, linkId: Int, createdAt: DateTime = DateTime.now) extends Identifiable

  case class AuthProviderEmail(email: String, password: String)

  case class AuthProviderSignupData(email: AuthProviderEmail)

  case class AuthenticationException(message: String) extends Exception(message)
  case class AuthorizationException(message: String) extends Exception(message)

  case object Authorized extends FieldTag
}

//  Your DIY kit
//  Before you go further, try to implement the changes yourself. I think, at this point, you have the necessary knowledge to add the User and Vote models. I’ll show what to do later in this chapter, but try to implement it yourself first.
//
//  What you have to do:
//
//  Add User class with fields: id, name, email, password and createdAt
//  Add Vote class with fields: id, createdAt, userId, linkId(you don’t have to to define any relations for now)
//  Create database tables for both,
//  Add object types for both,
//  Add fetchers for both,
//  Implement HasId type class,
//  Add fields in main ObjectType which allows for fetching a list of entities like users and votes
//  Please, go ahead with your implementation … I will wait here