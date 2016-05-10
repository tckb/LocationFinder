package com.goeuro.view;

import com.goeuro.service.impl.GoEuroService;
import com.goeuro.service.impl.GoEuroService.GoEuroApiService;
import com.goeuro.view.cl.GoEuroCLCommands;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.shell.CommandLine;
import org.springframework.shell.SimpleShellCommandLineOptions;
import org.springframework.shell.core.ExitShellRequest;
import org.springframework.shell.core.JLineShellComponent;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(
        excludeFilters = @Filter(
                type = FilterType.REGEX,
                pattern = {
                        "org.springframework.shell.commands.*",
                }),
        basePackages = {
                "org.springframework.shell.converters",
                "org.springframework.shell.plugin.support",
                "com.goeuro.service",
                "com.goeuro.view.cl"

        })
/**
 *  A command line "view" of the Location service
 */
public class CLView {

    private static Logger log = Logger.getLogger(CLView.class.getName());
    private static ApplicationContext ctx;
    private static CommandLine commandLine;


    public static void main(String[] args) throws IOException {
        ctx = SpringApplication.run(CLView.class);

        if (args.length > 0) {

            String location = args[0];
            String resultFile = args.length > 1 ? args[1] : "out.csv";
            final GoEuroCLCommands clCommands = ctx.getBean(GoEuroCLCommands.class);
            log.debug("Search location: " + location);
            final String matchedLocations = clCommands.searchLocation(location, resultFile);
            log.info("Found results: " + matchedLocations);

        } else {
            commandLine = SimpleShellCommandLineOptions.parseCommandLine(args);
            new CLView().runShell();
        }
    }

    @Bean(name = "shell")
    public JLineShellComponent jLineShellComponent() {
        return new JLineShellComponent();
    }

    @Bean
    public GoEuroApiService apiService() {
        return baseService().getService();
    }

    @Bean
    public GoEuroService baseService() {
        return new GoEuroService();
    }


    @Bean
    public CommandLine commandLine() {
        return new CommandLine(null, 3000, null);
    }


    private ExitShellRequest runShell() {

        String[] commandsToExecuteAndThenQuit = commandLine.getShellCommandsToExecute();

        JLineShellComponent shell = ctx.getBean(JLineShellComponent.class);
        ExitShellRequest exitShellRequest;

        if (null != commandsToExecuteAndThenQuit) {
            boolean successful = false;
            exitShellRequest = ExitShellRequest.FATAL_EXIT;

            for (String cmd : commandsToExecuteAndThenQuit) {
                successful = shell.executeCommand(cmd).isSuccess();
                if (!successful)
                    break;
            }

            if (successful) {
                exitShellRequest = ExitShellRequest.NORMAL_EXIT;
            }
        } else {
            shell.start();
            shell.promptLoop();
            exitShellRequest = shell.getExitShellRequest();
            if (exitShellRequest == null) {
                exitShellRequest = ExitShellRequest.NORMAL_EXIT;
            }
            shell.waitForComplete();
        }

        ((ConfigurableApplicationContext) ctx).close();
        return exitShellRequest;
    }
}
