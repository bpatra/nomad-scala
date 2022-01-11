import com.hashicorp.nomad.scalasdk.NomadScalaApi
import com.hashicorp.nomad.javasdk.ServerQueryResponse
import com.hashicorp.nomad.apimodel.JobListStub
import TrackUtils.TrackHealth

object Main extends App {
  TrackHealth.checkTrackHealth("prod", "ha");
}