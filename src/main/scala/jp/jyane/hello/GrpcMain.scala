package jp.jyane.hello

import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import jp.jyane.hello.proto.GreeterGrpc
import jp.jyane.hello.service.GreeterService

import scala.concurrent.ExecutionContext

object GrpcMain {
  def main(args: Array[String]): Unit = {
    val server = ServerBuilder
      .forPort(11111)
      .addService(ProtoReflectionService.newInstance())
      .addService(GreeterGrpc.bindService(new GreeterService, ExecutionContext.global))
      .build()

    server.start()
    server.awaitTermination()

    sys.addShutdownHook {
      server.shutdown()
    }
  }
}
