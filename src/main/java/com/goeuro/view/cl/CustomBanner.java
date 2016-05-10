package com.goeuro.view.cl;/**
 * @author tckb
 * Created on 08/05/16.
 */

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomBanner extends DefaultBannerProvider {
    public String getBanner() {
        StringBuffer buf = new StringBuffer();
        buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
        buf.append(" _____       ____               \n" +
                " / ___/___   / __/__ __ ____ ___ \n" +
                "/ (_ // _ \\ / _/ / // // __// _ \\\n" +
                "\\___/ \\___//___/ \\_,_//_/   \\___/\n" +
                "                                 " + OsUtils.LINE_SEPARATOR);
        buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
        buf.append("Version:" + this.getVersion());
        return buf.toString();
    }

    public String getVersion() {
        return "1.0.0";
    }

    public String getWelcomeMessage() {
        return "Welcome to GoEuro's CL Utility";
    }

    @Override
    public String getProviderName() {
        return "GoEuro";
    }
}
