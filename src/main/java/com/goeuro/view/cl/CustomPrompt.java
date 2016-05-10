package com.goeuro.view.cl;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

/**
 * @author tckb
 *         Created on 08/05/16.
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomPrompt extends DefaultPromptProvider {
    @Override
    public String getPrompt() {
        return "goeuro-cli>";
    }


    @Override
    public String getProviderName() {
        return "GoEuro";
    }
}
