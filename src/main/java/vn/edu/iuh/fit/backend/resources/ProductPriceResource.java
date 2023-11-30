package vn.edu.iuh.fit.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.services.ProductPriceService;
import vn.edu.iuh.fit.backend.services.ProductServices;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Path("/ProductPrice")
public class ProductPriceResource {
    @Inject
    private ProductPriceService productPriceSer;
    @Inject
    private ProductServices productSer;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@PathParam("id") long id) {
        System.out.println("oke");
        List<ProductPrice> all = productPriceSer.getAll(id);
        return Response.ok(all).build();
    }

    /**
     * Lấy các ProductPrice có product_id = id và lấy từ x đến y.
     * 
     * @param id: product_id
     * @param x:  là vị trí bắt đầu. x > 0 vì trong cơ sở dữ liệu dòng bắt đầu bằng
     *            1
     * @param y:  là vị trí kết thúc. y > x vì dòng đến không thể bé hơn dòng đi.
     * @return [dòng x, dòng y]
     */
    @GET
    @Path("/{id}/{x}/{y}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFromXToY(@PathParam("id") long id, @PathParam("x") int x, @PathParam("y") int y) {
        if (x <= 0 || y < x)
            return Response.status(Response.Status.BAD_REQUEST).build();

        List<ProductPrice> fromXToY = productPriceSer.getFromXToY(id, x, y);
        if (fromXToY == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(fromXToY).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ProductPrice productPrice) {
        boolean add = productPriceSer.add(productPrice);
        if (add)
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(productPrice).build();
    }

    @POST()
    @Path("/add-list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addList(List<ProductPrice> productPrices) {
        List<ProductPrice> temp = new ArrayList<>();
        productPrices.forEach(p -> {
            boolean add = productPriceSer.add(p);
            if (add)
                temp.add(p);
        });
        return Response.ok(temp).build();
    }

//     @PUT()
//     @Path("/{id}")
//     @Consumes(MediaType.APPLICATION_JSON)
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response update(@PathParam("id") long id, ProductPrice productPrice) {
//     ProductPrice temp = productPriceSer.searchById(id);
//         if (temp == null)
//         return Response.status(Response.Status.NOT_FOUND).build();
//         boolean update = productPriceSer.add(productPrice);
//         if (!update)
//         return Response.status(Response.Status.NOT_FOUND).build();
//         return Response.ok(productPrice).build();
//     }

}
