package TrackUtils

import com.hashicorp.nomad.scalasdk.NomadScalaApi
import com.hashicorp.nomad.javasdk.ServerQueryResponse
import com.hashicorp.nomad.apimodel.JobListStub

object TrackHealth{
    def checkTrackHealth(env: String = "dev" ,trackPrefix: String = "bpatra"): Unit = {
      val api = NomadScalaApi(s"http://nomad.${env}.iadvize.io");
      val responseFuture: ServerQueryResponse[Seq[JobListStub]] =
          api.jobs.list()
      val results = responseFuture.getValue();

      val bpatraJobs = results
                        .filter( f => f.getName().startsWith(trackPrefix));
      
      val healthy = bpatraJobs.count(j => j.getStatus() == "running");
      val unhealthy = bpatraJobs.count(j => j.getStatus() != "running");

      println(s"Total jobs for ${trackPrefix} : ${bpatraJobs.count(x => true)}...");
      println(s"${healthy} healthy / ${unhealthy} unhealthy");

      bpatraJobs.filter(j => j.getStatus() != "running").foreach(j => println(s"JobName: ${j.getName()} - Status ${j.getStatus()}"))

    }

}