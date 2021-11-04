package concurrency.chapterfour

import scala.concurrent.Future
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global

object IOSupport {
    def readFromFileInFuture(fileUri: String) = {
        val fileFuture: Future[String] = {
            Future {
                val file = Source.fromFile(fileUri)
                val lines = try {
                    file.getLines().mkString("\n")
                }
                finally {
                    file.close()
                }

                lines
            }
        }

        fileFuture
    }

    def readFromUrl(url: String) = {
        val urlSpec: Future[List[String]] = Future {
            val webPage = Source.fromURL(url)
            val webPageLines = try {
                webPage.getLines.toList
            }
            finally {
                webPage.close()
            }

            webPageLines
        }

        urlSpec
    }
}
