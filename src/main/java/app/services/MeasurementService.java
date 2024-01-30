package app.services;
import app.exceptions.DatabaseException;
import app.repository.ConnectionPool;
import app.repository.MeasurementRepository;
import io.javalin.http.Context;
import java.util.List;

public class MeasurementService {
    public static void getAllMeasurements(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        try {
            List<Integer> lengthList = MeasurementRepository.getAllLengths(connectionPool);
            List<Integer> widthList = MeasurementRepository.getAllWidths(connectionPool);
            List<Integer> heightList = MeasurementRepository.getAllHeights(connectionPool);

            ctx.attribute("lengthList", lengthList);
            ctx.attribute("widthList", widthList);
            ctx.attribute("heightList", heightList);
        }catch (DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
