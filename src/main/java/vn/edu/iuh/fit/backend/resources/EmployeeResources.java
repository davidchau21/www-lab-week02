package vn.edu.iuh.fit.backend.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.services.EmployeeServices;
import vn.edu.iuh.fit.backend.models.Employee;

@Path("/employees")
public class EmployeeResources {
    @Inject
    private EmployeeServices employeeServices;

    public EmployeeResources() {
        employeeServices = new EmployeeServices();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Employee> all = employeeServices.getAll();
        return Response.ok(all).build();
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
    public Response getFromXToY(@PathParam("x") int x,@PathParam("y") int y){
        if(x <= 0 || y<x)
            return Response.status(Response.Status.BAD_REQUEST).build();

        List<Employee> fromXToY = employeeServices.getFromXToY(x, y);
        return Response.ok(fromXToY).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getEmployeeById(@PathParam("id") long id) {
        Optional<Employee> op = employeeServices.findById(id);
        if (op.isPresent()) {
            return Response.ok(op.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Produces("application/json")
    public Response getAllEmployee() {
        List<Employee> list = employeeServices.getAll();
        return Response.ok(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Employee employee){
        boolean add = employeeServices.insertEmployee(employee);
        if(!add) return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(employee).build();
    }

    @POST()
    @Path("/add-list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addList(List<Employee> employees){
        employees.forEach(p->{
            boolean add = employeeServices.insertEmployee(p);
        });
        return Response.ok(employees).build();
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Employee employee){
        Optional<Employee> temp = employeeServices.findById(id);
        if(temp == null) return Response.status(Response.Status.NOT_FOUND).build();
        boolean update = employeeServices.insertEmployee(employee);
        if(!update) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(employee).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response del(@PathParam("id") long id){
        boolean del = employeeServices.delete(id);
        if(!del) return Response.status(Response.Status.NOT_FOUND).build();

        return  Response.ok(id).build();
    }

    @DELETE
    @Path("/delete-multiple")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delMultiple(List<Long> list){
        List<Long> listDel = new ArrayList<>();

        list.forEach(i->{
            Response del = del(i);
            if(del.getStatus()!=200) return;
            else listDel.add(i);
        });

        return  Response.ok(listDel).build();
    }

}
