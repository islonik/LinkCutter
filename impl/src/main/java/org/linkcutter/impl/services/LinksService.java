package org.linkcutter.impl.services;

import org.linkcutter.impl.model.Link;
import org.linkcutter.impl.utils.SymbolGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Lipatov Nikita
 */
@Component
public class LinksService {

    private String host;

    @Autowired
    public LinksService(@Value("${linkcutter.host}")String host) {
        //Preconditions.checkNotNull(host);
        //Preconditions.checkArgument(!host.isEmpty());
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void cut(Link link) {
        String shortText = generateShortText();
        String shortPath = host + "/" + shortText;
        link.setShortUrl(shortPath);
        link.setCreationDate(new Date());
    }

    /**
     * Should return 6 symbols:
     * @return
     */
    String generateShortText() {
        StringBuilder shortBuilder = new StringBuilder(6);
        for(int i = 0; i < 6; i++) {
            shortBuilder.append(SymbolGenerator.generateSymbol());
        }
        return shortBuilder.toString();
    }

}
