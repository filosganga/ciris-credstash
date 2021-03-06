package ciris

import ciris.api._

import scala.collection.mutable.ListBuffer
import scala.sys.process._

package object credstash {
  val CredstashKeyType: ConfigKeyType[String] =
    ConfigKeyType("credstash key")

  def credstashSource(region: String): ConfigSource[Id, String, String] =
    ConfigSource(CredstashKeyType) { key =>
      val outputBuffer =
        ListBuffer.empty[String]

      val exitValue =
        s"credstash get -r $region $key"
          .run(ProcessLogger(outputBuffer append _))
          .exitValue()

      val result =
        outputBuffer.mkString("\n").trim

      if (exitValue == 0) Right(result)
      else Left(ConfigError(result))
    }

  def credstash[Value](region: String)(key: String)(
      implicit decoder: ConfigDecoder[String, Value]
  ): ConfigEntry[Id, String, String, Value] = {
    credstashSource(region)
      .read(key)
      .decodeValue[Value]
  }

  def credstashF[F[_]: Sync, Value](region: String)(key: String)(
      implicit decoder: ConfigDecoder[String, Value]
  ): ConfigEntry[F, String, String, Value] = {
    credstashSource(region)
      .suspendF[F]
      .read(key)
      .decodeValue[Value]
  }
}
