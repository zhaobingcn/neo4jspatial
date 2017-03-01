package com.test.spatial;

import org.neo4j.gis.spatial.ShapefileImporter;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhzy on 2017/3/1.
 */
public class ImportShapeFile {
    public static void main(String[] args) throws IOException {
        File storeDir = new File("/Users/zhzy/Documents/Neo4j/spatial.db");
        GraphDatabaseService database = new GraphDatabaseFactory().newEmbeddedDatabase(storeDir);

        try (Transaction tx = database.beginTx()) {
            ShapefileImporter importer = new ShapefileImporter(database);
            importer.importFile("/Users/zhzy/Downloads/data/roa_4m.shp", "continent");
            tx.success();
        } finally {
            database.shutdown();
        }
    }
}
