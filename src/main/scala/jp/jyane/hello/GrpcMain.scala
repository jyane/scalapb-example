package jp.jyane.hello

import io.grpc.ServerBuilder
import io.grpc.health.v1.HealthCheckResponse.ServingStatus
import io.grpc.protobuf.services.ProtoReflectionService
import io.grpc.services.HealthStatusManager
import jp.jyane.hello.proto.GreeterGrpc
import jp.jyane.hello.service.GreeterService

import scala.concurrent.ExecutionContext

object GrpcMain {
  private[this] val healthStatusManager = new HealthStatusManager

  healthStatusManager.setStatus("Greeter", ServingStatus.NOT_SERVING)
  healthStatusManager.setStatus("", ServingStatus.SERVING)

  def main(args: Array[String]): Unit = {
    val server = ServerBuilder
      .forPort(11111)
      .addService(healthStatusManager.getHealthService)
      .addService(GreeterGrpc.bindService(new GreeterService, ExecutionContext.global))
      .addService(ProtoReflectionService.newInstance())
      .build()

    server.start()
    server.awaitTermination()


    sys.addShutdownHook {
      server.shutdown()
    }
  }
}
