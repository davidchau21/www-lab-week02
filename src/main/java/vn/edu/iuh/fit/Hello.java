package vn.edu.iuh.fit;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class Hello {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}
