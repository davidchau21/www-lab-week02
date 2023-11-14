package vn.edu.iuh.fit.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.models.OrderDetail;
import vn.edu.iuh.fit.backend.services.OrderDetailService;

import java.util.ArrayList;
import java.util.List;

@Path("/OrderDetail")
public class OrderDetailResource {
    @Inject
    private OrderDetailService orderDetailSer;

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@PathParam("id") long id) {
        List<OrderDetail> all = orderDetailSer.getAll(id);
        return Response.ok(all).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(OrderDetail orderDetail) {
        boolean add = orderDetailSer.add(orderDetail);
        if (add)
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(orderDetail).build();
    }

    @POST()
    @Path("/add-list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addList(List<OrderDetail> productPrices) {
        List<OrderDetail> temp = new ArrayList<>();
        productPrices.forEach(p -> {
            boolean add = orderDetailSer.add(p);
            if (add)
                temp.add(p);
        });
        return Response.ok(temp).build();
    }

}
