package fr.ot;

import fr.ot.entities.ApiKeysEntity;
import fr.ot.repository.ApiKeysRepository;
import fr.ot.security.MyApiKey;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Tag(name = "ApiKey")
@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public class ApiKeyRessource {

    @Inject
    ApiKeysRepository apiKeysRepository;

    @Path("/test")
    @GET
    public Response test(@HeaderParam(MyApiKey.APIKEY_PREFIX) String apiKey){
        if(MyApiKey.exist(apiKey)){
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("/register")
    @POST
    @Transactional
    public Response register() {
        String apiKey = "";
        do {
            apiKey = MyApiKey.generateApiKey();
        } while (MyApiKey.exist(apiKey));
        ApiKeysEntity apiKeysEntity = new ApiKeysEntity(apiKey);
        apiKeysRepository.persist(apiKeysEntity);
        return Response.ok().header(MyApiKey.APIKEY_PREFIX, apiKey).build();
    }
}
