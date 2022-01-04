import com.hashicorp.nomad.scalasdk.NomadScalaApi
import com.hashicorp.nomad.javasdk.ServerQueryResponse
import com.hashicorp.nomad.apimodel.JobListStub

object Main extends App {
  val api = NomadScalaApi("http://nomad.dev.iadvize.io");
  val responseFuture: ServerQueryResponse[Seq[JobListStub]] =
    api.jobs.list()
  val results = responseFuture.getValue();

  val bpatraJobs = results.filter( f => f.getName().startsWith("bpatra-"));
  
  bpatraJobs.filter( j => j.getStatus() != "running").foreach(j => println(s" JobName ${j.getName()} - Status ${j.getStatus()}"));
  
  println("...completed.");
}