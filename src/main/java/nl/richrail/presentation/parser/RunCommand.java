package nl.richrail.presentation.parser;

import nl.richrail.controller.command.CommandInterface;
import nl.richrail.presentation.ObserverMechanism;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.RichRailLexer;
import parser.RichRailListener;
import parser.RichRailParser;

import java.util.Observable;

public class RunCommand extends Observable implements CommandInterface {

    private String command;

    public RunCommand(String command) {
        this.command = command;
    }

    public RunCommand getRunCommandInstance() {
        return this;
    }

    @Override
    public void exec() {
        CharStream lineStream = CharStreams.fromString(command.toLowerCase());

        // Tokenize / Lexical analysis
        Lexer lexer = new RichRailLexer(lineStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create Parse Tree
        RichRailParser parser = new RichRailParser(tokens);
        ParseTree tree = parser.command();

        // Create ParseTreeWalker and Custom Listener
        ParseTreeWalker walker = new ParseTreeWalker();
        RichRailListener listener = new RichRailCli();

        // Walk over ParseTree using Custom Listener that listens to enter/exit events
        walker.walk(listener, tree);

        // Observer
        ObserverMechanism observer = ObserverMechanism.getInstance();
        Observable observable = this;
        observable.addObserver(observer);

        setChanged();
        notifyObservers();

    }
}
