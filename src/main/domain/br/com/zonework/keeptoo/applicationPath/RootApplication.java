package br.com.zonework.keeptoo.applicationPath;

import br.com.zonework.keeptoo.base.abstracsClasse.Folder;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class RootApplication extends Folder {
    private Path path;

    /**
     * Instancia um objeto <b>Path</b> enviando como argumento <code>System.getProperty("user.dir")</code>
     */
    public RootApplication() {
        this.path = Paths.get(System.getProperty("user.dir"));
    }

    /**
     * Método que retorna o caminho absoluto já configurado para cada Sistema Operacional
     * Por exemplo:<br>
     *     <code>Windows</code> C:\Users\keepToo\localDaInstalação\keepToo
     * @return o caminho absoluto da pasta raiz da aplicação
     */
    @Override
    public String getAbsolutePathFolder() {
        return this.path.toAbsolutePath().toString();
    }

    /**
     * Método que retorna o caminho absoluto já configurado para cada Sistema Operacional
     * Por exemplo:<br>
     *     <code>Windows</code> C:\Users\keepToo\localDaInstalação\keepToo
     * @return o caminho absoluto do arquivo.
     */
    @Override
    public String getAbsolutePathFile() {
        return this.getAbsolutePathFolder();
    }
}
