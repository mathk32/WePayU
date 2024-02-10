import org.wepayu.EmpregadoFacade;
import easyaccept.EasyAccept;
import util.Variables;
import util.VariablesImpl;

public class Testes {
    public static void main(String[] args) {
        EmpregadoFacade empregadoController = new EmpregadoFacade();
        Variables variables = new VariablesImpl();
        EasyAccept tester = new EasyAccept();

        try {
            String testFileName = "C:\\Users\\matheus\\Desktop\\WePayU\\src\\test\\java\\ScriptTeste.txt";
            tester.runAcceptanceTest(empregadoController, testFileName, variables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

