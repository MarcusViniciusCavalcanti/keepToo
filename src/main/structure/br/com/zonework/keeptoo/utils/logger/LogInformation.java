package br.com.zonework.keeptoo.utils.logger;

import br.com.zonework.keeptoo.utils.exception.LoggerException;
import org.fusesource.jansi.AnsiConsole;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Logger para debug de desenvolvimento em ambiente desenvolvimento
 *
 * Classe segue o padrão Singleton, escreve no terminal as informações
 * adivinda das ações executadas, para isto acontecer a inicialização
 * do aplicativo precisa enviar um argumento <code>dev</code> para as
 * informações de log serem escrita no console.
 *
 * Utiliza-se da biblioteca JCDP jansi versão 1.17
 *
 * <code>https://github.com/fusesource/jansi</code>
 * @version 1.0
 * @autor Vinicius Cavalcanti
 * @since 1.0 11/03/18 project keeptoo
 */
public final class LogInformation {
    private final String arg;
    private static LogInformation instance;

    private LogInformation(String arg) {
        this.arg = arg;
    }

    /**
     * Prepara uma instancia do logInformation
     * caso o args <code>dev</code> for presente
     *
     * a instancia inicializara as escrita no console.
     * @param args provindo do main
     * @return uma instancia de LogIformation
     */
    public static LogInformation getLogInformation(String... args) {
        String arg = "production";
        if (args.length > 0) {
            arg = args[0];
        }

        if(instance == null) {
            instance = new LogInformation(arg);
            return instance;
        }

        return instance;
    }

    /**
     * Retorna uma instancia de LogInformation
     * @return LoginInformation
     * @exception LoggerException caso o logger não for configurado adequadamente.
     */
    public static LogInformation getLogInformation() {
        if(instance == null ) {
            throw new LoggerException("Logger Not Configure");
        }
        return instance;
    }

    /**
     * Escreve no console uma tag de informação
     * @param msg a messagem que será exposta no console
     */
    public void writeInfo(String msg) {
        if(arg.equals("dev")) {
            AnsiConsole.systemInstall();
            String methodCallName = Thread.currentThread().getStackTrace()[2].getMethodName();
            String classCallName = Thread.currentThread().getStackTrace()[2].getClassName();

            System.out.println(ansi().render("@|cyan [INFO]|@ ---> " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) +
                    " =============== @|green [Classe: " + classCallName + "]|@" + " ----- (@|cyan Método: " + methodCallName + "|@) -----> " + msg));
            AnsiConsole.systemUninstall();
        }
    }

    /**
     * Escreve no console uma tag de erro
     * @param exception exeption lançada
     * @param msg mensagem que será exposta no console.
     */
    public void writeError(Exception exception, String msg) {
        if(arg.equals("dev")) {
            String methodCallName = Thread.currentThread().getStackTrace()[2].getMethodName();
            String classCallName = Thread.currentThread().getStackTrace()[2].getClassName();
            AnsiConsole.systemInstall();
            System.out.println(ansi().render("@|red [ERROR]|@ --> " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) +
                    " =============== @|green [Classe: " + classCallName + "]|@" + " ----- (@|cyan Método: " + methodCallName + "|@) -----> @|red " + msg + "|@ " +
                    "@|green [Exception: " + exception.getClass().getName() + "]|@"
            ));
            System.out.println(ansi().render("@|red [Menssagem: " + exception.getMessage() + "]|@"));
            AnsiConsole.systemUninstall();
        }

    }

}
