package vn.edu.iuh.fit.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.models.Order;
import vn.edu.iuh.fit.backend.services.OrdersServices;

import java.util.ArrayList;
import java.util.List;

@Path("/Orders")
public class OrdersResource {
    @Inject
    private OrdersServices ordersSer;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Order> all = ordersSer.getAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByID(@PathParam("id") int id) {
        Order orders = ordersSer.searchById(id);
        if (orders == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(orders).build();
    }

    /**
     * Lấy các Product từ x đến y.
     * Theo dòng trong cơ sở dữ liệu, không phải theo id
     *
     * @param x: là vị trí bắt đầu. x > 0 vì trong cơ sở dữ liệu dòng bắt đầu bằng 1
     * @param y: là vị trí kết thúc. y > x vì dòng đến không thể bé hơn dòng đi.
     * @return [dòng x, dòng y]
     */
    @GET
    @Path("/{x}-{y}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFromXToY(@PathParam("x") int x, @PathParam("y") int y) {
        if (x <= 0 || y < x)
            return Response.status(Response.Status.BAD_REQUEST).build();

        List<Order> fromXToY = ordersSer.getFromXToY(x, y);
        return Response.ok(fromXToY).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Order orders) {
        boolean add = ordersSer.add(orders);
        if (!add)
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(orders).build();
    }

    @POST()
    @Path("/add-list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addList(List<Order> orders) {
        orders.forEach(p -> {
            boolean add = ordersSer.add(p);
        });
        return Response.ok(orders).build();
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Order orders) {
        Order temp = ordersSer.searchById(id);
        if (temp == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        boolean update = ordersSer.add(orders);
        if (!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(orders).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response del(@PathParam("id") long id) {
        boolean del = ordersSer.del(id);
        if (!del)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(id).build();
    }

    @DELETE
    @Path("/delete-multiple")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delMultiple(List<Long> list) {
        List<Long> listDel = new ArrayList<>();

        list.forEach(i -> {
            Response del = del(i);
            if (del.getStatus() != 200)
                return;
            else
                listDel.add(i);
        });

        return Response.ok(listDel).build();
    }
}
