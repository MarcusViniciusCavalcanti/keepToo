package br.com.zonework.keeptoo.applicationPath;

import br.com.zonework.keeptoo.base.abstracsClasse.Folder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class Temp extends Folder {
    private Path path;

    /**
     * Instancia um objeto <b>Path</b> enviando como argumento o retorno do Path
     * recebido da classe pai bom base no Sistema operacional em execução
     *
     */
    public Temp() {
        this.path = Paths.get(super.getFolderFor(this));
    }

    /**
     * Instancia um objeto <b>Path</b> enviando como argumento o retorno do Path
     * recebido da classe pai bom base no Sistema operacional em execução
     *
     * @param fileName um arquivo para ser adicionado a pasta
     */
    public Temp(String fileName) {
        String result = fileName.replace("/", "");
        this.path = Paths.get(super.getFolderFor(this) + File.separator + result);
    }

    /**
     * Método que retorna o caminho absoluto já configurado para cada Sistema Operacional
     * Por exemplo:<br>
     *     <code>Windows</code> C:\Users\UserDomain\AppData\Local\FileUnitTemporary\keepToo_temp
     * @return o caminho absoluto da pasta raiz da aplicação
     */
    @Override
    public String getAbsolutePathFolder() {
        return this.path.toAbsolutePath().toString();
    }


    /**
     * Método que retorna o caminho absoluto já configurado para cada Sistema Operacional
     * Por exemplo:<br>
     *     <code>Windows</code> C:\Users\UserDomain\AppData\Local\FileUnitTemporary\keepToo_temp
     * @return o caminho absoluto do arquivo.
     */
    @Override
    public String getAbsolutePathFile() {
        return getAbsolutePathFolder();
    }
}
