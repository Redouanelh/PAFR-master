package nl.richrail.presentation.parser;

import nl.richrail.controller.command.*;
import nl.richrail.controller.factory.TypeBasedComponentFactory;
import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;
import org.antlr.v4.runtime.tree.TerminalNode;
import parser.RichRailBaseListener;
import parser.RichRailParser;

import java.util.List;

public class RichRailCli extends RichRailBaseListener {

    private LoggerInterface logger = Company.getInstance().getLogger();

    @Override
    public void enterHelpcommand(RichRailParser.HelpcommandContext ctx) {
        new HelpCommand().exec();
    }

    @Override
    public void enterNewcommand(RichRailParser.NewcommandContext ctx) {
        try {
            String option = ctx.TYPE.getText();
            if (ctx.ID().toString().equals("<missing ID>")) {
                logger.log("New command failed, make sure you are using the right layout. Check the help command." + "\n");
                return;
            }
            if (option.equals("locomotive")) {
                new NewLocomotiveCommand(ctx.ID().toString(), null, new TypeBasedComponentFactory("locomotive")).exec();
            } else if (option.equals("passenger")) {
                new NewPassengerWagonCommand(ctx.ID().toString(), (ctx.ARG(0) == null ? null : Integer.parseInt(ctx.ARG(0).toString())), new TypeBasedComponentFactory(ctx.TYPE.getText())).exec();
            } else if (option.equals("freight")) {
                new NewFreightWagonCommand(ctx.ID().toString(), (ctx.ARG(0) == null ? null : Integer.parseInt(ctx.ARG(0).toString())), new TypeBasedComponentFactory(ctx.TYPE.getText())).exec();
            }
        } catch (Exception e) {
            logger.log("New command failed, make sure you are using the right layout. Check the help command." + "\n");
        }
    }

    @Override
    public void enterAddcommand(RichRailParser.AddcommandContext ctx) {
        try {
            List<TerminalNode> l = ctx.ID();
            new AddCommand(l.get(0).toString(), l.get(1).toString()).exec();
        } catch (Exception e) {
            logger.log("Add command failed, make sure you are using the right layout. Check the help command." + "\n");
        }
    }

    @Override
    public void enterGetcommand(RichRailParser.GetcommandContext ctx) {
        new GetCommand(ctx.ID().toString()).exec();
    }

    @Override
    public void enterDelcommand(RichRailParser.DelcommandContext ctx) {
        try {
            String option = ctx.TYPE.getText();
            if (option.equals("wagon")) {
                new DeleteComponentCommand(ctx.ID().toString()).exec();
            } else if (option.equals("locomotive")) {
                new DeleteLocomotiveCommand(ctx.ID().toString()).exec();
            }
        } catch (Exception e) {
            logger.log("Delete command failed, make sure you are using the right layout. Check the help command." + "\n");
        }
    }

    @Override
    public void enterRemcommand(RichRailParser.RemcommandContext ctx) {
        try {
            List<TerminalNode> l = ctx.ID();
            new RemoveCommand(l.get(0).toString(), l.get(1).toString()).exec();
        } catch (Exception e) {
             logger.log("Remove command failed, make sure you are using the right layout. Check the help command." + "\n");
        }
    }

    @Override
    public void enterGetallcommand(RichRailParser.GetallcommandContext ctx) {
        new GetAllCommand().exec();
    }

    @Override
    public void enterSavecommand(RichRailParser.SavecommandContext ctx) {
        try {
            new SaveCommand(ctx.FILENAME().toString()).exec();
        } catch (Exception e) {
            logger.log("Save command failed, make sure you are using the right layout. Check the help command." + "\n");
        }
    }

    @Override
    public void enterLoadcommand(RichRailParser.LoadcommandContext ctx) {
        try {
            if (ctx.FILENAME().toString().equals("<missing FILENAME>")) {
                logger.log("Load command failed, make sure you are using the right layout. Check the help command." + "\n");
                return;
            }
            new LoadCommand(ctx.FILENAME().toString()).exec();
        } catch (Exception e) {
            logger.log("Load command failed, make sure you are using the right layout. Check the help command." + "\n");
        }
    }

}