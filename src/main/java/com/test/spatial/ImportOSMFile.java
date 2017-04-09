package com.test.spatial;

import org.neo4j.gis.spatial.SpatialDatabaseService;
import org.neo4j.gis.spatial.osm.OSMImporter;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserters;
import org.neo4j.unsafe.batchinsert.internal.BatchInserterImpl;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhzy on 2017/3/1.
 */
public class ImportOSMFile {

    public static void main(String[] args) throws IOException, XMLStreamException {
        File dbPath = new File("/home/zhzy/Documents/Neo4j/osm");
        OSMImporter importer = new OSMImporter("map");
        Map<String, String> config = new HashMap<String, String>();
        config.put("neostore.nodestore.db.mapped_memory", "90M" );
        config.put("dump_configuration", "true");
        BatchInserter batchInserter = BatchInserters.inserter(dbPath, config);
        importer.importFile(batchInserter, "/home/zhzy/Downloads/data/map.osm", false);
        batchInserter.shutdown();
        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
        importer.reIndex(db, 10000);
        db.shutdown();
    }
}
