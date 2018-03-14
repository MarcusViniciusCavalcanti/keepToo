package br.com.zonework.keeptoo.applicationPath;

import br.com.zonework.keeptoo.base.abstracsClasse.Folder;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class AppData extends Folder {
    private Path path;


    public AppData() {
        this.path = Paths.get(super.getFolderFor(this));
    }

    @Override
    public String getAbsolutePathFolder() {
        return this.path.toAbsolutePath().toString();
    }

    @Override
    public String getAbsolutePathFile() {
        return this.getAbsolutePathFolder();
    }
}
