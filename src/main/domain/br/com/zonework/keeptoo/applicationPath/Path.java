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

    public String getFrom(Folder folder) {
        String dir = folder.getAbsolutePathFolder() + File.separator;
        createFolderIfNotExists(dir);

        return dir;
    }

    public String getFrom(Folder folder, String file) {
        String dir = folder.getAbsolutePathFolder() + File.separator + this.value + file;
        createFileIfNotExists(dir);

        return dir;
    }

    private void createFileIfNotExists(String path) {
        File dir = new File(path);

        if(!dir.exists()) {
            FileUtils.createFile(dir.getAbsolutePath());
        }
    }

    private void createFolderIfNotExists(String path) {
        if(FileUtils.exists(path)) {
            FileUtils.createDirectory(path);
        }
    }
}
