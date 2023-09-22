package vn.edu.iuh.fit.resources;

import java.util.List;
import java.util.Optional;

import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.Employee;
import vn.edu.iuh.fit.services.EmployeeServices;

@Path("/employees")
public class EmployeeResources {
    private EmployeeServices employeeServices;

    public EmployeeResources() {
        employeeServices = new EmployeeServices();
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

    @GET
    @Produces("application/json")
    public Response insert(Employee employee) {
        employeeServices.insertEmployee(employee);
        return Response.ok(employee).build();
    }

}
