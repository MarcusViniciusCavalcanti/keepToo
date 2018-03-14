package br.com.zonework.keeptoo.applicationPath;

import br.com.zonework.keeptoo.base.abstracsClasse.Folder;
import org.h2.store.fs.FileUtils;

import java.io.File;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public enum Path {
    DATABASE("database" + File.separator),
    BACKUP("backup" + File.separator);

    private String value;

    Path(String value) {
        this.value = value;
    }


    public String getFrom(Folder folder, String file) {
        String dir = folder.getAbsolutePathFolder() + File.separator + this.value;
        createFolderIfNotExists(dir);

        return dir + file;
    }

    private void createFolderIfNotExists(String path) {
        File dir = new File(path);

        if(!dir.exists()) {
            FileUtils.createDirectory(dir.getAbsolutePath());
        }
    }
}
