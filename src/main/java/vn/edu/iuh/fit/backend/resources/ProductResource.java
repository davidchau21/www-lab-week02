package vn.edu.iuh.fit.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.services.ProductServices;

import java.util.ArrayList;
import java.util.List;

@Path("/Product")
public class ProductResource {
    @Inject
    private ProductServices productSer;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Product> all = productSer.getAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByID(@PathParam("id") int id) {
        Product product = productSer.searchById(id);
        if (product == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(product).build();
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

        List<Product> fromXToY = productSer.getFromXToY(x, y);
        return Response.ok(fromXToY).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Product product) {
        boolean add = productSer.add(product);
        System.out.println("alo");
        if (!add)
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok("true").build();
    }

    @POST()
    @Path("/add-list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addList(List<Product> products) {
        products.forEach(p -> {
            boolean add = productSer.add(p);
        });
        return Response.ok("true").build();
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Product product) {
        Product temp = productSer.searchById(id);
        if (temp == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        boolean update = productSer.add(product);
        if (!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok("true").build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response del(@PathParam("id") long id) {
        boolean del = productSer.del(id);
        if (!del)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok("true").build();
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
