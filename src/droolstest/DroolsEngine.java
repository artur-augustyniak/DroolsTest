package droolstest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import javax.rules.RuleException;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.StatefulSession;
import org.drools.core.rule.Package;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;

public class DroolsEngine {

    private final StatefulSession session;
    private final PackageBuilder builder;
    private Package pkg;
    private final RuleBase ruleBase;

    public DroolsEngine() {
        builder = new PackageBuilder();
        ruleBase = RuleBaseFactory.newRuleBase();
        session = ruleBase.newStatefulSession();

    }

    public void addRuleFromDrlFile(String rulesFileName) throws RuleException {
        try {

            Reader inputStreamReader = new FileReader(rulesFileName);
            File file = new File(rulesFileName);
            builder.addPackageFromDrl(inputStreamReader);
            pkg = builder.getPackage();
            ruleBase.addPackage(pkg);
        } catch (IOException | DroolsParserException ex) {
            throw new RuleException("Błąd tworzenia reguł " + ex.getMessage());
        }
    }

    public void addRuleFromXlsFile(String rulesXlsFileName) throws DroolsParserException, IOException {
        SpreadsheetCompiler spreadsheetCompiler = new SpreadsheetCompiler();
        InputStream inputStreamReader = new FileInputStream(rulesXlsFileName);
        String drlString = spreadsheetCompiler.compile(inputStreamReader, InputType.XLS);
        Reader drlReader = new StringReader(drlString);
        builder.addPackageFromDrl(drlReader);
        pkg = builder.getPackage();
        ruleBase.addPackage(pkg);
    }

    public <T> void addFact(T fact) {
        session.insert(fact);
    }

    public <T> void addGlobalVariable(String refName, T ref) {
        session.setGlobal(refName, ref);
    }

    public int runRules() {
        return session.fireAllRules();
    }
}
