package br.com.zonework.keeptoo.base.abstracsClasse;

import br.com.zonework.keeptoo.applicationPath.AppData;
import br.com.zonework.keeptoo.base.interfaces.Archive;
import br.com.zonework.keeptoo.utils.OsCheck;

import java.io.File;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public abstract class Folder implements Archive {
    private final String keepTooAppData =  File.separator + "keepToo_data";
    private final String keepTooAppDataHidden = File.separator + ".keepToo_data";
    private final String keepTooTemp = System.getProperty("java.io.tmpdir") + File.separator + "keepToo_temp";

    public abstract String getAbsolutePathFolder();

    protected String getFolderFor(Folder folder) {
        OsCheck.OSType currentOs = OsCheck.getOperatingSystemType();
        switch (currentOs){
            case Windows:
                if(folder.getClass().getName().equals(AppData.class.getName()))
                    return System.getenv("APPDATA") + keepTooAppData;
                return keepTooTemp;
            case Linux:
            case MacOS:
                if(folder.getClass().getName().equals(AppData.class.getName()))
                    return System.getProperty("user.home") + keepTooAppDataHidden;
                return keepTooTemp;
            default:
                throw new RuntimeException("Sistema não suportado!");
        }
    }
}
