package br.com.zonework.keeptoo.properties;

import br.com.zonework.keeptoo.applicationPath.AppData;
import br.com.zonework.keeptoo.applicationPath.Path;
import br.com.zonework.keeptoo.base.abstracsClasse.Folder;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class PersistenceUtils {
    private static final String PERSISTENCE_UNIT = "persistence";
    private static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
    private static final String JDBC_H2 =  "jdbc:h2:";

    private static PersistenceUtils instance;

    public static EntityManagerFactory entityManagerFactory;

    private PersistenceUtils() {
        setPersistenceUnit(new AppData(), "keepToo");
    }

    public static PersistenceUtils getInstance() {
        return instance == null ? new PersistenceUtils() : instance;
    }

    private void setPersistenceUnit(Folder folder, String databaseName) {
        Map properties = new HashMap();
        properties.put(HIBERNATE_CONNECTION_URL, getDbURL(folder, databaseName));
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, properties);
    }

    private String getDbURL(Folder folder, String databaseName) {
        return JDBC_H2 + Path.DATABASE.getFrom(folder, databaseName);
    }
}
