package br.com.zonework.keeptoo.checkApplication.process;

import br.com.zonework.keeptoo.applicationPath.AppData;
import br.com.zonework.keeptoo.applicationPath.Path;
import br.com.zonework.keeptoo.checkApplication.CheckProcess;
import br.com.zonework.keeptoo.controller.SlashController;
import br.com.zonework.keeptoo.properties.PersistenceUtils;
import br.com.zonework.keeptoo.exception.CheckApplicationException;
import br.com.zonework.keeptoo.utils.logger.LogInformation;
import br.com.zonework.keeptoo.exception.DatabaseException;
import org.h2.store.fs.FileUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class CheckEnvironment implements CheckProcess {
    private CheckProcess process;
    private LogInformation logInformation;

    @Override
    public Boolean isComplete(SlashController controller) throws CheckApplicationException {
        logInformation = LogInformation.getLogInformation();
        logInformation.writeInfo("Inicializando checagem de ambiente.");
        controller.showLoadFile();

        checkDatabase();
        checkConnectionDatabase();

        return process.isComplete(controller);
    }

    @Override
    public void setNext(Optional<CheckProcess> checkProcess) {
        checkProcess.ifPresent(process -> this.process = process);
    }

    private void checkDatabase() throws DatabaseException {
        logInformation.writeInfo("verificando banco de dados");

        String pathDataBase = Path.DATABASE.getFrom(new AppData(), "keepToo");

        if(!FileUtils.exists(pathDataBase)) {
            throw new DatabaseException(new CheckApplicationException("error ao encontrar banco de dados"));
        }

        logInformation.writeInfo("verificação do banco de dados finalizado.");
    }

    private void checkConnectionDatabase() throws HibernateException {
        logInformation.writeInfo("verificando conexão com banco de dados.");

        PersistenceUtils persistenceUtils = PersistenceUtils.getInstance();

        SessionFactory session = persistenceUtils.getSessionFactory();
        Session openSession = session.openSession();

        if (!openSession.isConnected()) {
            throw new DatabaseException("error na conexão com o banco de dados");
        }

        openSession.close();

        logInformation.writeInfo("verificação da conexão com banco de dados finalizado.");
    }
}
