package org.linkcutter.impl.services;

import com.google.common.base.Preconditions;
import com.google.inject.name.Named;
import org.linkcutter.impl.model.Link;
import org.linkcutter.impl.utils.SymbolGenerator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;

/**
 * @author Lipatov Nikita
 */
@Singleton
public class LinksService {

    private String host;

    @Inject
    public LinksService(@Named("linkcutter.host") String host) {
        Preconditions.checkNotNull(host);
        Preconditions.checkArgument(!host.isEmpty());
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
