package com.goeuro.view.cl;

import org.springframework.shell.commands.ConsoleCommands;
import org.springframework.shell.commands.ExitCommands;
import org.springframework.shell.commands.HelpCommands;
import org.springframework.stereotype.Component;

/**
 * @author tckb
 *         Created on 09/05/16.
 *         <p>
 *         A list of extra commands for the console
 */

@Component
public class HelpCommand extends HelpCommands {
}

@Component
class ConsoleCommand extends ConsoleCommands {
}

@Component
class ExitCommand extends ExitCommands {
}

