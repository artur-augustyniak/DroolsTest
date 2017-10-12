/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package droolstest;

import droolstest.facts.Bread;
import droolstest.facts.Milk;
import droolstest.facts.Product;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.rules.RuleException;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.StatefulSession;
import org.drools.core.rule.Package;

/**
 *
 * @author aaugustyniak
 */
public class DroolsTest {

    private final static Random r = new Random();

    public static Product nextProduct() {
        Product p = new Product();
        p.setAmount(r.nextInt(300));
        return p;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws org.drools.compiler.compiler.DroolsParserException
     * @throws javax.rules.RuleException
     */
    public static void main(String[] args) throws FileNotFoundException, DroolsParserException, IOException, RuleException {
        //simpleExample();
        ndExample();

    }

    public static void ndExample() throws DroolsParserException, IOException, RuleException {
        DroolsEngine droolsEngine = new DroolsEngine();
        List myList;
        myList = new ArrayList();
        
        droolsEngine.addRuleFromDrlFile("reguly.drl");		
        //droolsEngine.addRuleFromXlsFile("reguly.xls");

        Bread bread = new Bread("PszennyPełny", 100, 2, 50, Bread.Kinds.PSZENNY);
        Milk milk = new Milk("BiałePuszyste", 200, 3, 2, 2);

        droolsEngine.addFact(bread);
        droolsEngine.addFact(milk);
        droolsEngine.addGlobalVariable("myList", myList);
        droolsEngine.runRules();

        System.out.println(bread.getDescription());
        System.out.println(milk.getDescription());

        for (Iterator it = myList.iterator(); it.hasNext();) {
            String s = (String) it.next();
            System.out.println(s);
        }

    }

    public static void simpleExample() throws FileNotFoundException, DroolsParserException, IOException {
        PackageBuilder builder = new PackageBuilder();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        StatefulSession session = ruleBase.newStatefulSession();
        Reader inputStreamReader = new FileReader("rules.drl");
        builder.addPackageFromDrl(inputStreamReader);
        Package pkg = builder.getPackage();
        ruleBase.addPackage(pkg);
        List<Product> pl = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product p = nextProduct();
            pl.add(p);
            session.insert(p);
        }
        System.out.println(pl);
        session.fireAllRules();
        System.out.println(pl);

    }

}
