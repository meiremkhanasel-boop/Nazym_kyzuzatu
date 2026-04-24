package com.assel.nazym_kyzuzatu;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API для управления гостями
 * Endpoint: /api/guests
 */
@Path("/api/guests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GuestResource {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * GET /api/guests - Получить всех гостей
     */
    @GET
    public Response getAllGuests() {
        try {
            List<Guest> guests = GuestDAO.getAllGuests();
            return Response.ok(guests).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /**
     * GET /api/guests/{id} - Получить гостя по ID
     */
    @GET
    @Path("/{id}")
    public Response getGuestById(@PathParam("id") int id) {
        try {
            Guest guest = GuestDAO.getGuestById(id);
            if (guest != null) {
                return Response.ok(guest).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /**
     * POST /api/guests - Сохранить нового гостя
     */
    @POST
    public Response saveGuest(Guest guest) {
        try {
            if (guest.getName() == null || guest.getName().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Имя гостя обязательно\"}").build();
            }

            int guestId = GuestDAO.saveGuest(guest);
            guest.setGuestId(guestId);

            return Response.status(Response.Status.CREATED).entity(guest).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /**
     * PUT /api/guests/{id} - Обновить гостя
     */
    @PUT
    @Path("/{id}")
    public Response updateGuest(@PathParam("id") int id, Guest guest) {
        try {
            guest.setGuestId(id);
            if (GuestDAO.updateGuest(guest)) {
                return Response.ok(guest).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /**
     * DELETE /api/guests/{id} - Удалить гостя
     */
    @DELETE
    @Path("/{id}")
    public Response deleteGuest(@PathParam("id") int id) {
        try {
            if (GuestDAO.deleteGuest(id)) {
                return Response.noContent().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /**
     * GET /api/guests/stats - Получить статистику
     */
    @GET
    @Path("/stats")
    public Response getStatistics() {
        try {
            GuestStatistics stats = GuestDAO.getStatistics();
            return Response.ok(stats).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /**
     * GET /api/guests/attendance/{status} - Получить гостей по статусу
     * status: yes, no, pending
     */
    @GET
    @Path("/attendance/{status}")
    public Response getGuestsByAttendance(@PathParam("status") String status) {
        try {
            List<Guest> guests = GuestDAO.getGuestsByAttendance(status);
            return Response.ok(guests).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}

