package jp.jyane.hello.service

import jp.jyane.hello.proto.GreeterGrpc.Greeter
import jp.jyane.hello.proto.{HelloReply, HelloRequest}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class GreeterService extends Greeter {
  override def sayHello(request: HelloRequest): Future[HelloReply] =
    Future {
      println(request.name)
      HelloReply(message = s"hello ${request.name}")
    }
}
