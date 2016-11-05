package com.movie.recommendation.actor

import akka.actor.Actor
import akka.event.Logging
import com.movie.recommendation.model.Rating
import com.movie.recommendation.rest.RatingJSONProtocol
import spray.routing.RequestContext

/**
  * RatingServiceActor<br>
  *
  * Rating service will fetch movie recommendations from JDG cache.
  *
  * @author Zak Hassan <zak.hassan@redhat.com>
  */

object RatingService {
  case class GetMovieRatings()
}
class RatingServiceActor(requestContext: RequestContext) extends Actor {
  import RatingService._

  implicit val system = context.system

  val log = Logging(system, getClass)

  override def receive: Receive = {
    case GetMovieRatings => {
      import RatingJSONProtocol._
      import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
      // TODO: Replace stubs with JDG query code to pull ratings.
      log.info("Get movie ratings")
      requestContext.complete(List( new Rating(1, 1, 1.0),
                                    new Rating(2, 2, 2.0),
                                    new Rating(3, 3, 3.0),
                                    new Rating(4, 4, 4.0),
                                    new Rating(5, 5, 5.0)))
      context.stop(self)
    }
    case _ => {

      log.info("Unsupported method")
    }
  }
}

