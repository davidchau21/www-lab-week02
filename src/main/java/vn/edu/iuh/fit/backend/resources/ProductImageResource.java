package vn.edu.iuh.fit.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.models.ProductImage;
import vn.edu.iuh.fit.backend.services.ProductImageService;

import java.util.ArrayList;
import java.util.List;

@Path("/ProductImage")
public class ProductImageResource {
    @Inject
    private ProductImageService productImageSer;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<ProductImage> all = productImageSer.getAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByID(@PathParam("id") int id) {
        ProductImage productImage = productImageSer.searchById(id);
        if (productImage == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(productImage).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ProductImage productImage) {
        boolean add = productImageSer.add(productImage);
        if (!add)
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(productImage).build();
    }

    @POST()
    @Path("/add-list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addList(List<ProductImage> productImages) {
        productImages.forEach(p -> {
            boolean add = productImageSer.add(p);
        });
        return Response.ok(productImages).build();
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, ProductImage productImage) {
        ProductImage temp = productImageSer.searchById(id);
        if (temp == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        boolean update = productImageSer.add(productImage);
        if (!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(productImage).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response del(@PathParam("id") long id) {
        boolean del = productImageSer.del(id);
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
