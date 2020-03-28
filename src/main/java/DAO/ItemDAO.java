package DAO;

import DTO.DTO;
import DTO.ItemDTO;
import Mongo_db.MongoDB_consts;
import Mongo_db.dbConnection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class ItemDAO {
    private static ItemDAO instanceItemDAO = new ItemDAO();

    private MongoClient mongoClient = dbConnection.mongoClient;
    private MongoDatabase db = mongoClient.getDatabase(MongoDB_consts.db_name);
    private MongoCollection<Document> item_collection = db.getCollection(MongoDB_consts.item_col);

    private ItemDAO() {
    }

    public static ItemDAO getInstance() {
        if (instanceItemDAO == null) {
            instanceItemDAO = new ItemDAO();
        }
        return instanceItemDAO;
    }

    public void addItem(ItemDTO item) {
        Document doc = new Document("machineCode", item.machineCode)
                .append("name", item.name)
                .append("price", item.price);

        item_collection.insertOne(doc);
    }

    public ArrayList<DTO> getAllItems() {
        ArrayList<DTO> itemsList = new ArrayList<>();

        try (MongoCursor<Document> cursor = item_collection.find().iterator()) {
            Document doc;
            while (cursor.hasNext()) {
                doc = cursor.next();
                itemsList.add(new ItemDTO(
                        (long) doc.get("machineCode"),
                        (String) doc.get("name"),
                        (String) (doc.get("price"))));
            }
        } catch (Throwable err) {
            System.out.println("error in ItemDAO: " + err.toString());
        }
        return itemsList;
    }

    public long getIndex() {
        return item_collection.countDocuments();
    }


}
