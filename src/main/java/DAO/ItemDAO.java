package DAO;

import DTO.DTO;
import DTO.ItemDTO;
import Mongo_db.MongoDB_consts;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class ItemDAO {
    private static ItemDAO instanceItemDAO = new ItemDAO();

    private MongoDB_consts mdb_const = new MongoDB_consts();
    private MongoClient mongoClient = new MongoClient(mdb_const.host, mdb_const.port);
    private MongoDatabase db = mongoClient.getDatabase(mdb_const.db_name);
    private MongoCollection<Document> item_collection = db.getCollection(mdb_const.item_col);

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

        MongoCursor<Document> cursor = item_collection.find().iterator();
        Document doc;

        try {
            while (cursor.hasNext()) {
                doc = cursor.next();
                itemsList.add(new ItemDTO(
                        (long) doc.get("machineCode"),
                        (String) doc.get("name"),
                        (long) doc.get("price")));
            }
        } catch (Throwable err) {
            System.out.println("error in ItemDAO: " + err.toString());
        } finally {
            cursor.close();
        }
        return itemsList;
    }

    public long getIndex() {
        return item_collection.countDocuments();
    }


}
